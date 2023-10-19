package co.edu.uniquindio.proyecto;

import co.edu.uniquindio.proyecto.dto.autenticacionJwt.TokenDTO;
import co.edu.uniquindio.proyecto.dto.clinica.LoginDTO;
import co.edu.uniquindio.proyecto.servicios.interfaces.AutenticacionServicio;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
@Transactional
public class AutenticacionTest {

    @Autowired
    private AutenticacionServicio autenticacionServicio;

    @Test
    public void encode(){
        BCryptPasswordEncoder b = new BCryptPasswordEncoder();
        System.out.println( b.encode("1234") );
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void login(){
        LoginDTO login = new LoginDTO("az0031456@gmail.com","1234");

        try {
            TokenDTO tokenDTO = autenticacionServicio.login(login);
            Assertions.assertNotNull(tokenDTO);
            System.out.println(tokenDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
