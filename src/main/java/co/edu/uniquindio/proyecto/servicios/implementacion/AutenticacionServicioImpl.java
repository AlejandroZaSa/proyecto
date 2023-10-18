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
import co.edu.uniquindio.proyecto.utils.JWTUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.*;

@Service
@RequiredArgsConstructor
public class AutenticacionServicioImpl implements AutenticacionServicio {

    private final MedicoRepository medicoRepository;
    private final PacienteRepository pacienteRepository;
    private final AdminRepository adminRepository;

    private final JWTUtils jwtUtils;

    @Override
    public TokenDTO login(LoginDTO loginDTO) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        Object[] datos = buscarCorreo(loginDTO);

        if (!passwordEncoder.matches(loginDTO.password(), datos[3].toString())) {
            throw new Exception("La contraseña ingresada es incorrecta");
        }

        return new TokenDTO(crearToken(datos));
    }

    private String crearToken(Object[] datos) {

        Map<String, Object> map = new HashMap<>();
        map.put("rol", datos[4]);
        map.put("nombre", datos[1]);
        map.put("id", datos[2]);

        return jwtUtils.generarToken(datos[0].toString(), map);
    }

    public Object[] buscarCorreo(LoginDTO loginDTO) throws Exception {

        String correo = "";
        int codigo = 0;
        String nombre = "";
        String password = "";
        String rol = "";

        Medico medico = medicoRepository.findByEmail(loginDTO.email());

        if (medico == null) {

            Paciente paciente = pacienteRepository.findByEmail(loginDTO.email());

            if (paciente == null) {

                Administrador admin = adminRepository.findByEmail(loginDTO.email());

                if (admin == null) {
                    throw new Exception("El usuario no existe");
                }else{
                    correo = admin.getEmail();
                    nombre = "Administrador";
                    codigo = admin.getId();
                    password = admin.getContrasenia();
                    rol = "admin";
                }

            }else{

                correo = paciente.getEmail();
                nombre = paciente.getNombreCompleto();
                codigo = paciente.getId();
                password = paciente.getContrasenia();
                rol = "pacienta";
            }

        }else{

            correo = medico.getEmail();
            nombre = medico.getNombreCompleto();
            codigo = medico.getId();
            password = medico.getContrasenia();
            rol = "medico";
        }

        return new Object[]{correo, nombre, codigo, password, rol };
    }
}
