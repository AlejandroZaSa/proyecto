package co.edu.uniquindio.proyecto.servicios.implementacion;

import co.edu.uniquindio.proyecto.dto.clinica.EpsDTO;
import co.edu.uniquindio.proyecto.dto.clinica.ItemTratamientoDTO;
import co.edu.uniquindio.proyecto.dto.clinica.MensajeDTO;
import co.edu.uniquindio.proyecto.modelo.entidades.RespuestaAdmin;
import co.edu.uniquindio.proyecto.modelo.entidades.RespuestaPaciente;
import co.edu.uniquindio.proyecto.modelo.entidades.Tratamiento;
import co.edu.uniquindio.proyecto.modelo.enums.Ciudad;
import co.edu.uniquindio.proyecto.modelo.enums.EstadoPqrs;
import co.edu.uniquindio.proyecto.modelo.enums.Medicamento;
import co.edu.uniquindio.proyecto.modelo.enums.TipoSangre;
import co.edu.uniquindio.proyecto.repositorios.RespuestaAdminRepository;
import co.edu.uniquindio.proyecto.repositorios.RespuestaPacienteRepository;
import co.edu.uniquindio.proyecto.repositorios.TratamientoRepository;
import co.edu.uniquindio.proyecto.servicios.interfaces.ClinicaServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClinicaServicioImpl implements ClinicaServicio {

    private final RespuestaAdminRepository respuestaAdminRepository;
    private final RespuestaPacienteRepository respuestaPacienteRepository;
    private final TratamientoRepository tratamientoRepository;

    @Override
    public List<EstadoPqrs> cargarListaEstadosPqrs() {



        return null;
    }

    @Override
    public List<TipoSangre> cargarTiposSangre() {

        return null;
    }

    @Override
    public List<EpsDTO> cargarEps() {


        return null;
    }

    @Override
    public List<Medicamento> cargarMedicamentos() {

        return null;
    }

    @Override
    public List<Ciudad> cargarCiudades() {


        return null;
    }

    @Override
    public List<MensajeDTO> mostrarHistorialMensajesPqrs(int codigoPqrs) throws Exception {

        List<RespuestaAdmin> respuestaAdmin = respuestaAdminRepository.findAllByPqrs_id(codigoPqrs);
        List<RespuestaPaciente> respuestaPaciente = respuestaPacienteRepository.findAllByPqrs_id(codigoPqrs);

        List<MensajeDTO> mensajes = new ArrayList<>();

        for(RespuestaAdmin respuestaA : respuestaAdmin){
            mensajes.add(new MensajeDTO(
                    respuestaA.getId(),
                    respuestaA.getAdministrador().getId(),
                    respuestaA.getAdministrador().getEmail(),
                    respuestaA.getMensaje(),
                    respuestaA.getFecha(),
                    respuestaA.getPqrs().getNumeroRadicado()));
        }

        for(RespuestaPaciente respuestaP : respuestaPaciente){
            mensajes.add(new MensajeDTO(
                    respuestaP.getId(),
                    respuestaP.getPaciente().getId(),
                    respuestaP.getPaciente().getNombreCompleto(),
                    respuestaP.getMensaje(),
                    respuestaP.getFecha(),
                    respuestaP.getPqrs().getNumeroRadicado()));
        }

        Collections.sort(mensajes, (mensajeA, mensajeB) -> mensajeA.fecha().compareTo(mensajeB.fecha()));

        return mensajes;
    }

    @Override
    public void enviarLinkRecuperacion(String email) throws Exception {

    }

    @Override
    public void cambiarPassword(int codigoUsuario, String nuevaPassword) throws Exception {



    }

    @Override
    public List<ItemTratamientoDTO> verTratamiento(int codigoConsulta) {

        List<Tratamiento> listaTratamientos = tratamientoRepository.findAllByConsulta_id(codigoConsulta);
        List<ItemTratamientoDTO> respuesta = new ArrayList<>();

        for(Tratamiento tratamiento : listaTratamientos){
            respuesta.add(new ItemTratamientoDTO(tratamiento.getDosis(),
                    tratamiento.getObservaciones(),
                    tratamiento.getMedicamento()));
        }

        return respuesta;
    }
}
