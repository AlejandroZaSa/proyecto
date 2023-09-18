package co.edu.uniquindio.proyecto.dto;

import java.util.List;

public record MedicoDTO (

    String cedula,

    String nombre,

    String telefono,

    String ciudad,

    String correo,

    String password,

    String especialidad,

    List<HorarioDTO> horarioDTO){
}
