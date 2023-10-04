package co.edu.uniquindio.proyecto.servicios.implementacion;

import co.edu.uniquindio.proyecto.dto.admin.RegistroTratamientoDTO;
import co.edu.uniquindio.proyecto.dto.clinica.EmailDTO;
import co.edu.uniquindio.proyecto.dto.medico.*;
import co.edu.uniquindio.proyecto.modelo.entidades.*;
import co.edu.uniquindio.proyecto.modelo.enums.EstadoFactura;
import co.edu.uniquindio.proyecto.repositorios.*;
import co.edu.uniquindio.proyecto.servicios.interfaces.MedicoServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MedicoServicioImpl implements MedicoServicio {

    private final CitaRepository citaRepository;
    private final MedicoRepository medicoRepository;
    private final DiaLibreRepository diaLibreRepository;
    private final ConsultaRepository consultaRepository;
    private final TratamientoRepository tratamientoRepository;
    private final FacturaRepository facturaRepository;
    private final PacienteRepository pacienteRepository;

    @Override
    public List<ItemCitaMedicoDTO> listarCitasPendientes(int codigoMedico) throws Exception {

        List<Cita> citasMedico = citaRepository.findAllByMedico_IdAndFechaAfterOrEqual(codigoMedico, LocalDate.now());

        if(citasMedico.isEmpty()){
            throw new Exception("No tiene citas");
        }

        List<ItemCitaMedicoDTO> respuesta = new ArrayList<>();

        for(Cita cita: citasMedico){
            respuesta.add(new ItemCitaMedicoDTO(cita.getId(), cita.getPaciente().getId(),
                    cita.getPaciente().getCedula(),
                    cita.getPaciente().getNombreCompleto(),
                    cita.getFecha(),
                    cita.getHora(),
                    cita.getMotivo()));
        }

        return respuesta;
    }

    @Override
    public int atenderCita(AtencionMedicoDTO atencionMedicoDTO) throws Exception {

        Optional<Cita> cita = citaRepository.findById(atencionMedicoDTO.idCita());

        if(cita.isEmpty()){
            throw new Exception("No existe una cita con ese codigo");
        }

        Consulta consultaNueva = new Consulta();
        Cita buscado = cita.get();

        consultaNueva.setFecha(buscado.getFecha());
        consultaNueva.setNotasMedico(atencionMedicoDTO.notasMedico());
        consultaNueva.setDiagnostico(atencionMedicoDTO.diagnostico());
        consultaNueva.setSintomas(atencionMedicoDTO.sintomas());
        consultaNueva.setCita(buscado);

        Consulta consultaRegistrada = consultaRepository.save(consultaNueva);

        registrarTratamiento(atencionMedicoDTO.tratamientoDTOList(), consultaRegistrada);

        return consultaRegistrada.getId();
    }

    private void registrarTratamiento(List<RegistroTratamientoDTO> registroTratamientoDTOS, Consulta consultaRegistrada) {
        for(RegistroTratamientoDTO registroTratamientoDTO: registroTratamientoDTOS) {
            Tratamiento tratamientoNuevo = new Tratamiento();
            tratamientoNuevo.setConsulta(consultaRegistrada);
            tratamientoNuevo.setDosis(registroTratamientoDTO.dosis());
            tratamientoNuevo.setObservaciones(registroTratamientoDTO.observaciones());
            tratamientoNuevo.setMedicamento(registroTratamientoDTO.nombreMedicamento());
            tratamientoRepository.save(tratamientoNuevo);
        }
    }

    @Override
    public List<ItemConsultaMedicoPacienteDTO> listarCitaPaciente(int codigoPaciente) throws Exception {

        List<Consulta> consultasPaciente = pacienteRepository.buscarConsultasPaciente(codigoPaciente);

        if(consultasPaciente.isEmpty()){
            throw new Exception("El paciente no tiene consultas");
        }

        List<ItemConsultaMedicoPacienteDTO> itemConsultaMedicoPacienteDTOList = new ArrayList<>();

        for(Consulta consulta : consultasPaciente){
            itemConsultaMedicoPacienteDTOList.add(new ItemConsultaMedicoPacienteDTO(
                    consulta.getId(),
                    consulta.getCita().getPaciente().getNombreCompleto(),
                    consulta.getFecha(),
                    consulta.getNotasMedico(),
                    consulta.getDiagnostico(),
                    consulta.getSintomas()
            ));
        }

        return itemConsultaMedicoPacienteDTOList;
    }

    @Override
    public int agendarDiaLibre(DiaLibreDTO diaLibreDTO) throws Exception {

        Optional<Medico> medico = medicoRepository.findById(diaLibreDTO.codigoMedico());

        if(medico.isEmpty()){
            throw new Exception("El medico no existe");
        }

        List<Cita> citas = citaRepository.buscarCitaEnFecha(diaLibreDTO.codigoMedico(), diaLibreDTO.fecha());

        if(!citas.isEmpty()){
            throw new Exception("Ese día se tiene cita pendiente, no se puede pedir el día " + diaLibreDTO.fecha() + " libre.");
        }

        Medico buscado = medico.get();

        DiaLibre diaLibreNuevo = new DiaLibre();

        diaLibreNuevo.setMedico(buscado);
        diaLibreNuevo.setEstado(true);
        diaLibreNuevo.setFecha(diaLibreDTO.fecha());

        DiaLibre diaLibreRegistrado = diaLibreRepository.save(diaLibreNuevo);

        return diaLibreRegistrado.getId();
    }


    @Override
    public List<ItemConsultaMedicoPacienteDTO> listarCitasRealizadasMedico(int codigoMedico) throws Exception {

        List<Consulta> consultasMedico = medicoRepository.buscarConsultasMedico(codigoMedico);

        if(consultasMedico.isEmpty()){
            throw new Exception("No hay consultas");
        }

        List<ItemConsultaMedicoPacienteDTO> itemConsultaMedicoPacienteDTOList = new ArrayList<>();

        for(Consulta consulta: consultasMedico){
            itemConsultaMedicoPacienteDTOList.add(new ItemConsultaMedicoPacienteDTO(
                    consulta.getId(),
                    consulta.getCita().getPaciente().getNombreCompleto(),
                    consulta.getFecha(),
                    consulta.getNotasMedico(),
                    consulta.getDiagnostico(),
                    consulta.getSintomas()
            ));
        }

        return itemConsultaMedicoPacienteDTOList;
    }

    @Override
    public int generarFactura(RegistrarFacturaDTO facturaDTO) throws Exception {

        Optional<Consulta> consulta = consultaRepository.findById(facturaDTO.idConsulta());

        if(consulta.isEmpty()){
            throw  new Exception("No existe la consulta con codigo "+ facturaDTO.idConsulta());
        }

        Factura facturaNuevo = new Factura();
        Consulta consultaBuscada = consulta.get();

        facturaNuevo.setFecha(LocalDate.now());
        facturaNuevo.setValor(5);
        facturaNuevo.setConcepto(facturaDTO.concepto());
        facturaNuevo.setEstado(EstadoFactura.ENVIADA);
        facturaNuevo.setConsulta(consultaBuscada);

        Factura facturaRegistrada = facturaRepository.save(facturaNuevo);

        return facturaRegistrada.getId();
    }

    @Override
    public DetalleFacturaDTO mostrarDetalleFactura(int codigoConsulta) throws Exception {

        Optional<Factura> factura = facturaRepository.findByConsulta_Id(codigoConsulta);

        if(factura.isEmpty()){
            throw new Exception("No existe una factura asociado a la consulta " + codigoConsulta);
        }

        Factura facturaBuscada = factura.get();

        return new DetalleFacturaDTO(facturaBuscada.getId(),
                facturaBuscada.getFecha(),
                facturaBuscada.getValor(),
                facturaBuscada.getEstado());
    }

    @Override
    public EmailDTO enviarCorreoFactura(RegistrarFacturaDTO facturaDTO) {
        return null;
    }
}
