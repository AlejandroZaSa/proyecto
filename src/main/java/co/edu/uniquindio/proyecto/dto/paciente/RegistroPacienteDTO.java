package co.edu.uniquindio.proyecto.dto.paciente;

import co.edu.uniquindio.proyecto.modelo.enums.Ciudad;
import co.edu.uniquindio.proyecto.modelo.enums.TipoSangre;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record RegistroPacienteDTO(

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
        @Email
        @Length(max = 50)
        String email,
        @NotNull
        @Length(max = 20)
        String password,
        @NotNull
        String foto,
        @NotNull
        LocalDate fechaNacimiento,

        @NotNull
        Ciudad ciudad,
        @NotNull
        int eps,
        @NotNull
        TipoSangre tipoSangre,
        @NotNull
        String alergias) {
}
