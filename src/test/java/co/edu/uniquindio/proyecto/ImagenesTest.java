package co.edu.uniquindio.proyecto;

import co.edu.uniquindio.proyecto.servicios.interfaces.ImagenesServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ImagenesTest {

    @Autowired
    private ImagenesServicio imagenesServicio;
}
