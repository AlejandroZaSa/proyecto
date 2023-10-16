package co.edu.uniquindio.proyecto.servicios.interfaces;

import co.edu.uniquindio.proyecto.dto.autenticacionJwt.TokenDTO;
import co.edu.uniquindio.proyecto.dto.clinica.LoginDTO;

public interface AutenticacionServicio {

    TokenDTO login(LoginDTO loginDTO) throws Exception;
}
