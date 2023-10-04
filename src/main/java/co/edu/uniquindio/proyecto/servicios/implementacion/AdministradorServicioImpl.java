package co.edu.uniquindio.proyecto.servicios.implementacion;

import co.edu.uniquindio.proyecto.dto.admin.*;
import co.edu.uniquindio.proyecto.dto.clinica.ItemPqrsDTO;
import co.edu.uniquindio.proyecto.dto.clinica.ItemTratamientoDTO;
import co.edu.uniquindio.proyecto.modelo.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.*;
import co.edu.uniquindio.proyecto.servicios.interfaces.AdministradorServicio;
import co.edu.uniquindio.proyecto.servicios.interfaces.ClinicaServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdministradorServicioImpl implements AdministradorServicio{

    private final MedicoRepository medicoRepository;
    private final HorarioRepository horarioRepository;
    private final PqrsRepository pqrsRepository;
    private final RespuestaAdminRepository respuestaAdminRepository;
    private final AdminRepository adminRepository;
    private final ConsultaRepository consultaRepository;
    private final ClinicaServicio clinicaServicio;

    @Override
    public int crearMedico(RegistroMedicoDTO medicoDTO) throws Exception {

        if(!estaRepetidoCorreo(medicoDTO.email())){
            throw new Exception ("El correo ya está en uso");
        }
        if(!estaRepetidaCedula(medicoDTO.cedula())){
            throw new Exception ("La cedula ya se encuentra registrada");
        }

        Medico medicoNuevo = new Medico();

        medicoNuevo.setNombreCompleto(medicoDTO.nombre());
        medicoNuevo.setCedula(medicoDTO.cedula());
        medicoNuevo.setCiudad(medicoDTO.ciudad());
        medicoNuevo.setTelefono(medicoDTO.telefono());
        medicoNuevo.setFoto(medicoDTO.foto());
        medicoNuevo.setEspecialidad(medicoDTO.especialidad());
        medicoNuevo.setCostoConsulta(medicoDTO.precioConsulta());
        medicoNuevo.setEstado(true);

        medicoNuevo.setEmail(medicoDTO.email());
        medicoNuevo.setContrasenia(medicoDTO.password());

        Medico medicoRegistrado = medicoRepository.save(medicoNuevo);

        registrarHorario(medicoDTO.horarioDTO(),medicoRegistrado);

        return medicoRegistrado.getId();
    }

    private void registrarHorario(List<HorarioDTO> horarioDTOS, Medico medicoRegistrado) {

        for(HorarioDTO horario: horarioDTOS){
            Horario horarioNuevo = new Horario();
            horarioNuevo.setMedico(medicoRegistrado);
            horarioNuevo.setDia(horario.dia());
            horarioNuevo.setHoraInicio(horario.horaInicio());
            horarioNuevo.setHoraFin(horario.horaFin());
            horarioRepository.save(horarioNuevo);
        }
    }

    private boolean estaRepetidoCorreo(String email) {
        return medicoRepository.buscarPorCorreo(email) == null;
    }

    private boolean estaRepetidaCedula(String cedula) {
        return medicoRepository.buscarPorCedula(cedula) == null;
    }

    @Override
    public ActualizarMedicoDTO obtenerMedico(int codigoMedico) throws Exception {

        Optional<Medico> opcional = medicoRepository.findById(codigoMedico);

        if(opcional.isEmpty()){
            throw new Exception("No existe un medico con el codigo: "+ codigoMedico);
        }

        Medico buscado = opcional.get();

        List<Horario> horarios = horarioRepository.findAllByMedico_Id(codigoMedico);
        List<HorarioDTO> respuesta = new ArrayList<>();

        for(Horario h : horarios){
            respuesta.add( new HorarioDTO(
                    h.getDia(),
                    h.getHoraInicio(),
                    h.getHoraFin()
            ) );
        }

        return new ActualizarMedicoDTO(
                buscado.getCedula(),
                buscado.getNombreCompleto(),
                buscado.getFoto(),
                buscado.getCiudad(),
                buscado.getTelefono(),
                buscado.getEmail(),
                buscado.getEspecialidad(),
                buscado.getCostoConsulta(),
                respuesta);
    }

    @Override
    public int actualizarMedico(int codigoMedico, ActualizarMedicoDTO medico) throws Exception {

        Optional<Medico> opcional = medicoRepository.findById(codigoMedico);

        if(opcional.isEmpty()){
            throw new Exception("No existe un medico con el codigo: "+ codigoMedico);
        }

        Medico buscado = opcional.get();

        if(!estaRepetidoCorreo(medico.email()) && (!buscado.getEmail().equals(medico.email())) ){
            throw new Exception ("El correo ya está en uso");
        }
        if(!estaRepetidaCedula(medico.cedula()) && (!buscado.getCedula().equals(medico.cedula()))){
            throw new Exception ("La cedula ya se encuentra registrada");
        }

        buscado.setNombreCompleto(medico.nombre());
        buscado.setCedula(medico.cedula());
        buscado.setCiudad(medico.ciudad());
        buscado.setTelefono(medico.telefono());
        buscado.setFoto(medico.foto());
        buscado.setCostoConsulta(medico.precioConsulta());
        buscado.setEspecialidad(medico.especialidad());
        buscado.setEmail(medico.email());

        medicoRepository.save(buscado);

        List<Horario> horarios  = horarioRepository.findAllByMedico_Id(buscado.getId());
        int posicion = 0;
        for(Horario horario: horarios){
            horario.setMedico(buscado);
            horario.setDia(medico.horarioDTO().get(posicion).dia());
            horario.setHoraInicio(medico.horarioDTO().get(posicion).horaInicio());
            horario.setHoraFin(medico.horarioDTO().get(posicion).horaFin());
            horarioRepository.save(horario);
            posicion++;
        }

        return buscado.getId();
    }

    @Override
    public void eliminarMedico(int codigoMedico) throws Exception {

        Optional<Medico> opcional = medicoRepository.findById(codigoMedico);

        if(opcional.isEmpty()){
            throw new Exception("No existe un medico con el codigo: "+ codigoMedico);
        }

        Medico buscado = opcional.get();

        buscado.setEstado(false);

        medicoRepository.save(buscado);
    }

    @Override
    public List<ItemMedicoDTO> listarMedicos() throws Exception {

        List<Medico> medicos = medicoRepository.findAll();

        if(medicos.isEmpty()){
            throw new Exception("No hay medicos registrados");
        }

        List<ItemMedicoDTO> respuesta = medicos.stream().map(m -> new ItemMedicoDTO(
                m.getId(),
                m.getCedula(),
                m.getNombreCompleto(),
                m.getEspecialidad(),m.isEstado())).toList();

        return respuesta;
    }

    @Override
    public List<ItemPqrsDTO> listarPqrs() throws Exception {

        List<Pqrs> listaPqrs = pqrsRepository.findAll();

        if(listaPqrs.isEmpty()){
            throw new Exception("No hay pqrs");
        }

        List<ItemPqrsDTO> respuesta = new ArrayList<>();

        for(Pqrs p: listaPqrs){
            respuesta.add(new ItemPqrsDTO(
                    p.getNumeroRadicado(),
                    p.getDetalle(),
                    p.getFechaCreacion(),
                    p.getEstadoPqrs()));
        }
        return respuesta;
    }

    @Override
    public void cambiarEstadoPqrs(EstadoPqrsDTO estadoPqrsDTO) throws Exception {

        Optional<Pqrs> opcionalPqrs = pqrsRepository.findById(estadoPqrsDTO.codigoPqrs());

        if(opcionalPqrs.isEmpty()){
            throw new Exception("No existe la pqrs con el codigo "+ estadoPqrsDTO.codigoPqrs());
        }

        Pqrs buscado = opcionalPqrs.get();

        buscado.setEstadoPqrs(estadoPqrsDTO.estado());

        pqrsRepository.save(buscado);

    }

    @Override
    public DetalleConsultaPqrsDTO mostrarDetalleConsultaPqrs(int codigoPqrs) throws Exception {

        Optional<Pqrs> opcional = pqrsRepository.findById(codigoPqrs);

        if(opcional.isEmpty()){
            throw new Exception("No existe esa pqrs");
        }

        Pqrs buscado = opcional.get();

        Consulta consulta = buscado.getCita().getConsulta();

        if(consulta!=null) {

            List<ItemTratamientoDTO> respuesta = clinicaServicio.verTratamiento(consulta.getId());

            return new DetalleConsultaPqrsDTO(buscado.getFechaCreacion(),
                    buscado.getCita().getConsulta().getNotasMedico(),
                    buscado.getCita().getConsulta().getDiagnostico(),
                    respuesta, buscado.getCita().getConsulta().getSintomas());
        }else{
            throw new Exception("No tiene consulta");
        }
    }

    @Override
    public int responderPqrs(RespuestaAdminPqrsDTO respuestaPqrsDTO) throws Exception {

        Optional<Pqrs> opcionalPqrs = pqrsRepository.findById(respuestaPqrsDTO.codigoPqrs());

        if(opcionalPqrs.isEmpty()){
            throw new Exception("No existe la pqrs con el código " + respuestaPqrsDTO.codigoPqrs());
        }
        Optional<Administrador> admin = adminRepository.findById(respuestaPqrsDTO.codigoAdmin());

        if(admin.isEmpty()){
            throw new Exception("No existe el admin con " + respuestaPqrsDTO.codigoAdmin());
        }

        Administrador buscado = admin.get();

        RespuestaAdmin respuestaAdminNuevo = new RespuestaAdmin();

        respuestaAdminNuevo.setAdministrador(buscado);
        respuestaAdminNuevo.setFecha(LocalDateTime.now());
        respuestaAdminNuevo.setPqrs(opcionalPqrs.get());
        respuestaAdminNuevo.setMensaje(respuestaPqrsDTO.mensaje());

        RespuestaAdmin respuestaAdminRegistrada = respuestaAdminRepository.save(respuestaAdminNuevo);
        return respuestaAdminRegistrada.getId();
    }

    @Override
    public List<ItemCitaAdminDTO> listarCitas() throws Exception {

        List<Consulta> consultas = consultaRepository.findAll();

        if(consultas.isEmpty()){
            throw new Exception("No hay consultas");
        }

        List<ItemCitaAdminDTO> respuesta = new ArrayList<>();
        for(Consulta consulta : consultas){
            respuesta.add(new ItemCitaAdminDTO(
                    consulta.getCita().getPaciente().getCedula(),
                    consulta.getCita().getPaciente().getNombreCompleto(),
                    consulta.getFecha(),
                    consulta.getCita().getMedico().getNombreCompleto()));
        }
        return respuesta;
    }
}
