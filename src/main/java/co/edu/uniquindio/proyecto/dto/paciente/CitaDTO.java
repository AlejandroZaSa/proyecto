package co.edu.uniquindio.proyecto.dto.paciente;

import java.time.LocalDate;
import java.time.LocalTime;

public record CitaDTO(String motivo, LocalDate fecha, LocalTime hora,
                      int idMedico, int idPaciente) {

}
