package co.edu.uniquindio.proyecto.dto.admin;

import co.edu.uniquindio.proyecto.modelo.enums.Dia;
import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;

public record ActualizarHorarioDTO ( int codigoHorario,
                                     @NotNull
                                     Dia dia,
                                     @NotNull
                                     LocalTime horaInicio,
                                     @NotNull
                                     LocalTime horaFin){


}