package co.edu.uniquindio.proyecto.dto.paciente;

import co.edu.uniquindio.proyecto.modelo.enums.Ciudad;
import co.edu.uniquindio.proyecto.modelo.enums.TipoSangre;

import java.time.LocalDateTime;

public record ActualizarPacienteDTO(String nombre, String cedula, String telefono, String email, String foto, LocalDateTime fechaNacimiento, Ciudad ciudad, int eps, TipoSangre tipoSangre, String alergias) {
}