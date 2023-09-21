package co.edu.uniquindio.proyecto.dto.medico;

import java.time.LocalDate;

public record DiaLibreDTO(int codigoMedico, LocalDate fecha, boolean estado) {
}
