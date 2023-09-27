package co.edu.uniquindio.proyecto.dto.medico;

import co.edu.uniquindio.proyecto.modelo.enums.EstadoFactura;

import java.time.LocalDate;

public record DetalleFacturaDTO (int codigo, LocalDate fecha, float valor, EstadoFactura estadoFactura){
}
