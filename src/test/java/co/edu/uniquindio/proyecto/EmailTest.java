package co.edu.uniquindio.proyecto;

import co.edu.uniquindio.proyecto.dto.clinica.EmailDTO;
import co.edu.uniquindio.proyecto.servicios.interfaces.EmailServicio;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailTest {

    @Autowired
    private EmailServicio emailServicio;

    @Test
    public void enviarEmail() throws Exception{
        emailServicio.enviarEmail(new EmailDTO(
                "Test",
                "caflorez@uniquindio.edu.co",
                "<b>Saludo</b><br/><br/><p>Este es un mensaje de prueba</p>"
        ));
    }
}
