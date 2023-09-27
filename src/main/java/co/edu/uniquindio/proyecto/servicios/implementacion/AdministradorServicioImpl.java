package co.edu.uniquindio.proyecto.servicios.implementacion;

import co.edu.uniquindio.proyecto.dto.admin.*;
import co.edu.uniquindio.proyecto.modelo.entidades.Medico;
import co.edu.uniquindio.proyecto.modelo.enums.Ciudad;
import co.edu.uniquindio.proyecto.modelo.enums.Especialidad;
import co.edu.uniquindio.proyecto.repositorios.MedicoRepository;
import co.edu.uniquindio.proyecto.servicios.interfaces.AdministradorServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdministradorServicioImpl implements AdministradorServicio{

    private final MedicoRepository medicoRepository;

    @Override
    public int crearMedico(RegistroMedicoDTO medicoDTO) throws Exception {

        Medico medicoNuevo = new Medico();

        medicoNuevo.setNombreCompleto(medicoDTO.nombre());
        medicoNuevo.setCedula(medicoDTO.cedula());
        medicoNuevo.setCiudad(Ciudad.values()[medicoDTO.ciudad()]);
        medicoNuevo.setTelefono(medicoDTO.telefono());
        medicoNuevo.setFoto(medicoDTO.foto());
        medicoNuevo.setEspecialidad(Especialidad.values()[medicoDTO.especialidad()]);

        medicoNuevo.setEmail(medicoDTO.email());
        medicoNuevo.setContrasenia(medicoDTO.password());

        if(estaRepetidoCorreo(medicoDTO.email())){
            throw new Exception ("El correo ya está en uso");
        }
        if(estaRepetidaCedula(medicoDTO.cedula())){
            throw new Exception ("La cedula ya se encuentra registrada");
        }

        Medico medicoRegistrado = medicoRepository.save(medicoNuevo);

        return medicoRegistrado.getId();
    }

    private boolean estaRepetidoCorreo(String email) {
        return medicoRepository.buscarPorCorreo(email) == null;
    }

    private boolean estaRepetidaCedula(String cedula) {
        return medicoRepository.buscarPorCedula(cedula) == null;
    }

    @Override
    public ActualizarMedicoDTO obtenerMedico(int codigoMedico) throws Exception {
        return null;
    }

    @Override
    public int actualizarMedico(int codigoMedico, ActualizarMedicoDTO medico) throws Exception {
        return 0;
    }

    @Override
    public void eliminarMedico(int codigoMedico) throws Exception {

    }

    @Override
    public List<ItemMedicoDTO> listarMedicos() throws Exception {
        return null;
    }

    @Override
    public List<ItemPQRSAdminDTO> listarPqrs() throws Exception {
        return null;
    }

    @Override
    public void cambiarEstadoPqrs(EstadoPqrsDTO estadoPqrsDTO) throws Exception {

    }

    @Override
    public DetalleConsultaPqrsDTO mostrarDetalleConsultaPqrs(int codigoPqrs) throws Exception {
        return null;
    }

    @Override
    public int responderPqrs(RespuestaAdminPqrsDTO respuestaPqrsDTO) throws Exception {
        return 0;
    }

    @Override
    public List<ItemCitaAdminDTO> listarCitas() throws Exception {
        return null;
    }
}
