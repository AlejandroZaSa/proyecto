package co.edu.uniquindio.proyecto.servicios.implementacion;

import co.edu.uniquindio.proyecto.dto.clinica.LoginDTO;
import co.edu.uniquindio.proyecto.modelo.entidades.Administrador;
import co.edu.uniquindio.proyecto.modelo.entidades.Medico;
import co.edu.uniquindio.proyecto.modelo.entidades.Paciente;
import co.edu.uniquindio.proyecto.repositorios.AdminRepository;
import co.edu.uniquindio.proyecto.repositorios.MedicoRepository;
import co.edu.uniquindio.proyecto.repositorios.PacienteRepository;
import co.edu.uniquindio.proyecto.servicios.interfaces.AutenticacionServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AutenticacionServicioImpl implements AutenticacionServicio {

    private final MedicoRepository medicoRepository;
    private final PacienteRepository pacienteRepository;
    private final AdminRepository adminRepository;

    @Override
    public void login(LoginDTO loginDTO) throws Exception{

        Medico medico = medicoRepository.findByEmailAndContrasenia(loginDTO.email(),loginDTO.password());
        Paciente paciente = pacienteRepository.findByEmailAndContrasenia(loginDTO.email(),loginDTO.password());
        Administrador admin = adminRepository.findByEmailAndContrasenia(loginDTO.email(),loginDTO.password());

        if(medico==null&&paciente==null&&admin==null){
            throw new Exception("El usuario no existe");
        }

        if(medico!=null){
            //cargar pantalla de medico
        }else if(paciente!=null){
            //cargar pantalla paciente
        }else if(admin!=null) {
            //cargar pantalla admin
        }
    }
}
