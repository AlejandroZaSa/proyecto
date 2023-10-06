package co.edu.uniquindio.proyecto.dto.paciente;

import jakarta.validation.constraints.NotBlank;

public record RespuestaPacientePqrsDTO(

        int codigoPqrs,
        @NotBlank
        String mensaje,

        int respuestaAdmin,

        int codigoPaciente) {
}
