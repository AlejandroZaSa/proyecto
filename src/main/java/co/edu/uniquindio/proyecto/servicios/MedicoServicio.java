package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.dto.clinica.EmailDTO;
import co.edu.uniquindio.proyecto.dto.medico.*;

import java.util.List;

public interface MedicoServicio {
    List<ItemCitaMedicoDTO> listarCitasPendientes(int codigoMedico) throws Exception;

    int atenderCita(AtencionMedicoDTO atencionMedicoDTO) throws Exception;

    //esta es la dudosa
    List<ItemConsultaMedicoPacienteDTO> listarCitaPaciente(int codigoPaciente) throws Exception;

    int agendarDiaLibre(DiaLibreDTO diaLibreDTO) throws Exception;

    List<ItemConsultaMedicoPacienteDTO> listarCitasRealizadasMedico(int codigoMedico) throws Exception;

    int generarFactura(FacturaDTO facturaDTO) throws Exception;

    EmailDTO enviarCorreoFactura(FacturaDTO facturaDTO);
}
