package co.edu.uniquindio.proyecto.dto.paciente;

import jakarta.validation.constraints.NotBlank;

public record PQRSPacienteDTO (
        int codigoCita,

        @NotBlank
        String motivo){
}
