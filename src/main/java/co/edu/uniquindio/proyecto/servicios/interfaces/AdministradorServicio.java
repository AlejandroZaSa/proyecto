package co.edu.uniquindio.proyecto.servicios.interfaces;

import co.edu.uniquindio.proyecto.dto.admin.*;
import co.edu.uniquindio.proyecto.dto.clinica.ItemPqrsDTO;

import java.util.List;

public interface AdministradorServicio {

    int crearMedico(RegistroMedicoDTO medicoDTO) throws Exception;

    ActualizarMedicoDTO obtenerMedico(int codigoMedico) throws Exception;

    int actualizarMedico(int codigoMedico, ActualizarMedicoDTO medico) throws Exception;

    void eliminarMedico(int codigoMedico) throws Exception;

    List<ItemMedicoDTO> listarMedicos() throws Exception;

    List<ItemPqrsDTO> listarPqrs() throws Exception;

    void cambiarEstadoPqrs(EstadoPqrsDTO estadoPqrsDTO) throws Exception;

    DetalleConsultaPqrsDTO mostrarDetalleConsultaPqrs(int codigoPqrs) throws Exception;

    int responderPqrs(RespuestaAdminPqrsDTO respuestaPqrsDTO) throws Exception;

    List<ItemCitaAdminDTO> listarCitas() throws Exception;
}
