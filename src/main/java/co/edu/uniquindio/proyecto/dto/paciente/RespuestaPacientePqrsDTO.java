package co.edu.uniquindio.proyecto.dto.paciente;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record RespuestaPacientePqrsDTO(

        @Positive
        int codigoPqrs,
        @NotBlank
        String mensaje,

        @Positive
        int respuestaAdmin,

        @Positive
        int codigoPaciente) {
}
