package co.edu.uniquindio.proyecto;

import co.edu.uniquindio.proyecto.servicios.interfaces.ImagenesServicio;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Transactional
public class ImagenesTest {

    @Autowired
    private ImagenesServicio imagenesServicio;

    @Test
    public void subirImagen(){

    }

    @Test
    public void eliminarImagen(){

    }
}
