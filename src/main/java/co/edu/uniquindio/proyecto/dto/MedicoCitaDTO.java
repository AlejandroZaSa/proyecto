package co.edu.uniquindio.proyecto.dto;

import co.edu.uniquindio.proyecto.modelo.enums.Especialidad;

import java.time.LocalTime;

public record MedicoCitaDTO(int codigoMedico, String nombre, LocalTime hora) {
}
