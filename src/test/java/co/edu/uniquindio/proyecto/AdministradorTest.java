package co.edu.uniquindio.proyecto;

import co.edu.uniquindio.proyecto.dto.admin.*;
import co.edu.uniquindio.proyecto.dto.clinica.ItemPqrsDTO;
import co.edu.uniquindio.proyecto.modelo.enums.Ciudad;
import co.edu.uniquindio.proyecto.modelo.enums.Dia;
import co.edu.uniquindio.proyecto.modelo.enums.Especialidad;
import co.edu.uniquindio.proyecto.modelo.enums.EstadoPqrs;
import co.edu.uniquindio.proyecto.servicios.interfaces.AdministradorServicio;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Transactional
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
            int codigoAdmin = administradorServicio.crearMedico(registroMedicoDTO);

            Assertions.assertEquals(1, codigoAdmin);

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
            int codigoMedico = administradorServicio.actualizarMedico(1, actualizarMedicoDTO);
            System.out.println(codigoMedico);
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
    public void eliminarMedico(){
        try {
            administradorServicio.eliminarMedico(1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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

    @Test
    public void listarPqrs(){
        List<ItemPqrsDTO> listaPqrs = null;
        try {
            listaPqrs = administradorServicio.listarPqrs();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        for(ItemPqrsDTO itemPqrsDTO : listaPqrs){
            System.out.println(itemPqrsDTO.toString());
        }
    }

    @Test
    public void cambiarEstadoPqrs(){

        EstadoPqrsDTO estadoPqrsDTO = new EstadoPqrsDTO(1, EstadoPqrs.EN_PROCESO);

        try {
            administradorServicio.cambiarEstadoPqrs(estadoPqrsDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void mostrarDetalleConsultaPqrs(){

        try {
            DetalleConsultaPqrsDTO detalleConsultaPqrsDTO = administradorServicio.mostrarDetalleConsultaPqrs(1);
            System.out.println( detalleConsultaPqrsDTO.toString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void responderPqrs(){

        RespuestaAdminPqrsDTO respuestaAdminPqrsDTO = new RespuestaAdminPqrsDTO(1,1,"Estamos tramitando su pqrs");

        try {
            int codigoRespuestaPqrs = administradorServicio.responderPqrs(respuestaAdminPqrsDTO);
            System.out.println(codigoRespuestaPqrs);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void listarCitas(){
        List<ItemCitaAdminDTO> citaAdminDTOList = null;
        try {
            citaAdminDTOList = administradorServicio.listarCitas();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        for(ItemCitaAdminDTO itemCitaAdminDTO : citaAdminDTOList){
            System.out.println(itemCitaAdminDTO.toString());
        }
    }
}

