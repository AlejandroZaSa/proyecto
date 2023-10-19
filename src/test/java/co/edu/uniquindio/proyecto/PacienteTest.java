package co.edu.uniquindio.proyecto;

import co.edu.uniquindio.proyecto.dto.clinica.ItemPqrsDTO;
import co.edu.uniquindio.proyecto.dto.paciente.*;
import co.edu.uniquindio.proyecto.modelo.enums.Ciudad;
import co.edu.uniquindio.proyecto.modelo.enums.Especialidad;
import co.edu.uniquindio.proyecto.modelo.enums.TipoSangre;
import co.edu.uniquindio.proyecto.servicios.interfaces.PacienteServicio;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@SpringBootTest
@Transactional
public class PacienteTest {

    @Autowired
    private PacienteServicio pacienteServicio;

    @Test
    @Sql("classpath:dataset.sql")
    public void agregarPaciente() {

        RegistroPacienteDTO registroPacienteDTO = new RegistroPacienteDTO(
                "Karla Sanz",
                "4321",
                "3134697423",
                "karlas@gmail.com",
                "pass_prueba",
                "url_foto",
                LocalDate.of(2003, 4, 6),
                Ciudad.BOGOTA,
                1,
                TipoSangre.B_POSITIVO,
                "Rinitis"
        );

        int codigoPaciente;
        try {
            codigoPaciente = pacienteServicio.registrarse(registroPacienteDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Assertions.assertNotEquals(0, codigoPaciente);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void cargarDatosPaciente(){

        ActualizarPacienteDTO cargarDatosPaciente;

        try {
            cargarDatosPaciente = pacienteServicio.cargarDatosPaciente(1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Assertions.assertEquals("nancysalgado2369@gmail.com", cargarDatosPaciente.email());
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarPaciente() throws Exception {

        ActualizarPacienteDTO actualizarPacienteDTO = pacienteServicio.cargarDatosPaciente(1);

        ActualizarPacienteDTO nuevo = new ActualizarPacienteDTO(
                actualizarPacienteDTO.nombre(),
                actualizarPacienteDTO.cedula(),
                actualizarPacienteDTO.telefono(),
                actualizarPacienteDTO.email(),
                "otra foto",
                actualizarPacienteDTO.fechaNacimiento(),
                actualizarPacienteDTO.ciudad(),
                actualizarPacienteDTO.eps(),
                actualizarPacienteDTO.tipoSangre(),
                actualizarPacienteDTO.alergias(),
                actualizarPacienteDTO.estado()
        );

        try {
            pacienteServicio.editarPerfil(1, nuevo);

            ActualizarPacienteDTO actualizado = pacienteServicio.cargarDatosPaciente(1);

            Assertions.assertEquals("otra foto", actualizado.foto());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarCuenta() {

        ActualizarPacienteDTO actualizarPacienteDTO;
        try {
            pacienteServicio.eliminarCuenta(1);

            actualizarPacienteDTO = pacienteServicio.cargarDatosPaciente(1);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Assertions.assertEquals(false, actualizarPacienteDTO.estado());
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void filtrarMedicoCita() {

        List<ItemMedicoCitaDTO> medicoCitaDTOList;
        try {
            medicoCitaDTOList = pacienteServicio.filtrarMedicoCita(Especialidad.PEDIATRIA, LocalDate.of(2023, 10, 20));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Assertions.assertEquals(4,medicoCitaDTOList.size());
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void agendarCita(){

        CitaDTO citaDTO = new CitaDTO("Enfermedad", LocalDate.of(2023,10,5), LocalTime.of(7,8,0),1,1);

        int codigoCita;

        try {
            codigoCita = pacienteServicio.agendarCita(citaDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Assertions.assertNotEquals(0, codigoCita);

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void crearPqrs(){
        PQRSPacienteDTO pqrsPacienteDTO = new PQRSPacienteDTO(1, "Enfermedad");

        int numRadicado;

        try {
            numRadicado = pacienteServicio.crearPqrs(pqrsPacienteDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Assertions.assertNotEquals(0, numRadicado);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarPqrsPaciente(){

        List<ItemPqrsDTO> listaPqrsPaciente;

        try {
            listaPqrsPaciente = pacienteServicio.listarPqrsPaciente(1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Assertions.assertEquals(1, listaPqrsPaciente.size());

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void responderPqrs(){
        RespuestaPacientePqrsDTO respuestaPacientePqrsDTO = new RespuestaPacientePqrsDTO(1, "Mensaje", 1,1);

        Assertions.assertThrows(Exception.class, () -> pacienteServicio.responderPqrs(respuestaPacientePqrsDTO));

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarCitasPaciente(){

        List<ItemCitaPacienteDTO> listaCitasPaciente;

        try {
            listaCitasPaciente = pacienteServicio.listarCitasPaciente(1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Assertions.assertEquals(1, listaCitasPaciente.size());

    }


    @Test
    @Sql("classpath:dataset.sql")
    public void listarCitasPqrsPaciente(){

        List<ItemCitaPqrsPacienteDTO> listaCitasPqrsPaciente;

        try {
            listaCitasPqrsPaciente = pacienteServicio.listarCitasPqrsPaciente(1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Assertions.assertEquals(1, listaCitasPqrsPaciente.size());

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void buscarConsulta(){

        Assertions.assertThrows(Exception.class, () -> pacienteServicio.buscarConsulta("Dra. Laura Gonzalez", LocalDate.of(2023,10,5),1));

    }

}
