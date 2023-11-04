package co.edu.uniquindio.proyecto.dto.admin;

import co.edu.uniquindio.proyecto.modelo.enums.Medicamento;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record RegistroTratamientoDTO(
        @Positive
        int dosis,

        @NotBlank
        String observaciones,

        Medicamento nombreMedicamento) {
}
