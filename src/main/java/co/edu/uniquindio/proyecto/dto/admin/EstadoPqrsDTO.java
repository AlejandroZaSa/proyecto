package co.edu.uniquindio.proyecto.dto.admin;

import co.edu.uniquindio.proyecto.modelo.enums.EstadoPqrs;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record EstadoPqrsDTO(
        @NotNull
        int codigoPqrs,
        @NotNull
        @Min(0) @Max(3)
        EstadoPqrs estado) {
}
