package co.edu.uniquindio.proyecto.dto.paciente;

import co.edu.uniquindio.proyecto.modelo.enums.Ciudad;
import co.edu.uniquindio.proyecto.modelo.enums.TipoSangre;
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
        String email,
        @NotNull
        String foto,
        LocalDate fechaNacimiento,
        Ciudad ciudad,
        int eps,
        TipoSangre tipoSangre,
        String alergias) {
}
