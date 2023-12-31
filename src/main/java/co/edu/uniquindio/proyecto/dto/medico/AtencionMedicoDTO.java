package co.edu.uniquindio.proyecto.dto.medico;

import co.edu.uniquindio.proyecto.dto.admin.RegistroTratamientoDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

import java.util.List;

public record AtencionMedicoDTO(
        @Positive
        int idCita,
        @NotBlank
        String sintomas,
        @NotBlank
        String diagnostico,
        @NotBlank
        String notasMedico,
        @NotEmpty
        List<RegistroTratamientoDTO> tratamientoDTOList) {
}
