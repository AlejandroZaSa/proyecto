package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.dto.*;

import java.util.List;

public interface AdministradorServicio {

    int crearMedico(MedicoDTO medicoDTO) throws Exception;

    int actualizarMedico(int codigo, MedicoDTO medico) throws Exception;

    void eliminarMedico(int codigo) throws Exception;

    List<InfoMedicoAdminDTO> listarMedicos();

    DetalleMedicoDTO obtenerMedico();

    List<PQRSAdminDTO> listarPqrs();

    String responderPqrs(RespuestaPqrsDTO respuestaPqrsDTO) throws Exception;

    DetallePqrsDTO verDetallePqrs(int codigo) throws Exception;

    List<CitasAdminDTO> listarCitas();
}
