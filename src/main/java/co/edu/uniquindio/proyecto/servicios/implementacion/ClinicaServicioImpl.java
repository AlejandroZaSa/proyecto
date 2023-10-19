package co.edu.uniquindio.proyecto.servicios.implementacion;

import co.edu.uniquindio.proyecto.dto.clinica.EmailDTO;
import co.edu.uniquindio.proyecto.dto.clinica.EpsDTO;
import co.edu.uniquindio.proyecto.dto.clinica.ItemTratamientoDTO;
import co.edu.uniquindio.proyecto.dto.clinica.MensajeDTO;
import co.edu.uniquindio.proyecto.modelo.entidades.*;
import co.edu.uniquindio.proyecto.modelo.enums.Ciudad;
import co.edu.uniquindio.proyecto.modelo.enums.EstadoPqrs;
import co.edu.uniquindio.proyecto.modelo.enums.Medicamento;
import co.edu.uniquindio.proyecto.modelo.enums.TipoSangre;
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

        emailServicio.enviarEmail(new EmailDTO("Recupera tu cuenta", email, "link"));


    }

    @Override
    public void cambiarPassword(int codigoUsuario, String nuevaPassword) throws Exception {

        Optional<Medico> medico = medicoRepository.findById(codigoUsuario);

        if(medico.isEmpty()){
            Optional<Paciente> paciente = pacienteRepository.findById(codigoUsuario);

            if(paciente.isEmpty()){
                Optional<Administrador> admin = adminRepository.findById(codigoUsuario);

                if(admin.isEmpty()){
                    throw new Exception("El usuario con el código "+codigoUsuario+" no existe");
                }else{
                    Administrador buscado = admin.get();

                    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                    String passwordEncriptada = passwordEncoder.encode( nuevaPassword );

                    buscado.setContrasenia(passwordEncriptada);
                    adminRepository.save(buscado);
                }
            }else{
                Paciente buscado = paciente.get();

                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                String passwordEncriptada = passwordEncoder.encode( nuevaPassword );

                buscado.setContrasenia(passwordEncriptada);
                pacienteRepository.save(buscado);
            }
        }else{
            Medico buscado = medico.get();

            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String passwordEncriptada = passwordEncoder.encode( nuevaPassword );

            buscado.setContrasenia(passwordEncriptada);
            medicoRepository.save(buscado);
        }

        /**Optional<Paciente> paciente = pacienteRepository.findById(codigoUsuario);
        Optional<Administrador> admin = adminRepository.findById(codigoUsuario);

        if(medico.isEmpty() && paciente.isEmpty() && admin.isEmpty()){
            throw new Exception("El usuario con el código "+codigoUsuario+" no existe");
        }
        if(medico.isPresent()){
            Medico buscado = medico.get();

            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String passwordEncriptada = passwordEncoder.encode( nuevaPassword );

            buscado.setContrasenia(passwordEncriptada);
            medicoRepository.save(buscado);
        }else if(paciente.isPresent()){
            Paciente buscado = paciente.get();

            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String passwordEncriptada = passwordEncoder.encode( nuevaPassword );

            buscado.setContrasenia(passwordEncriptada);
            pacienteRepository.save(buscado);
        }else {
            Administrador buscado = admin.get();

            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String passwordEncriptada = passwordEncoder.encode( nuevaPassword );

            buscado.setContrasenia(passwordEncriptada);
            adminRepository.save(buscado);
        }**/

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
