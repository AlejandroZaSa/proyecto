package co.edu.uniquindio.proyecto.dto.paciente;

import co.edu.uniquindio.proyecto.modelo.enums.EstadoPqrs;
import jakarta.validation.constraints.NotNull;

public record PQRSPacienteDTO (
        @NotNull
        int codigoPaciente,
        @NotNull
        int codigoCita,

        @NotNull
        String motivo){
}
