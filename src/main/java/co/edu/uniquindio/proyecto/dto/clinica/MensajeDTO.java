package co.edu.uniquindio.proyecto.dto.clinica;

import java.time.LocalDateTime;

public record MensajeDTO(int codigo, int usuario, String mensaje, LocalDateTime fecha, int codigoPqrs) {
}
