package co.edu.uniquindio.proyecto.dto;

import java.time.LocalDate;

public record CitasAdminDTO (String cedulaPaciente, String nombrePaciente, LocalDate fecha, String nombreMedico) {
}
