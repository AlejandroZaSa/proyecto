package co.edu.uniquindio.proyecto.dto.admin;

import co.edu.uniquindio.proyecto.modelo.enums.Medicamento;

public record RegistroTratamientoDTO(int dosis, int codigConsulta, String observaciones, Medicamento nombreMedicamento) {
}
