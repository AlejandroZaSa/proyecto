package co.edu.uniquindio.proyecto.dto.medico;

import co.edu.uniquindio.proyecto.dto.admin.RegistroTratamientoDTO;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record AtencionMedicoDTO(
        @NotNull
        int idCita,
        @NotNull
        String sintomas,
        @NotNull
        String diagnostico,
        @NotNull
        String notasMedico,
        @NotNull
        List<RegistroTratamientoDTO> tratamientoDTOList) {
}
