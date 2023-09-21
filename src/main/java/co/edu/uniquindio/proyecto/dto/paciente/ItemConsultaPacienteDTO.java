package co.edu.uniquindio.proyecto.dto.paciente;

import java.time.LocalDate;

public record ItemConsultaPacienteDTO(int codigoConsulta, LocalDate fecha, String nombreMedico, String diagnostico, String sintomas) {
}
