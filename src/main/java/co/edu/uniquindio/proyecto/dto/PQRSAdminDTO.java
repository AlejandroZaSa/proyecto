package co.edu.uniquindio.proyecto.dto;

import co.edu.uniquindio.proyecto.modelo.enums.EstadoPqrs;

import java.time.LocalDateTime;

public record PQRSAdminDTO(int codigo, String tipo, LocalDateTime fecha, EstadoPqrs estadoPqrs) {
}
