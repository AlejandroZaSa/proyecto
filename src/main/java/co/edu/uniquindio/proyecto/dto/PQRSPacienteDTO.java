package co.edu.uniquindio.proyecto.dto;

import co.edu.uniquindio.proyecto.modelo.enums.EstadoPqrs;

public record PQRSPacienteDTO (int codigoPaciente, int codigoPqrs, String motivo, EstadoPqrs estadoPqrs){
}
