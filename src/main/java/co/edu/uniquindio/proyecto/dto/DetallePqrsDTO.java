package co.edu.uniquindio.proyecto.dto;

import co.edu.uniquindio.proyecto.modelo.enums.EstadoPqrs;

import java.time.LocalDateTime;
import java.util.List;

public record DetallePqrsDTO (String codigo, LocalDateTime fecha, EstadoPqrs estadoPqrs, String motivo, List<String> mensajes) {
}
