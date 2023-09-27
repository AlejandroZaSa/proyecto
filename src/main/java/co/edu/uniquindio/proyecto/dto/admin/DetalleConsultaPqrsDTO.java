package co.edu.uniquindio.proyecto.dto.admin;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public record DetalleConsultaPqrsDTO(

        LocalDate fecha,

        String notasMedico,

        String diagnostico,

        List<RegistroTratamientoDTO> tratamientoDTOList) {
}
