package co.edu.uniquindio.proyecto.dto;

import co.edu.uniquindio.proyecto.modelo.enums.Especialidad;

public record ItemMedicoDTO(int id, String cedula, String nombreCompleto, Especialidad especialidad, boolean estado) {
}
