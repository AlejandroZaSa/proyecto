package co.edu.uniquindio.proyecto.servicios.implementacion;

import co.edu.uniquindio.proyecto.dto.clinica.ItemPqrsDTO;
import co.edu.uniquindio.proyecto.dto.paciente.*;
import co.edu.uniquindio.proyecto.modelo.entidades.Eps;
import co.edu.uniquindio.proyecto.modelo.entidades.Paciente;
import co.edu.uniquindio.proyecto.repositorios.EpsRepository;
import co.edu.uniquindio.proyecto.repositorios.PacienteRepository;
import co.edu.uniquindio.proyecto.servicios.interfaces.PacienteServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PacienteServicioImpl implements PacienteServicio {

    private final PacienteRepository pacienteRepository;
    private final EpsRepository epsRepository;
    @Override
    public int registrarse(RegistroPacienteDTO pacienteDTO) throws Exception {

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

        if(estaRepetidoCorreo(pacienteDTO.email())){
            throw new Exception ("El correo ya está en uso");
        }
        if(estaRepetidaCedula(pacienteDTO.cedula())){
            throw new Exception ("La cedula ya se encuentra registrada");
        }

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
    public ActualizarPacienteDTO cargarDatosPaciente(int codigoPaciente) {
        return null;
    }

    @Override
    public int editarPerfil(int codigo, ActualizarPacienteDTO pacienteDTO) throws Exception {
        return 0;
    }

    @Override
    public void eliminarCuenta(int id) throws Exception {

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
    public int crearPqrs(PQRSPacienteDTO pqrsPacienteDTO) {
        return 0;
    }

    @Override
    public List<ItemPqrsDTO> listarPqrsPaciente(int idPaciente) throws Exception {
        return null;
    }

    @Override
    public int responderPqrs(RespuestaPacientePqrsDTO respuestaPqrsDTO) throws Exception {
        return 0;
    }

    @Override
    public List<ItemCitaPacienteDTO> listarCitasPaciente(int codigoPaciente) throws Exception {
        return null;
    }

    @Override
    public List<ItemCitaPqrsPacienteDTO> listarCitasPqrsPaciente(int idPaciente) throws Exception {
        return null;
    }

    @Override
    public List<ItemConsultaPacienteDTO> buscarConsulta(String nombreMedico, LocalDate fecha, int idPaciente) throws Exception {
        return null;
    }
}
