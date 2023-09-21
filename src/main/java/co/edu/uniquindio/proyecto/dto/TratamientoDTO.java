package co.edu.uniquindio.proyecto.dto;

import co.edu.uniquindio.proyecto.modelo.enums.Medicamento;

public record TratamientoDTO(int dosis, String observaciones, Medicamento nombreMedicamento) {
}
