package co.edu.uniquindio.proyecto.dto.paciente;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record PQRSPacienteDTO (
        @Positive
        int codigoCita,

        @NotBlank
        String motivo){
}
