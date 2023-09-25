package co.edu.uniquindio.proyecto.servicios.interfaces;

import co.edu.uniquindio.proyecto.dto.clinica.EmailDTO;

public interface EmailServicio {

    String enviarEmail(EmailDTO emailDTO) throws Exception;
}
