package co.edu.uniquindio.proyecto.servicios.implementacion;

import co.edu.uniquindio.proyecto.dto.clinica.EmailDTO;
import co.edu.uniquindio.proyecto.servicios.interfaces.EmailServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class EmailServicioImpl implements EmailServicio {
    @Override
    public String enviarEmail(EmailDTO emailDTO) throws Exception {
        return null;
    }
}
