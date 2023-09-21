package co.edu.uniquindio.proyecto.dto;

import java.time.LocalDate;

public record FacturaDTO(int idConsulta, int idFactura, LocalDate fecha, float valor, String concepto) {
}
