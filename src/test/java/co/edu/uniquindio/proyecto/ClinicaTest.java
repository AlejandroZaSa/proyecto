package co.edu.uniquindio.proyecto;

import co.edu.uniquindio.proyecto.dto.autenticacionJwt.TokenDTO;
import co.edu.uniquindio.proyecto.dto.clinica.*;
import co.edu.uniquindio.proyecto.servicios.interfaces.AutenticacionServicio;
import co.edu.uniquindio.proyecto.servicios.interfaces.ClinicaServicio;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@SpringBootTest
@Transactional
public class ClinicaTest {

    @Autowired
    private ClinicaServicio  clinicaServicio;
    @Autowired
    private AutenticacionServicio autenticacionServicio;

    @Test
    @Sql("classpath:dataset.sql")
    public void cargarEps(){

        List<EpsDTO> epsDTOList;
        try {
            epsDTOList = clinicaServicio.cargarEps();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Assertions.assertEquals(5,epsDTOList.size());

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void mostrarHistorialMensajesPqrs(){

        List<MensajeDTO> mensajes;

        try {
            mensajes = clinicaServicio.mostrarHistorialMensajesPqrs(1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Assertions.assertEquals(6, mensajes.size());
    }

    @Test
    public void enviarLinkRecuperacion() {

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void cambiarPassword() {

        try {
            clinicaServicio.cambiarPassword(new CambioPasswordDTO("az0031456@gmail.com","1hello2"));
            TokenDTO tokenDTO = autenticacionServicio.login(new LoginDTO("az0031456@gmail.com","1hello2"));
            Assertions.assertNotNull(tokenDTO);
            System.out.println(tokenDTO);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    @Test
    @Sql("classpath:dataset.sql")
    public void verTratamiento() {

        List<ItemTratamientoDTO> tratamientoDTOList;

        try {
            tratamientoDTOList = clinicaServicio.verTratamiento(1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Assertions.assertEquals(3, tratamientoDTOList.size());
    }
}
