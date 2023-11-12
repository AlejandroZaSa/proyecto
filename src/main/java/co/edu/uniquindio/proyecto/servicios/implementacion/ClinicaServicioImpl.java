package co.edu.uniquindio.proyecto.servicios.implementacion;

import co.edu.uniquindio.proyecto.dto.clinica.*;
import co.edu.uniquindio.proyecto.dto.medico.DiaLibreDTO;
import co.edu.uniquindio.proyecto.modelo.entidades.*;
import co.edu.uniquindio.proyecto.modelo.enums.*;
import co.edu.uniquindio.proyecto.repositorios.*;
import co.edu.uniquindio.proyecto.servicios.interfaces.ClinicaServicio;
import co.edu.uniquindio.proyecto.servicios.interfaces.EmailServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ClinicaServicioImpl implements ClinicaServicio {

    private final RespuestaAdminRepository respuestaAdminRepository;
    private final RespuestaPacienteRepository respuestaPacienteRepository;
    private final TratamientoRepository tratamientoRepository;
    private final MedicoRepository medicoRepository;
    private final PacienteRepository pacienteRepository;
    private final AdminRepository adminRepository;
    private final EpsRepository epsRepository;
    private final EmailServicio emailServicio;

    @Override
    public List<EstadoPqrs> cargarListaEstadosPqrs() {
        return List.of( EstadoPqrs.values() );
    }

    @Override
    public List<TipoSangre> cargarTiposSangre() {

        return List.of(TipoSangre.values());
    }

    @Override
    public List<Especialidad> cargarEspecialidades() {

        return List.of(Especialidad.values());
    }

    @Override
    public List<EpsDTO> cargarEps() {

        List<Eps> epsList = epsRepository.findAll();

        List<EpsDTO> epsDTOList = new ArrayList<>();

        for(Eps eps : epsList){
            epsDTOList.add(new EpsDTO(eps.getId(), eps.getNombre()));
        }

        return epsDTOList;
    }

    @Override
    public List<Medicamento> cargarMedicamentos() {

        return List.of(Medicamento.values());
    }

    @Override
    public List<Ciudad> cargarCiudades() {

        return List.of(Ciudad.values());
    }

    @Override
    public List<MensajeDTO> mostrarHistorialMensajesPqrs(int codigoPqrs) throws Exception {

        List<RespuestaAdmin> respuestaAdmin = respuestaAdminRepository.findAllByPqrs_NumeroRadicado(codigoPqrs);

        if(respuestaAdmin.isEmpty()){
            throw new Exception("No hay respuestas del administrador");
        }

        List<RespuestaPaciente> respuestaPaciente = respuestaPacienteRepository.findAllByPqrs_NumeroRadicado(codigoPqrs);

        if(respuestaAdmin.isEmpty()){
            throw new Exception("No hay respuestas del paciente");
        }

        List<MensajeDTO> mensajes = new ArrayList<>();

        for(RespuestaAdmin respuestaA : respuestaAdmin){
            mensajes.add(new MensajeDTO(
                    respuestaA.getId(),
                    respuestaA.getAdministrador().getId(),
                    respuestaA.getAdministrador().getEmail(),
                    respuestaA.getMensaje(),
                    respuestaA.getFecha(),
                    respuestaA.getPqrs().getNumeroRadicado()));
        }

        for(RespuestaPaciente respuestaP : respuestaPaciente){
            mensajes.add(new MensajeDTO(
                    respuestaP.getId(),
                    respuestaP.getPaciente().getId(),
                    respuestaP.getPaciente().getNombreCompleto(),
                    respuestaP.getMensaje(),
                    respuestaP.getFecha(),
                    respuestaP.getPqrs().getNumeroRadicado()));
        }

        mensajes.sort(Comparator.comparing(MensajeDTO::fecha));

        return mensajes;
    }

    @Override
    public void enviarLinkRecuperacion(String email) throws Exception {

        String parametroEmail = Base64.getEncoder().encodeToString(email.getBytes());

        emailServicio.enviarEmail(new EmailDTO("Recupera tu cuenta", email, "http:/localhost:4200/recuperar-password/"+parametroEmail));


    }

    @Override
    public void cambiarPassword(CambioPasswordDTO cambioPasswordDTO) throws Exception {

        Medico medico = medicoRepository.findByEmail(cambioPasswordDTO.email());

        if(medico==null){
            Paciente paciente = pacienteRepository.findByEmail(cambioPasswordDTO.email());

            if(paciente==null){
                Administrador admin = adminRepository.findByEmail(cambioPasswordDTO.email());

                if(admin==null){
                    throw new Exception("El usuario con el email "+cambioPasswordDTO.email()+" no existe");
                }else{
                    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                    String passwordEncriptada = passwordEncoder.encode(cambioPasswordDTO.nuevaPassword() );

                    admin.setContrasenia(passwordEncriptada);
                    adminRepository.save(admin);
                }
            }else{

                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                String passwordEncriptada = passwordEncoder.encode(cambioPasswordDTO.nuevaPassword() );

                paciente.setContrasenia(passwordEncriptada);
                pacienteRepository.save(paciente);
            }
        }else{

            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String passwordEncriptada = passwordEncoder.encode(cambioPasswordDTO.nuevaPassword() );

            medico.setContrasenia(passwordEncriptada);
            medicoRepository.save(medico);
        }
    }

    @Override
    public List<ItemTratamientoDTO> verTratamiento(int codigoConsulta) throws Exception{

        List<Tratamiento> listaTratamientos = tratamientoRepository.findAllByConsulta_id(codigoConsulta);

        if(listaTratamientos.isEmpty()){
            throw new Exception("No hay tratamientos para la consulta con código "+ codigoConsulta);
        }

        List<ItemTratamientoDTO> respuesta = new ArrayList<>();

        for(Tratamiento tratamiento : listaTratamientos){
            respuesta.add(new ItemTratamientoDTO(tratamiento.getDosis(),
                    tratamiento.getObservaciones(),
                    tratamiento.getMedicamento()));
        }

        return respuesta;
    }
}
