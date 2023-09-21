package co.edu.uniquindio.proyecto.dto;

import java.util.List;

public record RegistroMedicoDTO(

    String cedula,

    String nombre,

    String foto,

    int ciudad,

    String telefono,

    String email,

    String password,

    int especialidad,

    List<HorarioDTO> horarioDTO){
}
