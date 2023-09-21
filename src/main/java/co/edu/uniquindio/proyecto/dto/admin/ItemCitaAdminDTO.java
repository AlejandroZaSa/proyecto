package co.edu.uniquindio.proyecto.dto.admin;

import java.time.LocalDate;

public record ItemCitaAdminDTO(String cedulaPaciente, String nombrePaciente, LocalDate fecha, String nombreMedico) {
}
