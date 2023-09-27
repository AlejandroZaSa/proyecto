package co.edu.uniquindio.proyecto.dto.clinica;

import co.edu.uniquindio.proyecto.modelo.enums.Medicamento;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ItemTratamientoDTO (

        @Positive
        int dosis,
        String observaciones,
        @NotNull
        Medicamento nombreMedicamento
        ){
}
