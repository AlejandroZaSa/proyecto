package co.edu.uniquindio.proyecto.dto.admin;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public record DetalleConsultaPqrsDTO(
        @NotNull
        LocalDate fecha,
        @NotNull
        String notasMedico,

        @NotNull
        String diagnostico,

        List<RegistroTratamientoDTO> tratamientoDTOList) {
}
