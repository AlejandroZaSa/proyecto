package co.edu.uniquindio.proyecto;

import co.edu.uniquindio.proyecto.dto.admin.ActualizarMedicoDTO;
import co.edu.uniquindio.proyecto.dto.admin.HorarioDTO;
import co.edu.uniquindio.proyecto.dto.admin.ItemMedicoDTO;
import co.edu.uniquindio.proyecto.dto.admin.RegistroMedicoDTO;
import co.edu.uniquindio.proyecto.modelo.enums.Ciudad;
import co.edu.uniquindio.proyecto.modelo.enums.Dia;
import co.edu.uniquindio.proyecto.modelo.enums.Especialidad;
import co.edu.uniquindio.proyecto.servicios.interfaces.AdministradorServicio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class AdministradorTest {

    @Autowired
    private AdministradorServicio administradorServicio;

    @Test
    public void crearMedicoTest() {

        List<HorarioDTO> horarios = new ArrayList<>();

        horarios.add(new HorarioDTO(Dia.LUNES, LocalTime.of(7, 33, 0, 0), LocalTime.of(8, 0, 0, 0)));
        RegistroMedicoDTO registroMedicoDTO = new RegistroMedicoDTO("12345",
                "alejandro zapata",
                "foto_url",
                Ciudad.ARMENIA,
                "3102423689",
                "az0031456@gmail.com",
                50000,
                "pass_encriptada",
                Especialidad.PEDIATRIA,
                horarios);

        try {
            administradorServicio.crearMedico(registroMedicoDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void actualizarMedicoTest() {
        List<HorarioDTO> horarios = new ArrayList<>();
        horarios.add(new HorarioDTO(Dia.LUNES, LocalTime.of(7, 40, 0, 0), LocalTime.of(8, 0, 0, 0)));

        ActualizarMedicoDTO actualizarMedicoDTO = new ActualizarMedicoDTO("12345",
                "alejandro salgado",
                "foto_url",
                Ciudad.ARMENIA,
                "3102423689",
                "az@gmail.com",
                Especialidad.PEDIATRIA,
                150000,
                horarios);

        try {
            administradorServicio.actualizarMedico(1, actualizarMedicoDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void obtenerMedico(){

        ActualizarMedicoDTO actualizarMedicoDTO = null;
        try {
            actualizarMedicoDTO = administradorServicio.obtenerMedico(1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println(actualizarMedicoDTO.toString());
    }

    @Test
    public void listarMedicos() {
        List<ItemMedicoDTO> listaMedicos;
        try {
            listaMedicos = administradorServicio.listarMedicos();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        for (ItemMedicoDTO itemMedicoDTO : listaMedicos) {
            System.out.println(itemMedicoDTO.toString());
        }
    }
}

