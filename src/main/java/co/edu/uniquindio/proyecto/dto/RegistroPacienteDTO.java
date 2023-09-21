package co.edu.uniquindio.proyecto.dto;

import co.edu.uniquindio.proyecto.modelo.enums.Ciudad;
import co.edu.uniquindio.proyecto.modelo.enums.TipoSangre;

import java.time.LocalDateTime;

public record RegistroPacienteDTO(String nombre, String cedula, String telefono, String email, String password, String foto, LocalDateTime fechaNacimiento, Ciudad ciudad, int eps, TipoSangre tipoSangre, String alergias) {
}
