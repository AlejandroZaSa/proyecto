package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.dto.*;

import java.time.LocalDate;
import java.util.List;

public interface PacienteServicio {

    String registrarse(RegistroPacienteDTO pacienteDTO) throws Exception;

    ActualizarPacienteDTO cargarDatosPaciente(int codigoPaciente);

    String editarPerfil(int codigo, ActualizarPacienteDTO pacienteDTO) throws Exception;

    String eliminarCuenta(int id) throws Exception;

    String enviarLinkRecuperacion(String email) throws Exception;

    String cambiarPassword(int codigoUsuario, String nuevaPassword) throws Exception;

    List<MedicoCitaDTO> filtrarMedicoCita(int especialidad, LocalDate fecha) throws Exception;

    String agendarCita(CitaDTO citaDTO) throws Exception;

    String crearPqrs(PQRSPacienteDTO pqrsPacienteDTO);

    List<ItemPqrsPacienteDTO> listarPqrsPaciente(int idPaciente) throws Exception;

    String responderPqrs(RespuestaPacientePqrsDTO respuestaPqrsDTO);

    List<CitasPacienteDTO> listarCitasPaciente(int idPaciente) throws Exception;

    List<CitasPacienteDTO> buscarCita(int nombreMedico, LocalDate fecha) throws Exception;

    List<TratamientoDTO> verTratamiento(int codigoConsulta);
}
