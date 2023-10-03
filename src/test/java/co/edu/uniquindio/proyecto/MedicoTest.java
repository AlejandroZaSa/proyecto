package co.edu.uniquindio.proyecto;

import co.edu.uniquindio.proyecto.servicios.interfaces.MedicoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MedicoTest {

    @Autowired
    private MedicoServicio medicoServicio;


}
