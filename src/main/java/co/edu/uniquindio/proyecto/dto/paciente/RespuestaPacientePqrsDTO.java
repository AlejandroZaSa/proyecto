package co.edu.uniquindio.proyecto.dto.paciente;

import jakarta.validation.constraints.NotNull;

public record RespuestaPacientePqrsDTO(
        @NotNull
        int codigoPqrs,
        @NotNull
        String mensaje,
        @NotNull
        int respuestaAdmin,
        @NotNull
        int codigoPaciente) {
}
