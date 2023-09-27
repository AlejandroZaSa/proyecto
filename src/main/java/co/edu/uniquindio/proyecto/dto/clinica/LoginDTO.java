package co.edu.uniquindio.proyecto.dto.clinica;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record LoginDTO(
        @NotNull
                @Length(max=50)
        String email,
        @NotNull
        @Length(max=20)
        String password) {
}
