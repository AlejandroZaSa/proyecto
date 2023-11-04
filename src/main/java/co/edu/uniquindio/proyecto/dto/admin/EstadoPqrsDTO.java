package co.edu.uniquindio.proyecto.dto.admin;

import co.edu.uniquindio.proyecto.modelo.enums.EstadoPqrs;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record EstadoPqrsDTO(

        @Positive
        int codigoPqrs,
        @NotNull
        EstadoPqrs estado) {
}
