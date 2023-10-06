package co.edu.uniquindio.proyecto.servicios.interfaces;

import org.hibernate.mapping.Map;
import org.springframework.web.multipart.MultipartFile;

public interface ImagenesServicio {

    Map subirImagen(MultipartFile file) throws Exception;
    Map eliminarImagen(String idImagen) throws Exception;
}
