package co.edu.uniquindio.proyecto.dto.clinica;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record EmailDTO(

        @NotBlank
        String asunto,

        @NotBlank
                @Email
        String para,
        @NotBlank
        String mensaje
        ) {
}
