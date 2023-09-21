package co.edu.uniquindio.proyecto.dto;

import java.time.LocalDate;
import java.util.List;

public record DetalleConsultaPqrsDTO(LocalDate fecha, String notasMedico, String diagnostico, List<TratamientoDTO> tratamientoDTOList) {
}
