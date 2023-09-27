package co.edu.uniquindio.proyecto.dto.admin;

import jakarta.validation.constraints.NotNull;

public record RespuestaAdminPqrsDTO(

        @NotNull
        int codigoPqrs,

        @NotNull
        int codigoAdmin,

        @NotNull
        String mensaje) {
}
