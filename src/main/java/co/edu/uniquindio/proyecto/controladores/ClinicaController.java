package co.edu.uniquindio.proyecto.controladores;

import co.edu.uniquindio.proyecto.dto.autenticacionJwt.MensajeDTO;
import co.edu.uniquindio.proyecto.dto.clinica.CambioPasswordDTO;
import co.edu.uniquindio.proyecto.dto.clinica.EpsDTO;
import co.edu.uniquindio.proyecto.dto.clinica.ItemTratamientoDTO;
import co.edu.uniquindio.proyecto.modelo.enums.*;
import co.edu.uniquindio.proyecto.servicios.interfaces.ClinicaServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clinica")
@RequiredArgsConstructor
public class ClinicaController {

    private final ClinicaServicio clinicaServicio;

    @GetMapping("/estados-pqrs")
    public ResponseEntity<MensajeDTO<List<EstadoPqrs>>> cargarListaEstadosPqrs(){
        return ResponseEntity.ok().body( new MensajeDTO<>(false, clinicaServicio.cargarListaEstadosPqrs()));
    }

    @GetMapping("/tipos-sangre")
    public ResponseEntity<MensajeDTO<List<TipoSangre>>> cargarTiposSangre(){
        return ResponseEntity.ok().body( new MensajeDTO<>(false, clinicaServicio.cargarTiposSangre()));
    }

    @GetMapping("/eps")
    public ResponseEntity<MensajeDTO<List<EpsDTO>>> cargarEps(){
        return ResponseEntity.ok().body( new MensajeDTO<>(false, clinicaServicio.cargarEps()));
    }

    @GetMapping("/medicamentos")
    public ResponseEntity<MensajeDTO<List<Medicamento>>> cargarMedicamentos(){
        return ResponseEntity.ok().body( new MensajeDTO<>(false, clinicaServicio.cargarMedicamentos()));
    }

    @GetMapping("/ciudades")
    public ResponseEntity<MensajeDTO<List<Ciudad>>> cargarCiudades(){
        return ResponseEntity.ok().body( new MensajeDTO<>(false, clinicaServicio.cargarCiudades()));
    }

    @GetMapping("/especialidades")
    public ResponseEntity<MensajeDTO<List<Especialidad>>> cargarEspecialidades(){
        return ResponseEntity.ok().body( new MensajeDTO<>(false, clinicaServicio.cargarEspecialidades()));
    }

    @GetMapping("/enviar-link-recuperacion/{email}")
    public ResponseEntity<MensajeDTO<String>> enviarLinkRecuperacion(@PathVariable String email) throws Exception{
        clinicaServicio.enviarLinkRecuperacion(email);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Revisa el correo e ingresa al link de recuperación enviado"));
    }
}
