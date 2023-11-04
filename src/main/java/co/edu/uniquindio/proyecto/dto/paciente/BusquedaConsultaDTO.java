package co.edu.uniquindio.proyecto.dto.paciente;

import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public record BusquedaConsultaDTO(String nombreMedico, LocalDate fecha, @Positive  int idPaciente) {
}
