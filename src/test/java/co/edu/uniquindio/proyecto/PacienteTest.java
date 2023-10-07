package co.edu.uniquindio.proyecto;

import co.edu.uniquindio.proyecto.dto.clinica.ItemPqrsDTO;
import co.edu.uniquindio.proyecto.dto.paciente.*;
import co.edu.uniquindio.proyecto.modelo.enums.Ciudad;
import co.edu.uniquindio.proyecto.modelo.enums.Especialidad;
import co.edu.uniquindio.proyecto.modelo.enums.TipoSangre;
import co.edu.uniquindio.proyecto.servicios.interfaces.PacienteServicio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@SpringBootTest
public class PacienteTest {

    @Autowired
    private PacienteServicio pacienteServicio;

    @Test
    public void agregarPaciente(){

        RegistroPacienteDTO registroPacienteDTO = new RegistroPacienteDTO(
                "Karla Sanz",
                "4321",
                "3134697423",
                "karlas@gmail.com",
                "pass_prueba",
                "url_foto",
                LocalDate.of(2003,4,6),
                Ciudad.BOGOTA,
                1,
                TipoSangre.B_POSITIVO,
                "Rinitis"
        );

        try {
            pacienteServicio.registrarse(registroPacienteDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void cargarDatosPaciente(){

        ActualizarPacienteDTO cargarDatosPaciente;

        try {
            cargarDatosPaciente = pacienteServicio.cargarDatosPaciente(1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if(cargarDatosPaciente != null){
            System.out.println(cargarDatosPaciente.toString());
        }
    }

    @Test
    public void actualizarPaciente(){
        ActualizarPacienteDTO actualizarPacienteDTO = new ActualizarPacienteDTO(
                "Karla Sanz",
                "4321",
                "3134697423",
                "karlita@gmail.com",
                "url_foto",
                LocalDate.of(2003,4,6),
                Ciudad.ARMENIA,
                1,
                TipoSangre.B_POSITIVO,
                "Rinitis"
        );
        try {
            pacienteServicio.editarPerfil(1,actualizarPacienteDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void eliminarCuenta(){

        try {
            pacienteServicio.eliminarCuenta(1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void filtrarMedicoCita() {

        List<ItemMedicoCitaDTO> medicoCitaDTOList;
        try {
            medicoCitaDTOList = pacienteServicio.filtrarMedicoCita(Especialidad.PEDIATRIA, LocalDate.of(2023, 10, 11));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        for (ItemMedicoCitaDTO itemMedicoCitaDTO :medicoCitaDTOList){
            System.out.println(itemMedicoCitaDTO.toString());
        }

    }

    @Test
    public void agendarCita(){

        //lo de la hora es lo que selecciono de la tabla como lo represento acá
        //pacienteServicio.agendarCita(new CitaDTO("Migraña continua",LocalDate.of(2023,10,5),""));

        CitaDTO citaDTO = new CitaDTO("Enfermedad", LocalDate.of(2023,10,5), LocalTime.of(7,8,0),1,1);

        int codigoCita;

        try {
            codigoCita = pacienteServicio.agendarCita(citaDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        System.out.println(codigoCita);
    }

    @Test
    public void crearPqrs(){
        PQRSPacienteDTO pqrsPacienteDTO = new PQRSPacienteDTO(1, "Enfermedad");

        int numRadicado;

        try {
            numRadicado = pacienteServicio.crearPqrs(pqrsPacienteDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        System.out.println(numRadicado);
    }

    @Test
    public void listarPqrsPaciente(){

        List<ItemPqrsDTO> listaPqrsPaciente;

        try {
            listaPqrsPaciente = pacienteServicio.listarPqrsPaciente(1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (listaPqrsPaciente != null){
            for (ItemPqrsDTO itemPqrsDTO: listaPqrsPaciente){
                System.out.println(itemPqrsDTO.toString());
            }
        }
    }

    @Test
    public void responderPqrs(){
        RespuestaPacientePqrsDTO respuestaPacientePqrsDTO = new RespuestaPacientePqrsDTO(1, "Mensaje", 1,1);

        int idRespuestaPaciente;

        try {
            idRespuestaPaciente = pacienteServicio.responderPqrs(respuestaPacientePqrsDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        System.out.println(idRespuestaPaciente);
    }

    @Test
    public void listarCitasPaciente(){

        List<ItemCitaPacienteDTO> listaCitasPaciente;

        try {
            listaCitasPaciente = pacienteServicio.listarCitasPaciente(1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (listaCitasPaciente != null){
            for (ItemCitaPacienteDTO itemCitaPacienteDTO: listaCitasPaciente){
                System.out.println(itemCitaPacienteDTO.toString());
            }
        }
    }


    @Test
    public void listarCitasPqrsPaciente(){
        List<ItemCitaPqrsPacienteDTO> listaCitasPqrsPaciente;

        try {
            listaCitasPqrsPaciente = pacienteServicio.listarCitasPqrsPaciente(1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (listaCitasPqrsPaciente != null){
            for (ItemCitaPqrsPacienteDTO itemCitaPqrsPacienteDTO: listaCitasPqrsPaciente){
                System.out.println(itemCitaPqrsPacienteDTO.toString());
            }
        }
    }

    @Test
    public void buscarConsulta(){
        List<ItemConsultaPacienteDTO> listaConsultasPaciente;

        try {
            listaConsultasPaciente  = pacienteServicio.buscarConsulta(" NOMBREMEDICO !!!!!!!!!!", LocalDate.of(2023,10,5),1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (listaConsultasPaciente != null){
            for (ItemConsultaPacienteDTO  itemConsultaPacienteDTO: listaConsultasPaciente){
                System.out.println(listaConsultasPaciente.toString());
            }
        }
    }

}
