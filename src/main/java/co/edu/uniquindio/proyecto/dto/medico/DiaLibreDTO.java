package co.edu.uniquindio.proyecto.dto.medico;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DiaLibreDTO(
        int codigoMedico,

        @NotNull
        LocalDate fecha) {
}
