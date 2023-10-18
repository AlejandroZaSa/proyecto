package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.modelo.entidades.Consulta;
import co.edu.uniquindio.proyecto.modelo.entidades.Medico;
import co.edu.uniquindio.proyecto.modelo.entidades.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Integer> {

    @Query("select p from Paciente p where p.email = :email")
    Paciente buscarPorCorreo(String email);

    @Query("select p from Paciente p where p.cedula = :cedula")
    Paciente buscarPorCedula(String cedula);

    @Query("select c from Consulta c where c.cita.paciente.id =:codigoPaciente")
    List<Consulta> buscarConsultasPaciente(int codigoPaciente);

    Paciente findByEmailAndContrasenia(String email, String password);

    @Query("select p from Paciente p where p.email = :email")
    Paciente findByEmail(String email);
}
