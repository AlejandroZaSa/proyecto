package co.edu.uniquindio.proyecto.dto.paciente;

import java.time.LocalTime;

public record ItemMedicoCitaDTO(int codigoMedico, String nombreMedico, LocalTime hora) {

}
