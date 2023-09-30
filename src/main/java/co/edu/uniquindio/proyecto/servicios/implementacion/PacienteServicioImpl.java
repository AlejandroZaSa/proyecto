package co.edu.uniquindio.proyecto.servicios.implementacion;

import co.edu.uniquindio.proyecto.dto.admin.ActualizarMedicoDTO;
import co.edu.uniquindio.proyecto.dto.admin.HorarioDTO;
import co.edu.uniquindio.proyecto.dto.clinica.ItemPqrsDTO;
import co.edu.uniquindio.proyecto.dto.paciente.*;
import co.edu.uniquindio.proyecto.modelo.entidades.*;
import co.edu.uniquindio.proyecto.modelo.enums.Dia;
import co.edu.uniquindio.proyecto.modelo.enums.Especialidad;
import co.edu.uniquindio.proyecto.modelo.enums.EstadoPqrs;
import co.edu.uniquindio.proyecto.repositorios.*;
import co.edu.uniquindio.proyecto.servicios.interfaces.PacienteServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PacienteServicioImpl implements PacienteServicio {

    private final PacienteRepository pacienteRepository;
    private final EpsRepository epsRepository;
    private final PqrsRepository pqrsRepository;
    private final CitaRepository citaRepository;
    private final RespuestaPacienteRepository respuestaPacienteRepository;
    private final RespuestaAdminRepository respuestaAdminRepository;
    private final ConsultaRepository consultaRepository;
    private final MedicoRepository medicoRepository;
    private final DiaLibreRepository diaLibreRepository;
    private final HorarioRepository horarioRepository;

    @Override
    public int registrarse(RegistroPacienteDTO pacienteDTO) throws Exception {

        if (estaRepetidoCorreo(pacienteDTO.email())) {
            throw new Exception("El correo ya está en uso");
        }
        if (estaRepetidaCedula(pacienteDTO.cedula())) {
            throw new Exception("La cedula ya se encuentra registrada");
        }

        Paciente pacienteNuevo = new Paciente();

        pacienteNuevo.setTelefono(pacienteDTO.telefono());
        pacienteNuevo.setNombreCompleto(pacienteDTO.nombre());
        pacienteNuevo.setCedula(pacienteDTO.cedula());
        pacienteNuevo.setFoto(pacienteDTO.foto());
        pacienteNuevo.setEps(buscarEps(pacienteDTO.eps()));
        pacienteNuevo.setAlergias(pacienteDTO.alergias());
        pacienteNuevo.setFechaNacimiento(pacienteDTO.fechaNacimiento());
        pacienteNuevo.setTipoSangre(pacienteDTO.tipoSangre());
        pacienteNuevo.setCiudad(pacienteDTO.ciudad());

        pacienteNuevo.setContrasenia(pacienteDTO.password());
        pacienteNuevo.setEmail(pacienteDTO.email());

        Paciente pacienteRegistrado = pacienteRepository.save(pacienteNuevo);

        return pacienteRegistrado.getId();
    }

    private Eps buscarEps(int eps) {
        return epsRepository.buscarEps(eps);
    }

    private boolean estaRepetidoCorreo(String email) {
        return pacienteRepository.buscarPorCorreo(email) == null;
    }

    private boolean estaRepetidaCedula(String cedula) {
        return pacienteRepository.buscarPorCedula(cedula) == null;
    }

    @Override
    public ActualizarPacienteDTO cargarDatosPaciente(int codigoPaciente) throws Exception {

        Optional<Paciente> opcional = pacienteRepository.findById(codigoPaciente);

        if (opcional.isEmpty()) {
            throw new Exception("No existe un paciente con el codigo: " + codigoPaciente);
        }

        Paciente buscado = opcional.get();

        return new ActualizarPacienteDTO(
                buscado.getNombreCompleto(),
                buscado.getCedula(),
                buscado.getTelefono(),
                buscado.getEmail(),
                buscado.getFoto(),
                buscado.getFechaNacimiento(),
                buscado.getCiudad(),
                buscado.getEps().getId(),
                buscado.getTipoSangre(),
                buscado.getAlergias()
        );
    }

    @Override
    public int editarPerfil(int codigoPaciente, ActualizarPacienteDTO pacienteDTO) throws Exception {

        Optional<Paciente> opcional = pacienteRepository.findById(codigoPaciente);

        if (opcional.isEmpty()) {
            throw new Exception("No existe un paciente con el codigo: " + codigoPaciente);
        }

        Paciente buscado = opcional.get();

        if (estaRepetidoCorreo(buscado.getEmail())) {
            throw new Exception("El correo ya está en uso");
        }
        if (estaRepetidaCedula(buscado.getCedula())) {
            throw new Exception("La cedula ya se encuentra registrada");
        }

        buscado.setTelefono(pacienteDTO.telefono());
        buscado.setNombreCompleto(pacienteDTO.nombre());
        buscado.setCedula(pacienteDTO.cedula());
        buscado.setFoto(pacienteDTO.foto());
        buscado.setEps(buscarEps(pacienteDTO.eps()));
        buscado.setAlergias(pacienteDTO.alergias());
        buscado.setFechaNacimiento(pacienteDTO.fechaNacimiento());
        buscado.setTipoSangre(pacienteDTO.tipoSangre());
        buscado.setCiudad(pacienteDTO.ciudad());

        buscado.setEmail(pacienteDTO.email());

        pacienteRepository.save(buscado);

        return buscado.getId();
    }

    @Override
    public void eliminarCuenta(int idPaciente) throws Exception {

        Optional<Paciente> opcional = pacienteRepository.findById(idPaciente);

        if (opcional.isEmpty()) {
            throw new Exception("No existe un paciente con el codigo: " + idPaciente);
        }

        Paciente buscado = opcional.get();

        buscado.setEstado(false);

        pacienteRepository.save(buscado);
    }

    @Override
    public List<ItemMedicoCitaDTO> filtrarMedicoCita(Especialidad especialidad, LocalDate fecha) throws Exception {
        List<Medico> medicosEspecializados = medicoRepository.obtenerMedicoEspecialidad(especialidad);

        //____________________________Vemos qué día se quiere la cita____________________________
        //Obtenemos el día de la semana
        DayOfWeek diaDeLaSemana = fecha.getDayOfWeek();
        //Obtenemos el nombre en español
        String nombreDia = diaDeLaSemana.getDisplayName(
                java.time.format.TextStyle.FULL, // Estilo de texto completo (por ejemplo, "lunes" en español)
                Locale.getDefault() // Localización predeterminada para el idioma
        );
        //Obtenemos el enumerable
        Dia dia = Dia.valueOf(nombreDia.toUpperCase());
        //________________________________________________________________________________________

        //____________________________Filtrar los que no tienen libre ese día_____________________
        List<Medico> medicosDisponibles = new ArrayList<>();
        //Validamos que el médico no tenga día libre ese día
        for (Medico medico : medicosEspecializados) {
            if (diaLibreRepository.obtenerDiaLibreFecha(medico.getId(), fecha) == null) {
                //Si no tiene día libre en esa fecha lo agregamos a los posibles disponibles
                medicosDisponibles.add(medico);
            }
        }
        //________________________________________________________________________________________

        //________Obtenemos todos los horarios posibles en los que se pueda agendar una cita______
        List<ItemMedicoCitaDTO> listaItemMedicoCitaDTOS = new ArrayList<>();

        for (Medico medico : medicosDisponibles) {
            Horario horarioMedico = horarioRepository.obtenerHorarioFecha(medico.getId(), dia);
            List<Cita> citasPendientes = citaRepository.obtenerCitasFecha(medico.getId(), fecha);

            //Empezamos con la primera hora de trabajo del medico
            LocalTime horaInicioCita = horarioMedico.getHoraInicio();
            LocalTime finJornada = horarioMedico.getHoraFin();

            /*
            * Mientras que el posible inicio de la cita sea diferente al fin de la jornada
            * Se evalua como posible inicio de una nueva cita
            * */
            while (horaInicioCita != finJornada){

                boolean sePuedeAgendar = true;
                //Validamos que ninguna cita cumpla con esa hora
                for (Cita cita : citasPendientes) {
                    if(horaInicioCita == cita.getHora()){
                        sePuedeAgendar = false;
                        break;
                    }
                }
                if(sePuedeAgendar){
                    listaItemMedicoCitaDTOS.add(new ItemMedicoCitaDTO(medico.getId(), medico.getNombreCompleto(), horaInicioCita));
                }
                //Sumamos 30 minutos que es la duración de una cita
                horaInicioCita = horaInicioCita.plusMinutes(30);
            }
        }
        //________________________________________________________________________________________
        return listaItemMedicoCitaDTOS;
    }

    @Override
    public int agendarCita(CitaDTO citaDTO) throws Exception {

        Cita citaNueva = new Cita();

        Optional<Medico> medico = medicoRepository.findById(citaDTO.idMedico());
        Optional<Paciente> paciente = pacienteRepository.findById(citaDTO.idPaciente());

        if(medico.isEmpty()){
            throw new Exception("No existe ese medico");
        }

        if(paciente.isEmpty()){
            throw new Exception("No existe ese paciente");
        }

        citaNueva.setMotivo(citaDTO.motivo());
        citaNueva.setFecha(citaDTO.fecha());
        citaNueva.setHora(citaDTO.hora());
        citaNueva.setMedico(medico.get());
        citaNueva.setPaciente(paciente.get());

        Cita citaRegistrada = citaRepository.save(citaNueva);

        return citaRegistrada.getId();
    }

    @Override
    public int crearPqrs(PQRSPacienteDTO pqrsPacienteDTO) throws Exception {

        Optional<Cita> opcional = citaRepository.findById(pqrsPacienteDTO.codigoCita());

        if (opcional.isEmpty()) {
            throw new Exception("No existe esa cita");
        }

        Pqrs pqrsNuevo = new Pqrs();
        Cita buscado = opcional.get();

        pqrsNuevo.setEstadoPqrs(EstadoPqrs.NUEVO);
        pqrsNuevo.setFechaCreacion(LocalDateTime.now());
        pqrsNuevo.setDetalle(pqrsPacienteDTO.motivo());
        pqrsNuevo.setCita(buscado);

        Pqrs pqrsRegistrada = pqrsRepository.save(pqrsNuevo);

        return pqrsRegistrada.getNumeroRadicado();
    }

    @Override
    public List<ItemPqrsDTO> listarPqrsPaciente(int idPaciente) throws Exception {

        List<Pqrs> pqrsPaciente = pqrsRepository.findAllByPaciente_Id(idPaciente);

        List<ItemPqrsDTO> listaItemPqrsDTO = new ArrayList<>();

        for(Pqrs pqrs : pqrsPaciente ){
            listaItemPqrsDTO.add(new ItemPqrsDTO(pqrs.getNumeroRadicado(), pqrs.getDetalle(),
                    pqrs.getFechaCreacion(), pqrs.getEstadoPqrs()));
        }

        return listaItemPqrsDTO;
    }

    @Override
    public int responderPqrs(RespuestaPacientePqrsDTO respuestaPqrsDTO) throws Exception {

        Optional<Pqrs> opcionalPqrs = pqrsRepository.findById(respuestaPqrsDTO.codigoPqrs());

        if (opcionalPqrs.isEmpty()) {
            throw new Exception("No existe esa pqrs");
        }
        Optional<RespuestaAdmin> respuestaAdmin = respuestaAdminRepository.findById(respuestaPqrsDTO.respuestaAdmin());
        RespuestaAdmin buscadoRespuesta = respuestaAdmin.get();

        if (respuestaAdmin.isEmpty()) {
            throw new Exception("No existe una respuesta con ese codigo");
        }

        Optional<Paciente> paciente = pacienteRepository.findById(respuestaPqrsDTO.codigoPaciente());
        Paciente buscadoPaciente = paciente.get();

        if (paciente.isEmpty()) {
            throw new Exception("No existe el paciente");
        }

        RespuestaPaciente respuestaPacienteNuevo = new RespuestaPaciente();

        respuestaPacienteNuevo.setRespuestaAdmin(buscadoRespuesta);
        respuestaPacienteNuevo.setFecha(LocalDateTime.now());
        respuestaPacienteNuevo.setPqrs(opcionalPqrs.get());
        respuestaPacienteNuevo.setMensaje(respuestaPqrsDTO.mensaje());
        respuestaPacienteNuevo.setPaciente(buscadoPaciente);

        RespuestaPaciente respuestaPacienteRegistrada = respuestaPacienteRepository.save(respuestaPacienteNuevo);
        return respuestaPacienteRegistrada.getId();
    }

    @Override
    public List<ItemCitaPacienteDTO> listarCitasPaciente(int codigoPaciente) throws Exception {

        List<Cita> citasPaciente = citaRepository.findAllByPaciente_Id(codigoPaciente);
        List<ItemCitaPacienteDTO> respuesta = new ArrayList<>();

        for(Cita cita: citasPaciente ){
            respuesta.add(new ItemCitaPacienteDTO(cita.getMotivo(),
                    cita.getFechaCreacion(), cita.getFecha(),
                    cita.getHora(), cita.getEstadoCita(),
                    cita.getMedico().getNombreCompleto()));
        }

        return respuesta;
    }

    @Override
    public List<ItemCitaPqrsPacienteDTO> listarCitasPqrsPaciente(int idPaciente) throws Exception {

        List<Cita> citas = citaRepository.findAllByPaciente_Id(idPaciente);
        List<ItemCitaPqrsPacienteDTO> respuesta = new ArrayList<>();

        for (Cita cita : citas) {
            respuesta.add(new ItemCitaPqrsPacienteDTO(cita.getId(), cita.getFecha()));
        }

        return respuesta;
    }

    @Override
    public List<ItemConsultaPacienteDTO> buscarConsulta(String nombreMedico, LocalDate fecha, int idPaciente) throws Exception {

        return null;
    }
}
