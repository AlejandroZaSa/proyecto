package co.edu.uniquindio.proyecto.dto.clinica;

import co.edu.uniquindio.proyecto.modelo.enums.EstadoPqrs;

import java.time.LocalDateTime;

public record ItemPqrsDTO(int codigoRadicacion, String detalle,

                          LocalDateTime fecha, EstadoPqrs estadoPqrs) {
}
