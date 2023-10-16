package co.edu.uniquindio.proyecto;

import co.edu.uniquindio.proyecto.dto.admin.ItemCitaAdminDTO;
import co.edu.uniquindio.proyecto.dto.clinica.EpsDTO;
import co.edu.uniquindio.proyecto.dto.clinica.ItemTratamientoDTO;
import co.edu.uniquindio.proyecto.dto.clinica.MensajeDTO;
import co.edu.uniquindio.proyecto.modelo.entidades.Tratamiento;
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
    public void cambiarPassword() {

        try {
            clinicaServicio.cambiarPassword(1,"1hello2");
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
