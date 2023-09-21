package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.dto.*;

import java.util.List;

public interface AdministradorServicio {

    String crearMedico(RegistroMedicoDTO medicoDTO) throws Exception;

    RegistroMedicoDTO obtenerMedico(int codigoMedico) throws Exception;

    String actualizarMedico(int codigoMedico, ActualizarMedicoDTO medico) throws Exception;

    String eliminarMedico(int codigoMedico) throws Exception;

    List<ItemMedicoDTO> listarMedicos() throws Exception;

    List<PQRSAdminDTO> listarPqrs() throws Exception;

    String cambiarEstadoPqrs(EstadoPqrsDTO estadoPqrsDTO) throws Exception;

    DetalleConsultaPqrsDTO mostrarDetalleConsultaPqrs(int codigoPqrs) throws Exception;

    String responderPqrs(RespuestaAdminPqrsDTO respuestaPqrsDTO) throws Exception;

    List<CitasAdminDTO> listarCitas() throws Exception;

    List<CitasAdminDTO> listarCitas(int codigoMedico) throws Exception;

}
