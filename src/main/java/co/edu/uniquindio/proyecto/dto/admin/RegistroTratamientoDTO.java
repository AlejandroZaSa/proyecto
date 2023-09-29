package co.edu.uniquindio.proyecto.dto.admin;

import co.edu.uniquindio.proyecto.modelo.enums.Medicamento;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record RegistroTratamientoDTO(int dosis,

                                     @NotNull
                                     int codigConsulta,
                                     @NotNull
                                     String observaciones,

                                     Medicamento nombreMedicamento) {
}
