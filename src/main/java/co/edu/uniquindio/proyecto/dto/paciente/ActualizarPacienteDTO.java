package co.edu.uniquindio.proyecto.dto.paciente;

import co.edu.uniquindio.proyecto.modelo.enums.Ciudad;
import co.edu.uniquindio.proyecto.modelo.enums.TipoSangre;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

public record ActualizarPacienteDTO(
        @NotBlank
        @Length(max = 30, message="El nombre debe tener máximo 30 caracteres")
        String nombre,
        @NotBlank
        @Length(max = 10, message="La cedula debe tener máximo 10 caracteres")
        String cedula,
        @NotBlank
        @Length(max = 10, message="El telefono debe tener máximo 10 caracteres")
        String telefono,
        @NotBlank
        @Email(message = "Ingrese una dirección de correo electrónico válida")
        @Length(max = 50, message = "El correo debe tener máximo 50 caracteres")
        String email,
        @NotBlank
        String foto,
        @NotNull
                @Past(message = "Seleccione una fecha de nacimiento correcta")
        LocalDate fechaNacimiento,

        @NotNull
        Ciudad ciudad,

        @Positive
        int eps,
        @NotNull
        TipoSangre tipoSangre,
        @NotBlank
        String alergias,

        boolean estado) {
}
