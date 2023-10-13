package co.edu.uniquindio.proyecto.servicios.implementacion;

import co.edu.uniquindio.proyecto.dto.admin.RegistroTratamientoDTO;
import co.edu.uniquindio.proyecto.dto.clinica.EmailDTO;
import co.edu.uniquindio.proyecto.dto.medico.*;
import co.edu.uniquindio.proyecto.modelo.entidades.*;
import co.edu.uniquindio.proyecto.modelo.enums.EstadoCita;
import co.edu.uniquindio.proyecto.repositorios.*;
import co.edu.uniquindio.proyecto.servicios.interfaces.EmailServicio;
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
    private final EmailServicio emailServicio;

    @Override
    public List<ItemCitaMedicoDTO> listarCitasPendientes(int codigoMedico) throws Exception {

        List<Cita> citasMedico = citaRepository.findAllByMedico_IdAndFechaGreaterThanEqual(codigoMedico, LocalDate.now());

        if(citasMedico.isEmpty()){
            throw new Exception("No tienes citas");
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
            throw new Exception("No existe una cita con el código"+atencionMedicoDTO.idCita());
        }else if(cita.get().getFecha().isAfter(LocalDate.now())){
            throw new Exception("La cita no se puede atender dado que la fecha de atención es: "+cita.get().getFecha());
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

        buscado.setEstadoCita(EstadoCita.COMPLETADA);

        citaRepository.save(buscado);

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
            throw new Exception("El medico con código "+diaLibreDTO.codigoMedico()+" no existe");
        }

        Optional<DiaLibre> diaLibre = diaLibreRepository.findByMedico_IdAndFechaGreaterThanEqual(diaLibreDTO.codigoMedico(),LocalDate.now());

        if(diaLibre.isPresent()){
            throw new Exception("Ya tiene un día libre activo con fecha "+diaLibre.get().getFecha());
        }

        List<Cita> citas = citaRepository.obtenerCitasFecha(diaLibreDTO.codigoMedico(), diaLibreDTO.fecha());

        if(!citas.isEmpty()){
            throw new Exception("Ese día tienes cita(s) pendiente(s), no se puede pedir el día " + diaLibreDTO.fecha() + " libre. Revisa tu agenda");
        }

        Medico buscado = medico.get();

        DiaLibre diaLibreNuevo = new DiaLibre();

        diaLibreNuevo.setMedico(buscado);
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
    public int generarFactura(int idConsulta) throws Exception {

        Optional<Consulta> consulta = consultaRepository.findById(idConsulta);

        if(consulta.isEmpty()){
            throw  new Exception("No existe la consulta con codigo "+ idConsulta);
        }

        Factura facturaNuevo = new Factura();
        Consulta consultaBuscada = consulta.get();

        facturaNuevo.setFecha(LocalDate.now());
        facturaNuevo.setValor(consultaBuscada.getCita().getMedico().getCostoConsulta() - (consultaBuscada.getCita().getMedico().getCostoConsulta()*
                consultaBuscada.getCita().getPaciente().getEps().getPorcentajeConsulta()));
        facturaNuevo.setConcepto("Consulta por: " +consultaBuscada.getCita().getMedico().getEspecialidad());
        facturaNuevo.setConsulta(consultaBuscada);
        Factura facturaRegistrada = facturaRepository.save(facturaNuevo);

        emailServicio.enviarEmail(new EmailDTO("Factura Consulta",
                consultaBuscada.getCita().getPaciente().getEmail(), facturaRegistrada.getConcepto() + " Valor: " +
                facturaRegistrada.getValor()));

        return facturaRegistrada.getId();
    }

    @Override
    public DetalleFacturaDTO mostrarDetalleFactura(int codigoConsulta) throws Exception {

        Optional<Factura> factura = facturaRepository.findByConsulta_Id(codigoConsulta);

        if(factura.isEmpty()){
            throw new Exception("No existe una factura asociado a la consulta con código " + codigoConsulta);
        }

        Factura facturaBuscada = factura.get();

        return new DetalleFacturaDTO(facturaBuscada.getId(),
                facturaBuscada.getFecha(),
                facturaBuscada.getValor(),
                facturaBuscada.getConcepto());
    }
}
