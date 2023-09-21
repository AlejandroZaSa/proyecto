package co.edu.uniquindio.proyecto.dto;

import java.util.List;

public record AtencionMedicoDTO(int idCita, String sintomas, String diagnostico, String notasMedico, List<TratamientoDTO> tratamientoDTOList) {
}
