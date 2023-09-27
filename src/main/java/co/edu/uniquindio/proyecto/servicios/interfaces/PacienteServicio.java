package co.edu.uniquindio.proyecto.servicios.interfaces;

import co.edu.uniquindio.proyecto.dto.clinica.ItemPqrsDTO;
import co.edu.uniquindio.proyecto.dto.paciente.*;

import java.time.LocalDate;
import java.util.List;

public interface PacienteServicio {

    int registrarse(RegistroPacienteDTO pacienteDTO) throws Exception;

    ActualizarPacienteDTO cargarDatosPaciente(int codigoPaciente);

    int editarPerfil(int codigo, ActualizarPacienteDTO pacienteDTO) throws Exception;

    void eliminarCuenta(int id) throws Exception;

    List<ItemMedicoCitaDTO> filtrarMedicoCita(int especialidad, LocalDate fecha) throws Exception;

    int agendarCita(CitaDTO citaDTO) throws Exception;

    int crearPqrs(PQRSPacienteDTO pqrsPacienteDTO);

    List<ItemPqrsDTO> listarPqrsPaciente(int idPaciente) throws Exception;

    int responderPqrs(RespuestaPacientePqrsDTO respuestaPqrsDTO) throws Exception;

    List<ItemCitaPacienteDTO> listarCitasPaciente(int codigoPaciente) throws Exception;

    List<ItemCitaPqrsPacienteDTO> listarCitasPqrsPaciente(int idPaciente) throws Exception;

    List<ItemConsultaPacienteDTO> buscarConsulta(String nombreMedico, LocalDate fecha, int idPaciente) throws Exception;
}
