package co.edu.uniquindio.proyecto.servicios.interfaces;

import co.edu.uniquindio.proyecto.dto.clinica.ItemPqrsDTO;
import co.edu.uniquindio.proyecto.dto.paciente.*;
import co.edu.uniquindio.proyecto.modelo.enums.Especialidad;

import java.time.LocalDate;
import java.util.List;

public interface PacienteServicio {

    int registrarse(RegistroPacienteDTO pacienteDTO) throws Exception;

    ActualizarPacienteDTO cargarDatosPaciente(int codigoPaciente) throws Exception;

    int editarPerfil(int codigo, ActualizarPacienteDTO pacienteDTO) throws Exception;

    void eliminarCuenta(int id) throws Exception;

    List<ItemMedicoCitaDTO> filtrarMedicoCita(FiltroCitaDTO citaDTO) throws Exception;

    int agendarCita(CitaDTO citaDTO) throws Exception;

    int crearPqrs(String autenticacion, PQRSPacienteDTO pqrsPacienteDTO) throws Exception;

    List<ItemPqrsDTO> listarPqrsPaciente(int idPaciente) throws Exception;

    int responderPqrs(RespuestaPacientePqrsDTO respuestaPqrsDTO) throws Exception;

    List<ItemCitaPacienteDTO> listarCitasPaciente(int codigoPaciente) throws Exception;

    List<ItemCitaPqrsPacienteDTO> listarCitasPqrsPaciente(int idPaciente) throws Exception;

    List<ItemConsultaPacienteDTO> buscarConsulta(BusquedaConsultaDTO busquedaConsultaDTO) throws Exception;

}
