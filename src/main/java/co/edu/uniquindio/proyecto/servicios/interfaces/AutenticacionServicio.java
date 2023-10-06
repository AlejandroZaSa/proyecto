package co.edu.uniquindio.proyecto.servicios.interfaces;

import co.edu.uniquindio.proyecto.dto.clinica.LoginDTO;

public interface AutenticacionServicio {

    String login(LoginDTO loginDTO) throws Exception;
}
