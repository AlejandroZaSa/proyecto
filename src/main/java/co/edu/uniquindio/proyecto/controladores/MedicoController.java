package co.edu.uniquindio.proyecto.controladores;

import co.edu.uniquindio.proyecto.dto.autenticacionJwt.MensajeDTO;
import co.edu.uniquindio.proyecto.dto.clinica.ItemTratamientoDTO;
import co.edu.uniquindio.proyecto.dto.medico.*;
import co.edu.uniquindio.proyecto.servicios.interfaces.ClinicaServicio;
import co.edu.uniquindio.proyecto.servicios.interfaces.MedicoServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/medicos")
@RequiredArgsConstructor
public class MedicoController {

    private final MedicoServicio medicoServicio;
    private final ClinicaServicio clinicaServicio;

    @GetMapping("/listar-citas-pendientes/{codigoMedico}")
    public ResponseEntity<MensajeDTO<List<ItemCitaMedicoDTO>>> listarCitasPendientes(@PathVariable int codigoMedico) throws Exception {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, medicoServicio.listarCitasPendientes(codigoMedico)));
    }

    @PostMapping("/atender-cita")
    public ResponseEntity<MensajeDTO<String>> atenderCita(@Valid @RequestBody AtencionMedicoDTO atencionMedicoDTO) throws Exception {
        int codigoConsulta = medicoServicio.atenderCita(atencionMedicoDTO);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Consulta guardada correctamente con código " + codigoConsulta));
    }

    @GetMapping("/listar-cita-paciente/{codigoPaciente}")
    public ResponseEntity<MensajeDTO<List<ItemConsultaMedicoPacienteDTO>>> listarCitaPaciente(@PathVariable int codigoPaciente) throws Exception {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, medicoServicio.listarCitaPaciente(codigoPaciente)));
    }

    @PostMapping("/agendar-dia-libre")
    public ResponseEntity<MensajeDTO<String>> agendarDiaLibre(@Valid @RequestBody DiaLibreDTO diaLibreDTO) throws Exception {
        medicoServicio.agendarDiaLibre(diaLibreDTO);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Dia libre programado con éxito"));
    }

    @GetMapping("/listar-citas-realizadas/{codigoMedico}")
    public ResponseEntity<MensajeDTO<List<ItemConsultaMedicoPacienteDTO>>> listarCitasRealizadasMedico(@PathVariable  int codigoMedico) throws Exception {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, medicoServicio.listarCitasRealizadasMedico(codigoMedico)));
    }

    @PostMapping("/generar-factura/{idConsulta}")
    public ResponseEntity<MensajeDTO<String>> generarFactura(@PathVariable int idConsulta) throws Exception {
        medicoServicio.generarFactura(idConsulta);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Factura generada con éxito"));
    }

    @GetMapping("/detalle-factura/{codigoConsulta}")
    public ResponseEntity<MensajeDTO<DetalleFacturaDTO>> mostrarDetalleFactura(@PathVariable int codigoConsulta) throws Exception {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, medicoServicio.mostrarDetalleFactura(codigoConsulta)));
    }

    @GetMapping("/tratamiento/{codigoConsulta}")
    public ResponseEntity<MensajeDTO<List<ItemTratamientoDTO>>> verTratamiento(@PathVariable int codigoConsulta) throws Exception {
        return ResponseEntity.ok().body( new MensajeDTO<>(false, clinicaServicio.verTratamiento(codigoConsulta)));
    }

    @GetMapping("/listar-dias-libres/{codigoMedico}")
    public ResponseEntity<MensajeDTO<List<DiaLibreDTO>>> listarDiasLibres(@PathVariable int codigoMedico) throws Exception {
        return ResponseEntity.ok().body( new MensajeDTO<>(false, medicoServicio.listarDiasLibres(codigoMedico)));
    }
}