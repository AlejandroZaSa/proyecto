package co.edu.uniquindio.proyecto.controladores;
import co.edu.uniquindio.proyecto.dto.autenticacionJwt.MensajeDTO;
import co.edu.uniquindio.proyecto.dto.clinica.ItemPqrsDTO;
import co.edu.uniquindio.proyecto.dto.clinica.ItemTratamientoDTO;
import co.edu.uniquindio.proyecto.dto.paciente.*;
import co.edu.uniquindio.proyecto.servicios.interfaces.ClinicaServicio;
import co.edu.uniquindio.proyecto.servicios.interfaces.PacienteServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pacientes")
@RequiredArgsConstructor
public class PacienteController {

    private final PacienteServicio pacienteServicio;
    private final ClinicaServicio clinicaServicio;

    @PutMapping("/editar-perfil/{codigoPaciente}")
    public ResponseEntity<MensajeDTO<String>> editarPerfil(@PathVariable int codigoPaciente, @Valid @RequestBody ActualizarPacienteDTO pacienteDTO) throws Exception{
        pacienteServicio.editarPerfil(codigoPaciente, pacienteDTO);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Paciente actualizado correctamente") );
    }

    @DeleteMapping("/eliminar/{idPaciente}")
    public ResponseEntity<MensajeDTO<String>> eliminarCuenta(@PathVariable int idPaciente) throws Exception{
        pacienteServicio.eliminarCuenta(idPaciente);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Paciente eliminado correctamente"));
    }

    @GetMapping("/datospaciente/{codigoPaciente}")
    public ResponseEntity<MensajeDTO<ActualizarPacienteDTO>> cargarDatosPaciente(@PathVariable int codigoPaciente) throws Exception {
        return ResponseEntity.ok().body( new MensajeDTO<>(false, pacienteServicio.cargarDatosPaciente(codigoPaciente)));
    }

    @PostMapping("/filtrar-medico-cita")
    public ResponseEntity<MensajeDTO<List<ItemMedicoCitaDTO>>> filtrarMedicoCita(@Valid @RequestBody FiltroCitaDTO citaDTO) throws Exception {
        System.out.println("Estoy en controlador"+ citaDTO.fecha()+citaDTO.especialidad());
        return ResponseEntity.ok().body( new MensajeDTO<>(false, pacienteServicio.filtrarMedicoCita(citaDTO)));
    }

    @PostMapping("/agendar-cita")
    public ResponseEntity<MensajeDTO<String>> agendarCita(@Valid @RequestBody CitaDTO citaDTO) throws Exception {
        pacienteServicio.agendarCita(citaDTO);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Cita agendada con éxito"));
    }

    @PostMapping("/crear-pqrs")
    public ResponseEntity<MensajeDTO<String>> crearPqrs(@Valid @RequestBody PQRSPacienteDTO pqrsPacienteDTO) throws Exception {
        pacienteServicio.crearPqrs(pqrsPacienteDTO);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Pqrs creada con éxito"));
    }

    @GetMapping("/listar-pqrs/{idPaciente}")
    public ResponseEntity<MensajeDTO<List<ItemPqrsDTO>>> listarPqrsPaciente(@PathVariable int idPaciente) throws Exception {
        return ResponseEntity.ok().body( new MensajeDTO<>(false, pacienteServicio.listarPqrsPaciente(idPaciente)));
    }

    @PostMapping("/responder-pqrs")
    public ResponseEntity<MensajeDTO<String>> responderPqrs(@Valid @RequestBody RespuestaPacientePqrsDTO respuestaPqrsDTO) throws Exception{
        pacienteServicio.responderPqrs(respuestaPqrsDTO);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Respuesta enviada con éxito"));
    }

    @GetMapping("/listar-citas/{codigoPaciente}")
    public ResponseEntity<MensajeDTO<List<ItemCitaPacienteDTO>>> listarCitasPaciente(@PathVariable int codigoPaciente) throws Exception {
        return ResponseEntity.ok().body( new MensajeDTO<>(false, pacienteServicio.listarCitasPaciente(codigoPaciente)));
    }

    @GetMapping("/listar-citas-pqrs/{idPaciente}")
    public ResponseEntity<MensajeDTO<List<ItemCitaPqrsPacienteDTO>>> listarCitasPqrsPaciente(@PathVariable int idPaciente) throws Exception {
        return ResponseEntity.ok().body( new MensajeDTO<>(false, pacienteServicio.listarCitasPqrsPaciente(idPaciente)));
    }

    @PostMapping("/buscar-consulta")
    public ResponseEntity<MensajeDTO<List<ItemConsultaPacienteDTO>>> buscarConsulta(@Valid @RequestBody BusquedaConsultaDTO busquedaConsultaDTO) throws Exception {
        System.out.println("llegue controlador");
        return ResponseEntity.ok().body( new MensajeDTO<>(false, pacienteServicio.buscarConsulta(busquedaConsultaDTO)));
    }

    @GetMapping("/listar-consultas-paciente/{idPaciente}")
    public ResponseEntity<MensajeDTO<List<ItemConsultaPacienteDTO>>> listarConsultasPaciente(@PathVariable int idPaciente) throws Exception {
        return ResponseEntity.ok().body( new MensajeDTO<>(false, pacienteServicio.listarConsultasPaciente(idPaciente)));
    }

    @GetMapping("/historial-mensajes-pqrs/{codigoPqrs}")
    public ResponseEntity<MensajeDTO<List<co.edu.uniquindio.proyecto.dto.clinica.MensajeDTO>>> mostrarHistorialMensajesPqrs(@PathVariable int codigoPqrs) throws Exception{
        return ResponseEntity.ok().body( new MensajeDTO<>(false, clinicaServicio.mostrarHistorialMensajesPqrs(codigoPqrs)));
    }

    @GetMapping("/tratamiento/{codigoConsulta}")
    public ResponseEntity<MensajeDTO<List<ItemTratamientoDTO>>> verTratamiento(@PathVariable int codigoConsulta) throws Exception {
        return ResponseEntity.ok().body( new MensajeDTO<>(false, clinicaServicio.verTratamiento(codigoConsulta)));
    }
}