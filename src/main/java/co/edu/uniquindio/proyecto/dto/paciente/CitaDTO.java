package co.edu.uniquindio.proyecto.dto.paciente;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;
import java.time.LocalTime;

public record CitaDTO(
        @NotBlank
        String motivo,
        @NotNull
        LocalDate fecha,
        @NotNull
        LocalTime hora,
        @Positive
        int idMedico,
        @Positive
        int idPaciente) {

}
