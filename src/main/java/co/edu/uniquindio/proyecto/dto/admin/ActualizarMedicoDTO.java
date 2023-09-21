package co.edu.uniquindio.proyecto.dto.admin;

import java.util.List;

public record ActualizarMedicoDTO(String cedula,

                                  String nombre,

                                  String foto,

                                  int ciudad,

                                  String telefono,

                                  String email,

                                  int especialidad,

                                  List<HorarioDTO> horarioDTO) {
}
