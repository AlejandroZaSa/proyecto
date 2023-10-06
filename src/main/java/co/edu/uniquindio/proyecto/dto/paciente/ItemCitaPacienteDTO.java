package co.edu.uniquindio.proyecto.dto.paciente;

import co.edu.uniquindio.proyecto.modelo.enums.EstadoCita;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public record ItemCitaPacienteDTO(String motivo, LocalDateTime fechaCreacion, LocalDate fechaCita, LocalTime hora, EstadoCita estadoCita, String nombreMedico) {
}
