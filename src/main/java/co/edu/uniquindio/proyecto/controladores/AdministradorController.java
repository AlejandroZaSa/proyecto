package co.edu.uniquindio.proyecto.controladores;

import co.edu.uniquindio.proyecto.dto.admin.*;
import co.edu.uniquindio.proyecto.dto.autenticacionJwt.MensajeDTO;
import co.edu.uniquindio.proyecto.dto.clinica.ItemPqrsDTO;
import co.edu.uniquindio.proyecto.servicios.interfaces.AdministradorServicio;
import co.edu.uniquindio.proyecto.servicios.interfaces.ClinicaServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/administrador")
@RequiredArgsConstructor
public class AdministradorController {

    private final AdministradorServicio administradorServicio;
    private final ClinicaServicio clinicaServicio;

    @PostMapping("/registrar-medico")
    public ResponseEntity<MensajeDTO<String>> crearMedico(@Valid @RequestBody RegistroMedicoDTO medicoDTO) throws Exception{
        administradorServicio.crearMedico(medicoDTO);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Medico registrado correctamente"));
    }

    @GetMapping("/obtener-medico/{codigoMedico}")
    public ResponseEntity<MensajeDTO<ActualizarMedicoDTO>> obtenerMedico(@PathVariable int codigoMedico) throws Exception {
        return ResponseEntity.ok().body( new MensajeDTO<>(false, administradorServicio.obtenerMedico(codigoMedico)));
    }

    @PutMapping("/actualizar-medico/{codigoMedico}")
    public ResponseEntity<MensajeDTO<String>> actualizarMedico(@PathVariable int codigoMedico, @Valid @RequestBody ActualizarMedicoDTO medico) throws Exception{
        administradorServicio.actualizarMedico(codigoMedico, medico);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Medico actualizado correctamente") );
    }

    @DeleteMapping("/eliminar-medico/{codigoMedico}")
    public ResponseEntity<MensajeDTO<String>> eliminarCuenta(@PathVariable int codigoMedico) throws Exception{
        administradorServicio.eliminarMedico(codigoMedico);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Medico eliminado correctamente"));
    }

    @GetMapping("/listar-medicos")
    public ResponseEntity<MensajeDTO<List<ItemMedicoDTO>>> listarMedicos() throws Exception {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, administradorServicio.listarMedicos()));
    }

    @GetMapping("/listar-pqrs")
    public ResponseEntity<MensajeDTO<List<ItemPqrsDTO>>> listarPqrs() throws Exception {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, administradorServicio.listarPqrs()));
    }

    @PutMapping("/cambiar-estado-pqrs")
    public ResponseEntity<MensajeDTO<String>> cambiarEstadoPqrs(@Valid @RequestBody EstadoPqrsDTO estadoPqrsDTO) throws Exception{
        administradorServicio.cambiarEstadoPqrs(estadoPqrsDTO);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Estado actualizado correctamente") );
    }

    @GetMapping("/detalle-consulta-pqrs/{codigoPqrs}")
    public ResponseEntity<MensajeDTO<DetalleConsultaPqrsDTO>> mostrarDetalleConsultaPqrs(@PathVariable int codigoPqrs) throws Exception{
        return ResponseEntity.ok().body( new MensajeDTO<>(false,administradorServicio.mostrarDetalleConsultaPqrs(codigoPqrs)));
    }

    @PostMapping("/responder-pqrs")
    public ResponseEntity<MensajeDTO<String>> responderPqrs(@Valid @RequestBody RespuestaAdminPqrsDTO respuestaPqrsDTO) throws Exception{
        administradorServicio.responderPqrs(respuestaPqrsDTO);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Respuesta enviada correctamente"));
    }

    @GetMapping("/listar-citas")
    public ResponseEntity<MensajeDTO<List<ItemCitaAdminDTO>>> listarCitas() throws Exception{
        return ResponseEntity.ok().body(new MensajeDTO<>(false, administradorServicio.listarCitas()));
    }

    @GetMapping("/historial-mensajes-pqrs/{codigoPqrs}")
    public ResponseEntity<co.edu.uniquindio.proyecto.dto.autenticacionJwt.MensajeDTO<List<co.edu.uniquindio.proyecto.dto.clinica.MensajeDTO>>> mostrarHistorialMensajesPqrs(@PathVariable int codigoPqrs) throws Exception {
        return ResponseEntity.ok().body( new co.edu.uniquindio.proyecto.dto.autenticacionJwt.MensajeDTO<>(false, clinicaServicio.mostrarHistorialMensajesPqrs(codigoPqrs)));
    }
}
