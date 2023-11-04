package co.edu.uniquindio.proyecto.dto.medico;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public record DiaLibreDTO(
        @Positive
        int codigoMedico,

        @NotNull
        LocalDate fecha) {
}
