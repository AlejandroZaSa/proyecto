package co.edu.uniquindio.proyecto.dto.clinica;

import java.time.LocalDateTime;

public record MensajeDTO(int codigo, int codigoCuenta, String nombreUsuario, String mensaje, LocalDateTime fecha, int codigoPqrs) {
}
