package co.edu.uniquindio.proyecto.dto.admin;

import co.edu.uniquindio.proyecto.modelo.enums.Dia;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.aspectj.lang.annotation.Pointcut;

import java.time.LocalTime;

public record ActualizarHorarioDTO (
                                    @Positive
                                    int codigoHorario,
                                     @NotNull
                                     Dia dia,
                                     @NotNull
                                     LocalTime horaInicio,
                                     @NotNull
                                     LocalTime horaFin){


}
