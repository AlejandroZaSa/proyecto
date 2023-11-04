package co.edu.uniquindio.proyecto.dto.clinica;

import jakarta.validation.constraints.NotBlank;

public record CambioPasswordDTO (
                    @NotBlank
                    String email,
                    @NotBlank
                    String nuevaPassword){
}
