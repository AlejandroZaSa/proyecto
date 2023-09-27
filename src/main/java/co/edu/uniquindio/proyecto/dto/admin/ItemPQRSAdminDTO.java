package co.edu.uniquindio.proyecto.dto.admin;

import co.edu.uniquindio.proyecto.modelo.enums.EstadoPqrs;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ItemPQRSAdminDTO(

        int codigo,

        String detalle,

        LocalDateTime fecha,

        int estadoPqrs) {
}
