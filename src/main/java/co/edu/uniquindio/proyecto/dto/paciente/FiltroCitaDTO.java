package co.edu.uniquindio.proyecto.dto.paciente;

import co.edu.uniquindio.proyecto.modelo.enums.Especialidad;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record FiltroCitaDTO(
                            @NotNull
                            Especialidad especialidad,
                            @NotNull
                            LocalDate fecha) {
}
