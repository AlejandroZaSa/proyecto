package co.edu.uniquindio.proyecto.servicios.implementacion;

import co.edu.uniquindio.proyecto.dto.admin.RegistroTratamientoDTO;
import co.edu.uniquindio.proyecto.dto.clinica.EmailDTO;
import co.edu.uniquindio.proyecto.dto.medico.*;
import co.edu.uniquindio.proyecto.servicios.interfaces.MedicoServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicoServicioImpl implements MedicoServicio {
    @Override
    public List<ItemCitaMedicoDTO> listarCitasPendientes(int codigoMedico) throws Exception {
        return null;
    }

    @Override
    public int atenderCita(AtencionMedicoDTO atencionMedicoDTO) throws Exception {
        return 0;
    }

    @Override
    public List<ItemConsultaMedicoPacienteDTO> listarCitaPaciente(int codigoPaciente) throws Exception {
        return null;
    }

    @Override
    public int agendarDiaLibre(DiaLibreDTO diaLibreDTO) throws Exception {
        return 0;
    }

    @Override
    public void registrarTratamiento(List<RegistroTratamientoDTO> tratamientoDTOList) throws Exception {

    }

    @Override
    public List<ItemConsultaMedicoPacienteDTO> listarCitasRealizadasMedico(int codigoMedico) throws Exception {
        return null;
    }

    @Override
    public int generarFactura(RegistrarFacturaDTO facturaDTO) throws Exception {
        return 0;
    }

    @Override
    public DetalleFacturaDTO mostrarDetalleFactura(int codigoConsulta) throws Exception {
        return null;
    }

    @Override
    public EmailDTO enviarCorreoFactura(RegistrarFacturaDTO facturaDTO) {
        return null;
    }
}
