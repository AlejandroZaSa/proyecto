package co.edu.uniquindio.proyecto.servicios.interfaces;

import co.edu.uniquindio.proyecto.dto.clinica.EmailDTO;
import co.edu.uniquindio.proyecto.dto.admin.RegistroTratamientoDTO;
import co.edu.uniquindio.proyecto.dto.medico.*;

import java.util.List;

public interface MedicoServicio {
    List<ItemCitaMedicoDTO> listarCitasPendientes(int codigoMedico) throws Exception;

    int atenderCita(AtencionMedicoDTO atencionMedicoDTO) throws Exception;

    List<ItemConsultaMedicoPacienteDTO> listarCitaPaciente(int codigoPaciente) throws Exception;

    int agendarDiaLibre(DiaLibreDTO diaLibreDTO) throws Exception;
    List<ItemConsultaMedicoPacienteDTO> listarCitasRealizadasMedico(int codigoMedico) throws Exception;
    int generarFactura(RegistrarFacturaDTO facturaDTO) throws Exception;

    DetalleFacturaDTO mostrarDetalleFactura(int codigoConsulta) throws Exception;
    EmailDTO enviarCorreoFactura(RegistrarFacturaDTO facturaDTO);
}
