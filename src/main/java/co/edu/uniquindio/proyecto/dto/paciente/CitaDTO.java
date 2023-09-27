package co.edu.uniquindio.proyecto.dto.paciente;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

public record CitaDTO(
        @NotNull
        String motivo,
        @NotNull
        LocalDate fecha,
        @NotNull
        LocalTime hora,
        @NotNull
        int idMedico,
        @NotNull
        int idPaciente) {

}
