package co.edu.uniquindio.proyecto.dto.autenticacionJwt;

import jakarta.validation.constraints.NotBlank;
public record TokenDTO (
        @NotBlank
        String token
){
}