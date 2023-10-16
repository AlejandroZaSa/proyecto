package co.edu.uniquindio.proyecto.servicios.implementacion;

import co.edu.uniquindio.proyecto.dto.autenticacionJwt.TokenDTO;
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
import co.edu.uniquindio.proyecto.modelo.entidades.Cuenta;
import co.edu.uniquindio.proyecto.utils.JWTUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.*;

@Service
@RequiredArgsConstructor
public class AutenticacionServicioImpl implements AutenticacionServicio {

    private final MedicoRepository medicoRepository;
    private final PacienteRepository pacienteRepository;
    private final AdminRepository adminRepository;

    private final CuentaRepo cuentaRepo;
    private final JWTUtils jwtUtils;

    @Override
    public TokenDTO login(LoginDTO loginDTO) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Optional<Cuenta> cuentaOptional = cuentaRepo.findByCorreo(loginDTO.email());
        if (cuentaOptional.isEmpty()) {
            throw new Exception("No existe el correo ingresado");
        }
        Cuenta cuenta = cuentaOptional.get();
        if (!passwordEncoder.matches(loginDTO.password(), cuenta.getPassword())) {
            throw new Exception("La contraseña ingresada es incorrecta");
        }
        return new TokenDTO(crearToken(cuenta));
    }

    private String crearToken(Cuenta cuenta) {
        String rol;
        String nombre;
        if (cuenta instanceof Paciente) {
            rol = "paciente";
            nombre = ((Paciente) cuenta).getNombre();
        } else if (cuenta instanceof Medico) {
            rol = "medico";
            nombre = ((Medico) cuenta).getNombre();
        } else {
            rol = "admin";
            nombre = "Administrador";
        }
        Map<String, Object> map = new HashMap<>();
        map.put("rol", rol);
        map.put("nombre", nombre);
        map.put("id", cuenta.getCodigo());

        return jwtUtils.generarToken(cuenta.getCorreo(), map);
    }

    public String buscarCorreo(LoginDTO loginDTO) throws Exception {

        Medico medico = medicoRepository.findByEmail(loginDTO.email());

        if (medico == null) {

            Paciente paciente = pacienteRepository.findByEmailAndContrasenia(loginDTO.email(), loginDTO.password());

            if (paciente == null) {

                Administrador admin = adminRepository.findByEmailAndContrasenia(loginDTO.email(), loginDTO.password());

                if (admin == null) {
                    throw new Exception("El usuario no existe");
                }

            }

        }

        return "";
    }
}
