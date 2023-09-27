package co.edu.uniquindio.proyecto.dto.medico;

import jakarta.validation.constraints.NotNull;

public record RegistrarFacturaDTO(

        @NotNull
        int idConsulta,
        @NotNull
        String concepto) {
}
