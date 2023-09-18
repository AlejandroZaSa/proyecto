package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.dto.AtencionMedicoDTO;
import co.edu.uniquindio.proyecto.dto.CitaMedicoDTO;
import co.edu.uniquindio.proyecto.dto.DiaLibreDTO;

import java.util.List;

public interface MedicoServicio {

    List<CitaMedicoDTO> listarCitasPendientes(int codigo) throws Exception;

    void atenderCita(AtencionMedicoDTO atencionMedicoDTO) throws Exception;

    List<CitaMedicoDTO> listarCitaPaciente(int codigoPaciente) throws Exception;//historial medico

    void agendarDiaLibre(DiaLibreDTO diaLibreDTO) throws Exception;

    List<CitaMedicoDTO> listarCitasRealizadasMedico(int codigo) throws Exception;
}
