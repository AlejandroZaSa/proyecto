package co.edu.uniquindio.proyecto.dto.paciente;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PQRSPacienteDTO (
        int codigoCita,

        @NotBlank
        String motivo){
}
