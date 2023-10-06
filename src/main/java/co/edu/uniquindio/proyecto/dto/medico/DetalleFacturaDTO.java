package co.edu.uniquindio.proyecto.dto.medico;

import java.time.LocalDate;

public record DetalleFacturaDTO (int codigo, LocalDate fecha, float valor, String concepto){
}
