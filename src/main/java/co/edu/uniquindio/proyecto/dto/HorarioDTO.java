package co.edu.uniquindio.proyecto.dto;

import co.edu.uniquindio.proyecto.modelo.enums.Dia;

import java.time.LocalTime;

public record HorarioDTO (Dia dia,
                          LocalTime horaInicio, LocalTime horaFin){
}
