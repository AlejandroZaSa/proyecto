package co.edu.uniquindio.proyecto.dto.admin;

import co.edu.uniquindio.proyecto.dto.clinica.ItemTratamientoDTO;

import java.time.LocalDateTime;
import java.util.List;

public record DetalleConsultaPqrsDTO(

        LocalDateTime fecha,

        String notasMedico,

        String diagnostico,

        List<ItemTratamientoDTO> tratamientoDTOList,

        String sintomas) {
}
