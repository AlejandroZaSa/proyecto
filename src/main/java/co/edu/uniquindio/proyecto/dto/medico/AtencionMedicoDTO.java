package co.edu.uniquindio.proyecto.dto.medico;

import co.edu.uniquindio.proyecto.dto.clinica.TratamientoDTO;

import java.util.List;

public record AtencionMedicoDTO(int idCita, String sintomas, String diagnostico, String notasMedico, List<TratamientoDTO> tratamientoDTOList) {
}
