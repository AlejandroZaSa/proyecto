package co.edu.uniquindio.proyecto.dto;

import java.util.List;

public record DetalleMedicoDTO(String cedula,

                               int codigo,

                               String nombre,

                               String telefono,

                               String ciudad,

                               String correo,

                               String especialidad,

                               List<HorarioDTO> horarioDTO) {
}
