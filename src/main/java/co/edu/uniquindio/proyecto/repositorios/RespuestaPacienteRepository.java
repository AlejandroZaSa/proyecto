package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.modelo.entidades.RespuestaPaciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RespuestaPacienteRepository extends JpaRepository<RespuestaPaciente, Integer> {
    List<RespuestaPaciente> findAllByPqrs_NumeroRadicado(int codigoPqrs);
}
