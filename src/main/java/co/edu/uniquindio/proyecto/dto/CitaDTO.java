package co.edu.uniquindio.proyecto.dto;

import co.edu.uniquindio.proyecto.modelo.enums.EstadoCita;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public record CitaDTO(String motivo, LocalDate fecha, LocalTime hora, EstadoCita estado,
                      int idMedico, int idPaciente) {
}
