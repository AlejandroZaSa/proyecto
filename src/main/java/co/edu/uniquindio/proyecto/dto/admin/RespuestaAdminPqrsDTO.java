package co.edu.uniquindio.proyecto.dto.admin;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record RespuestaAdminPqrsDTO(

        @Positive
        int codigoPqrs,

        @Positive
        int codigoAdmin,

        @NotBlank
        String mensaje) {
}
