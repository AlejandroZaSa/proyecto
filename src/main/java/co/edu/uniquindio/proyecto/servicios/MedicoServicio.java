package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.dto.*;

import java.util.List;

public interface MedicoServicio {
    List<CitaMedicoDTO> listarCitasPendientes(int codigoMedico) throws Exception;

    String atenderCita(AtencionMedicoDTO atencionMedicoDTO) throws Exception;

    //ver historial de consultas que ya lo tiene el paciente con tratamiento se deberían compartir no?
    List<CitaMedicoDTO> listarCitaPaciente(int codigoPaciente) throws Exception;//historial medico

    //no faltaria mandar el codigo del medico
    String agendarDiaLibre(DiaLibreDTO diaLibreDTO) throws Exception;

    List<CitaMedicoDTO> listarCitasRealizadasMedico(int codigo) throws Exception;

    String generarFactura(FacturaDTO facturaDTO) throws Exception;

    //no esta bien
    EmailDTO enviarCorreoFactura(FacturaDTO facturaDTO);
}
