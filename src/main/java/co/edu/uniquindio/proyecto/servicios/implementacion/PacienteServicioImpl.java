package co.edu.uniquindio.proyecto.servicios.implementacion;

import co.edu.uniquindio.proyecto.dto.clinica.EmailDTO;
import co.edu.uniquindio.proyecto.dto.clinica.ItemPqrsDTO;
import co.edu.uniquindio.proyecto.dto.paciente.*;
import co.edu.uniquindio.proyecto.modelo.entidades.*;
import co.edu.uniquindio.proyecto.modelo.enums.Dia;
import co.edu.uniquindio.proyecto.modelo.enums.EstadoCita;
import co.edu.uniquindio.proyecto.modelo.enums.EstadoPqrs;
import co.edu.uniquindio.proyecto.repositorios.*;
import co.edu.uniquindio.proyecto.servicios.interfaces.EmailServicio;
import co.edu.uniquindio.proyecto.servicios.interfaces.PacienteServicio;
import co.edu.uniquindio.proyecto.utils.JWTUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.ArrayList;
import java.util.List;
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
    private final EmailServicio emailServicio;
    private final AdminRepository adminRepository;
    private final JWTUtils jwtUtils;

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
        pacienteNuevo.setEstado(true);

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String passwordEncriptada = passwordEncoder.encode( pacienteDTO.password() );

        pacienteNuevo.setContrasenia(passwordEncriptada);
        pacienteNuevo.setEmail(pacienteDTO.email());

        Paciente pacienteRegistrado = pacienteRepository.save(pacienteNuevo);

        return pacienteRegistrado.getId();
    }

    private Eps buscarEps(int eps) {
        return epsRepository.buscarEps(eps);
    }

    private boolean estaRepetidoCorreo(String email) {
        return medicoRepository.buscarPorCorreo(email) != null || adminRepository.findByEmail(email) != null || pacienteRepository.buscarPorCorreo(email) != null;

    }

    private boolean estaRepetidaCedula(String cedula) {
        return pacienteRepository.buscarPorCedula(cedula) != null;
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
                buscado.getAlergias(),
                buscado.isEstado()
        );
    }

    @Override
    public int editarPerfil(int codigoPaciente, ActualizarPacienteDTO pacienteDTO) throws Exception {

        Optional<Paciente> opcional = pacienteRepository.findById(codigoPaciente);

        if (opcional.isEmpty()) {
            throw new Exception("No existe un paciente con el codigo: " + codigoPaciente);
        }

        Paciente buscado = opcional.get();

        if (estaRepetidoCorreo(pacienteDTO.email()) && (!buscado.getEmail().equals(pacienteDTO.email()))) {
            throw new Exception("El correo ya está en uso");
        }
        if (estaRepetidaCedula(pacienteDTO.cedula()) && (!buscado.getCedula().equals(pacienteDTO.cedula()))) {
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
    public List<ItemMedicoCitaDTO> filtrarMedicoCita(FiltroCitaDTO citaDTO) throws Exception {

        if(citaDTO.fecha().isBefore(LocalDate.now())){
            throw new Exception("La fecha es anterior a hoy");
        }

        //Filtramos los médicos especializados y que estén activos

        List<Medico> medicosEspecializados = medicoRepository.obtenerMedicoEspecialidad(citaDTO.especialidad());

        if (medicosEspecializados.isEmpty()) {
            throw new Exception("No hay médicos con la Especialidad "+ citaDTO.especialidad());
        }

        //____________________________Vemos para qué día se quiere la cita____________________________
        //Obtenemos el día de la semana
        DayOfWeek diaDeLaSemana = citaDTO.fecha().getDayOfWeek();

        int numeroDia = diaDeLaSemana.getValue() - 1;
        //Obtenemos el enumerable
        Dia dia = Dia.values()[numeroDia];
        //________________________________________________________________________________________

        //____________________________Filtrar los que no tienen libre ese día_____________________
        List<Medico> medicosDisponibles = new ArrayList<>();
        //Validamos que el médico no tenga día libre ese día
        for (Medico medico : medicosEspecializados) {
            if (diaLibreRepository.obtenerDiaLibreFecha(medico.getId(), citaDTO.fecha()) == null) {
                //Si no tiene día libre en esa fecha lo agregamos a los posibles disponibles
                medicosDisponibles.add(medico);
            }
        }
        //________________________________________________________________________________________

        //________Obtenemos todos los horarios posibles en los que se pueda agendar una cita______
        List<ItemMedicoCitaDTO> listaItemMedicoCitaDTOS = new ArrayList<>();

        for (Medico medico : medicosDisponibles) {
            Horario horarioMedico = horarioRepository.obtenerHorarioFecha(medico.getId(), dia);

            if (horarioMedico != null) {

                List<Cita> citasPendientes = citaRepository.obtenerCitasFecha(medico.getId(), citaDTO.fecha());

                //Empezamos con la primera hora de trabajo del medico
                LocalTime horaInicioCita = horarioMedico.getHoraInicio();
                LocalTime finJornada = horarioMedico.getHoraFin();

                /*
                 * Mientras que el posible inicio de la cita sea diferente al fin de la jornada
                 * Se evalua como posible inicio de una nueva cita
                 * */

                while (!horaInicioCita.equals(finJornada) && horaInicioCita.isBefore(finJornada) && verificarHoras(horaInicioCita,finJornada)) {

                    boolean sePuedeAgendar = true;
                    //Validamos que ninguna cita cumpla con esa hora
                    for (Cita cita : citasPendientes) {
                        if (horaInicioCita.equals(cita.getHora())) {
                            sePuedeAgendar = false;
                            break;
                        }
                    }
                    if (sePuedeAgendar) {
                        listaItemMedicoCitaDTOS.add(new ItemMedicoCitaDTO(medico.getId(), medico.getNombreCompleto(), horaInicioCita));
                    }
                    //Sumamos 30 minutos que es la duración de una cita
                    horaInicioCita = horaInicioCita.plusMinutes(30);
                }

            }

        }
        //________________________________________________________________________________________
        if(listaItemMedicoCitaDTOS.isEmpty()){
            throw new Exception("No hay disponibilidad de médicos, inténtalo más tarde");
        }

        return listaItemMedicoCitaDTOS;
    }

    private boolean verificarHoras(LocalTime horaInicioCita, LocalTime finJornada) {

        Duration diferencia = Duration.between(finJornada, horaInicioCita);

        // Obtener la diferencia en minutos
        long minutosDiferencia = Math.abs(diferencia.toMinutes());

        if(minutosDiferencia>=30){
            return true;
        }

        return false;
    }

    @Override
    public int agendarCita(CitaDTO citaDTO) throws Exception {

        Optional<Medico> medico = medicoRepository.findById(citaDTO.idMedico());

        if (medico.isEmpty()) {
            throw new Exception("No existe el medico con el código " + citaDTO.idMedico());
        }

        Optional<Paciente> paciente = pacienteRepository.findById(citaDTO.idPaciente());

        if (paciente.isEmpty()) {
            throw new Exception("No existe el  paciente con código " + citaDTO.idPaciente());
        }

        long numeroCitasActivas = citaRepository.countAllByPacienteIdAndEstadoCita(citaDTO.idPaciente(), EstadoCita.PROGRAMADA);

        if (numeroCitasActivas >= 3) {
            throw new Exception("El numero de citas ha programar, máximo pueden ser 3");
        }

        Cita citaNueva = new Cita();

        citaNueva.setMotivo(citaDTO.motivo());
        citaNueva.setFechaCreacion(LocalDateTime.now());
        citaNueva.setFecha(citaDTO.fecha());
        citaNueva.setHora(citaDTO.hora());
        citaNueva.setMedico(medico.get());
        citaNueva.setPaciente(paciente.get());
        citaNueva.setEstadoCita(EstadoCita.PROGRAMADA);

        Cita citaRegistrada = citaRepository.save(citaNueva);

        emailServicio.enviarEmail(new EmailDTO("Agendamiento de Cita", paciente.get().getEmail(), "Haz agendado una cita con fecha " +
                citaRegistrada.getFecha() + " y hora " + citaRegistrada.getHora() + " Motivo: "
                + citaRegistrada.getMotivo() + " con el especialista en " + citaRegistrada.getMedico().getEspecialidad()
                + " " + citaRegistrada.getMedico().getNombreCompleto()));

        emailServicio.enviarEmail(new EmailDTO("Agendamiento de Cita", medico.get().getEmail(), "Se ha agendado una cita con fecha " +
                citaRegistrada.getFecha() + " y hora " + citaRegistrada.getHora() + " Motivo: "
                + citaRegistrada.getMotivo()+ " Nombre del Paciente: "+ paciente.get().getNombreCompleto()));

        return citaRegistrada.getId();
    }

    @Override
    public int crearPqrs(PQRSPacienteDTO pqrsPacienteDTO) throws Exception {

        List<Administrador> administradorList = adminRepository.findAll();

        if (administradorList.isEmpty()) {
            throw new Exception("No hay administradores");
        }

        Optional<Cita> opcional = citaRepository.findById(pqrsPacienteDTO.codigoCita());

        if (opcional.isEmpty()) {
            throw new Exception("No existe la cita con el código " + pqrsPacienteDTO.codigoCita());
        }

        List<Pqrs> pqrsList = pqrsRepository.findAllByCita_Paciente_IdAndEstadoPqrsOrEstadoPqrs(opcional.get().getPaciente().getId(), EstadoPqrs.NUEVO, EstadoPqrs.EN_PROCESO);

        if (pqrsList.size() >= 3) {
            throw new Exception("No se pueden crear más pqrs, máximo puedes tener 3 activas");
        }

        Pqrs pqrsNuevo = new Pqrs();
        Cita buscado = opcional.get();

        pqrsNuevo.setEstadoPqrs(EstadoPqrs.NUEVO);
        pqrsNuevo.setFechaCreacion(LocalDateTime.now());
        pqrsNuevo.setDetalle(pqrsPacienteDTO.motivo());
        pqrsNuevo.setCita(buscado);

        Pqrs pqrsRegistrada = pqrsRepository.save(pqrsNuevo);
        for (Administrador admin : administradorList) {
            emailServicio.enviarEmail(new EmailDTO("Creación de PQRS", admin.getEmail(), pqrsRegistrada.getDetalle()));
        }
        return pqrsRegistrada.getNumeroRadicado();
    }

    @Override
    public List<ItemPqrsDTO> listarPqrsPaciente(int idPaciente) throws Exception {

        List<Pqrs> pqrsPaciente = pqrsRepository.findAllByCita_Paciente_Id(idPaciente);

        if (pqrsPaciente.isEmpty()) {
            throw new Exception("No tienes pqrs registradas");
        }

        List<ItemPqrsDTO> listaItemPqrsDTO = new ArrayList<>();

        for (Pqrs pqrs : pqrsPaciente) {
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

        if (respuestaAdmin.isEmpty()) {
            throw new Exception("No existe una respuesta con ese codigo");
        }

        //Esta variable almacena el numero de veces que aparece la respuesta del admin en una
        //respuesta paciente si es 1 no se puede responder, si es 0 si
        long numRespuestas = respuestaPacienteRepository.contarRespuestas(respuestaAdmin.get().getId());

        if(numRespuestas!=0){
            throw new Exception("No puedes responder más de una vez, debes esperar a que el admin te conteste");
        }

        Optional<Paciente> paciente = pacienteRepository.findById(respuestaPqrsDTO.codigoPaciente());

        if (paciente.isEmpty()) {
            throw new Exception("No existe el paciente");
        }

        if (opcionalPqrs.get().getEstadoPqrs().equals(EstadoPqrs.EN_PROCESO)) {
            Paciente buscadoPaciente = paciente.get();
            RespuestaAdmin buscadoRespuesta = respuestaAdmin.get();
            RespuestaPaciente respuestaPacienteNuevo = new RespuestaPaciente();

            respuestaPacienteNuevo.setRespuestaAdmin(buscadoRespuesta);
            respuestaPacienteNuevo.setFecha(LocalDateTime.now());
            respuestaPacienteNuevo.setPqrs(opcionalPqrs.get());
            respuestaPacienteNuevo.setMensaje(respuestaPqrsDTO.mensaje());
            respuestaPacienteNuevo.setPaciente(buscadoPaciente);

            RespuestaPaciente respuestaPacienteRegistrada = respuestaPacienteRepository.save(respuestaPacienteNuevo);

            emailServicio.enviarEmail(new EmailDTO("Respuesta Paciente", buscadoRespuesta.getAdministrador().getEmail(), respuestaPacienteRegistrada.getMensaje()));

            return respuestaPacienteRegistrada.getId();
        } else {
            throw new Exception("No se puede responder a la pqrs porque su estado es "+ opcionalPqrs.get().getEstadoPqrs());
        }
    }

    @Override
    public List<ItemCitaPacienteDTO> listarCitasPaciente(int codigoPaciente) throws Exception {

        List<Cita> citasPaciente = citaRepository.findAllByPaciente_Id(codigoPaciente);

        if (citasPaciente.isEmpty()) {
            throw new Exception("No ha citas");
        }

        List<ItemCitaPacienteDTO> respuesta = new ArrayList<>();

        for (Cita cita : citasPaciente) {
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

        if (citas.isEmpty()) {
            throw new Exception("No tienes citas");
        }

        List<ItemCitaPqrsPacienteDTO> respuesta = new ArrayList<>();

        for (Cita cita : citas) {
            respuesta.add(new ItemCitaPqrsPacienteDTO(cita.getId(), cita.getFecha()));
        }

        return respuesta;
    }

    @Override
    public List<ItemConsultaPacienteDTO> buscarConsulta(BusquedaConsultaDTO busquedaConsultaDTO) throws Exception {

        List<Consulta> consultas = new ArrayList<>();


        if((busquedaConsultaDTO.nombreMedico().isEmpty() && busquedaConsultaDTO.fecha()!=null) || (!busquedaConsultaDTO.nombreMedico().isEmpty() && busquedaConsultaDTO.fecha()==null)){
            consultas = consultaRepository.buscarConsulta(busquedaConsultaDTO.nombreMedico(), busquedaConsultaDTO.fecha(), busquedaConsultaDTO.idPaciente());

        }else if(!busquedaConsultaDTO.nombreMedico().isEmpty() && busquedaConsultaDTO.fecha()!=null) {
            consultas = consultaRepository.buscarConsulta2(busquedaConsultaDTO.nombreMedico(), busquedaConsultaDTO.fecha(), busquedaConsultaDTO.idPaciente());

        }else{
            throw new Exception("Selecciona el nombre del médico o una fecha");
        }

        if(consultas.isEmpty()){
            throw new Exception("No tienes consultas con esos atributos de busqueda");
        }

        List<ItemConsultaPacienteDTO> consultaPacienteDTOS = new ArrayList<>();

        for (Consulta consulta : consultas) {
            consultaPacienteDTOS.add(new ItemConsultaPacienteDTO(consulta.getId(),
                    consulta.getFecha(), consulta.getCita().getMedico().getNombreCompleto(),
                    consulta.getDiagnostico(), consulta.getNotasMedico(), consulta.getSintomas()));
        }

        return consultaPacienteDTOS;
    }

    @Override
    public List<ItemConsultaPacienteDTO> listarConsultasPaciente(int codigoPaciente) throws Exception {

        List<Consulta> consultas;

        consultas = consultaRepository.findAllByPaciente_Id(codigoPaciente);

        if(consultas.isEmpty()){
            throw new Exception("No tienes consultas");
        }

        List<ItemConsultaPacienteDTO> consultaPacienteDTOS = new ArrayList<>();

        for (Consulta consulta : consultas) {
            consultaPacienteDTOS.add(new ItemConsultaPacienteDTO(consulta.getId(),
                    consulta.getFecha(), consulta.getCita().getMedico().getNombreCompleto(),
                    consulta.getDiagnostico(), consulta.getNotasMedico(), consulta.getSintomas()));
        }

        return consultaPacienteDTOS;
    }
}
