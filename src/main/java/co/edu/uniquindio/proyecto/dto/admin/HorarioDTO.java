package co.edu.uniquindio.proyecto.dto.admin;

import co.edu.uniquindio.proyecto.modelo.enums.Dia;

import java.time.LocalTime;

public record HorarioDTO (Dia dia,
                          LocalTime horaInicio, LocalTime horaFin){
}
