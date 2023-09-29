package co.edu.uniquindio.proyecto.servicios.implementacion;

import co.edu.uniquindio.proyecto.dto.admin.ActualizarMedicoDTO;
import co.edu.uniquindio.proyecto.dto.admin.HorarioDTO;
import co.edu.uniquindio.proyecto.dto.clinica.ItemPqrsDTO;
import co.edu.uniquindio.proyecto.dto.paciente.*;
import co.edu.uniquindio.proyecto.modelo.entidades.*;
import co.edu.uniquindio.proyecto.modelo.enums.EstadoPqrs;
import co.edu.uniquindio.proyecto.repositorios.*;
import co.edu.uniquindio.proyecto.servicios.interfaces.PacienteServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    @Override
    public int registrarse(RegistroPacienteDTO pacienteDTO) throws Exception {

        if(estaRepetidoCorreo(pacienteDTO.email())){
            throw new Exception ("El correo ya está en uso");
        }
        if(estaRepetidaCedula(pacienteDTO.cedula())){
            throw new Exception ("La cedula ya se encuentra registrada");
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
    public ActualizarPacienteDTO cargarDatosPaciente(int codigoPaciente) throws Exception{

        Optional<Paciente> opcional = pacienteRepository.findById(codigoPaciente);

        if(opcional.isEmpty()){
            throw new Exception("No existe un paciente con el codigo: "+ codigoPaciente);
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

        if(opcional.isEmpty()){
            throw new Exception("No existe un paciente con el codigo: "+ codigoPaciente);
        }

        Paciente buscado = opcional.get();

        if(estaRepetidoCorreo(buscado.getEmail())){
            throw new Exception ("El correo ya está en uso");
        }
        if(estaRepetidaCedula(buscado.getCedula())){
            throw new Exception ("La cedula ya se encuentra registrada");
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

        if(opcional.isEmpty()){
            throw new Exception("No existe un paciente con el codigo: "+ idPaciente);
        }

        Paciente buscado = opcional.get();

        buscado.setEstado(false);

        pacienteRepository.save(buscado);
    }

    @Override
    public List<ItemMedicoCitaDTO> filtrarMedicoCita(int especialidad, LocalDate fecha) throws Exception {


        return null;
    }

    @Override
    public int agendarCita(CitaDTO citaDTO) throws Exception {
        return 0;
    }

    @Override
    public int crearPqrs(PQRSPacienteDTO pqrsPacienteDTO) throws Exception{

        Optional<Cita> opcional = citaRepository.findById(pqrsPacienteDTO.codigoCita());

        if(opcional.isEmpty()){
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

        return null;
    }

    @Override
    public int responderPqrs(RespuestaPacientePqrsDTO respuestaPqrsDTO) throws Exception {

        Optional<Pqrs> opcionalPqrs = pqrsRepository.findById(respuestaPqrsDTO.codigoPqrs());

        if(opcionalPqrs.isEmpty()){
            throw new Exception("No existe esa pqrs");
        }
        Optional<RespuestaAdmin> respuestaAdmin = respuestaAdminRepository.findById(respuestaPqrsDTO.respuestaAdmin());
        RespuestaAdmin buscadoRespuesta = respuestaAdmin.get();

        if(respuestaAdmin.isEmpty()){
            throw new Exception("No existe una respuesta con ese codigo");
        }

        Optional<Paciente> paciente = pacienteRepository.findById(respuestaPqrsDTO.codigoPaciente());
        Paciente buscadoPaciente = paciente.get();

        if(paciente.isEmpty()){
            throw new Exception("No existe el paciente");
        }


        RespuestaPaciente respuestaPacienteNuevo = new RespuestaPaciente();


        respuestaPacienteNuevo.setRespuestaAdmin(buscadoRespuesta);
        respuestaPacienteNuevo.setFecha(LocalDateTime.now());
        respuestaPacienteNuevo.setPqrs(opcionalPqrs.get());
        respuestaPacienteNuevo.setMensaje(respuestaPqrsDTO.mensaje());
        respuestaPacienteNuevo.setPaciente(buscadoPaciente);

        RespuestaPaciente respuestaPacienteRegistrada =respuestaPacienteRepository.save(respuestaPacienteNuevo);
        return respuestaPacienteRegistrada.getId();
    }

    @Override
    public List<ItemCitaPacienteDTO> listarCitasPaciente(int codigoPaciente) throws Exception {

        List<Consulta> consulta = null;
        List<ItemCitaPacienteDTO> respuesta = new ArrayList<>();

        return respuesta;
    }

    @Override
    public List<ItemCitaPqrsPacienteDTO> listarCitasPqrsPaciente(int idPaciente) throws Exception {

        List<Cita> citas = citaRepository.findAllByPaciente_Id(idPaciente);
        List<ItemCitaPqrsPacienteDTO> respuesta = new ArrayList<>();

        for(Cita cita: citas){
            respuesta.add(new ItemCitaPqrsPacienteDTO(cita.getId(),cita.getFecha()));
        }

        return respuesta;
    }

    @Override
    public List<ItemConsultaPacienteDTO> buscarConsulta(String nombreMedico, LocalDate fecha, int idPaciente) throws Exception {


        return null;
    }
}
