package co.edu.uniquindio.proyecto.dto.medico;

import java.time.LocalDate;
import java.time.LocalTime;

public record ItemCitaMedicoDTO(int codigoCita, int codigoPaciente, String cedulaPaciente, String nombrePaciente, LocalDate fecha, LocalTime hora, String motivo){
}
