package co.edu.uniquindio.proyecto.dto.paciente;

import co.edu.uniquindio.proyecto.modelo.enums.Ciudad;
import co.edu.uniquindio.proyecto.modelo.enums.TipoSangre;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

public record ActualizarPacienteDTO(
        @NotNull
        @Length(max = 30)
        String nombre,
        @NotNull
        @Length(max = 10)
        String cedula,
        @NotNull
        @Length(max = 10)
        String telefono,
        @NotNull
        @Length(max = 50)
                @Email
        String email,
        @NotNull
        String foto,
        @NotNull
        LocalDate fechaNacimiento,

        @NotNull
        @Min(0) @Max(3)
        Ciudad ciudad,
        @NotNull
        int eps,
        @NotNull
        @Min(0) @Max(7)
        TipoSangre tipoSangre,
        @NotNull
        String alergias) {
}
