package co.edu.uniquindio.proyecto.dto.clinica;

import co.edu.uniquindio.proyecto.modelo.enums.Medicamento;

import java.util.List;

public record TratamientoDTO(int dosis, String observaciones, List<Medicamento> nombreMedicamento) {
}
