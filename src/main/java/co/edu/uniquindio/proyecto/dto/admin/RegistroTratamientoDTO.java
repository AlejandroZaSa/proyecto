package co.edu.uniquindio.proyecto.dto.admin;

import co.edu.uniquindio.proyecto.modelo.enums.Medicamento;
import jakarta.validation.constraints.NotBlank;

public record RegistroTratamientoDTO(int dosis,

                                     @NotBlank
                                     String observaciones,

                                     Medicamento nombreMedicamento) {
}
