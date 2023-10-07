package co.edu.uniquindio.proyecto.dto.admin;

import co.edu.uniquindio.proyecto.modelo.enums.Ciudad;
import co.edu.uniquindio.proyecto.modelo.enums.Especialidad;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;

import java.util.List;

public record RegistroMedicoDTO(

        @NotBlank
        @Length(max = 10, message="La cedula debe tener máximo 10 caracteres")
        String cedula,

        @NotBlank
        @Length(max = 30, message="El nombre debe tener máximo 30 caracteres")
        String nombre,

        @NotBlank
        String foto,

        @NotNull
        Ciudad ciudad,

        @NotBlank
        @Length(max = 10, message="El telefono debe tener máximo 10 caracteres")
        String telefono,

        @NotBlank
        @Email(message = "Ingrese una dirección de correo electrónico válida")
        @Length(max = 50, message = "El correo debe tener máximo 50 caracteres")
        String email,

        @Positive
        float precioConsulta,

        @NotBlank
        String password,

        @NotNull
        Especialidad especialidad,

        @NotEmpty
        List<RegistroHorarioDTO> horarioDTO) {
}
