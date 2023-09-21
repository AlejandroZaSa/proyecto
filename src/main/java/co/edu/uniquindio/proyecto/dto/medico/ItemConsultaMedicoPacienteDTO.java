package co.edu.uniquindio.proyecto.dto.medico;

import java.time.LocalDate;

public record ItemConsultaMedicoPacienteDTO(int idConsulta, String nombrePaciente, LocalDate fecha, String notasMedico, String diagnostico, String sintomas) {
}
