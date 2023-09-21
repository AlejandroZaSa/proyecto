package co.edu.uniquindio.proyecto.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public record CitaMedicoDTO (int codigoCita, String nombrePaciente, LocalDate fecha, LocalTime hora, String motivo){
}
