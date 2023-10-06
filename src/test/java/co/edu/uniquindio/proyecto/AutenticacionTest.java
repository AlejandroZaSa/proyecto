package co.edu.uniquindio.proyecto;

import co.edu.uniquindio.proyecto.dto.clinica.LoginDTO;
import co.edu.uniquindio.proyecto.servicios.interfaces.AutenticacionServicio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
public class AutenticacionTest {

    @Autowired
    private AutenticacionServicio autenticacionServicio;

    @Test
    @Sql("classpath:dataset.sql")
    public void login(){
        LoginDTO login = new LoginDTO("az0031456@gmail.com","pass_encriptada");

        try {
            autenticacionServicio.login(login);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
