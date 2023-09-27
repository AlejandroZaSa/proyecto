package co.edu.uniquindio.proyecto.servicios.implementacion;

import co.edu.uniquindio.proyecto.dto.clinica.ItemPqrsDTO;
import co.edu.uniquindio.proyecto.dto.paciente.*;
import co.edu.uniquindio.proyecto.servicios.interfaces.PacienteServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PacienteServicioImpl implements PacienteServicio {
    @Override
    public int registrarse(RegistroPacienteDTO pacienteDTO) throws Exception {
        return 0;
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
