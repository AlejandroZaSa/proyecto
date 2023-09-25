package co.edu.uniquindio.proyecto.dto.admin;

import co.edu.uniquindio.proyecto.modelo.enums.Ciudad;
import co.edu.uniquindio.proyecto.modelo.enums.Especialidad;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.util.List;

public record RegistroMedicoDTO(

        @NotNull
        @Length(max = 10)
        String cedula,

        @NotNull
        @Length(max = 30)
        String nombre,

        @NotNull
        String foto,

        @NotNull
        @Min(0) @Max(3)
        int ciudad,

        @NotNull
        @Length(max = 10)
        String telefono,

        @NotNull @Email @Length(max = 50)
        String email,

        @NotNull
        @Length(max = 20)
        String password,

        @NotNull
        @Min(0)
        @Max(8)
        int especialidad,

        List<HorarioDTO> horarioDTO) {
}
