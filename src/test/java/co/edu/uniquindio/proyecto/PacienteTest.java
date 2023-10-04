package co.edu.uniquindio.proyecto;

import co.edu.uniquindio.proyecto.dto.paciente.ActualizarPacienteDTO;
import co.edu.uniquindio.proyecto.dto.paciente.RegistroPacienteDTO;
import co.edu.uniquindio.proyecto.modelo.enums.Ciudad;
import co.edu.uniquindio.proyecto.modelo.enums.TipoSangre;
import co.edu.uniquindio.proyecto.servicios.interfaces.PacienteServicio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

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
                Ciudad.MEDELLIN,
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

    }

    @Test
    public void filtrarMedicoCita(){

    }

    @Test
    public void agendarCita(){

    }

    @Test
    public void crearPqrs(){

    }

    @Test
    public void listarPqrsPaciente(){

    }

    @Test
    public void responderPqrs(){

    }

    @Test
    public void listarCitasPaciente(){

    }


    @Test
    public void listarCitasPqrsPaciente(){

    }

    @Test
    public void buscarConsulta(){

    }

}
