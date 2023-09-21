package co.edu.uniquindio.proyecto.dto;

import java.time.LocalDateTime;

public record MensajeDTO(int usuario, String mensaje, LocalDateTime fecha, int codigoPqrs) {
}
