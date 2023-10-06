package co.edu.uniquindio.proyecto.dto.admin;

import jakarta.validation.constraints.NotBlank;

public record RespuestaAdminPqrsDTO(

        int codigoPqrs,

        int codigoAdmin,

        @NotBlank
        String mensaje) {
}
