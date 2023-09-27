package co.edu.uniquindio.proyecto.dto.admin;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.util.List;

public record ActualizarMedicoDTO(@NotNull
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
                                  @Min(0)
                                  @Max(8)
                                  int especialidad,

                                  @NotNull
                                  List<HorarioDTO> horarioDTO) {
}
