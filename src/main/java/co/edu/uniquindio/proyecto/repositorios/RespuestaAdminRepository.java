package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.modelo.entidades.RespuestaAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RespuestaAdminRepository extends JpaRepository<RespuestaAdmin, Integer> {
    List<RespuestaAdmin> findAllByPqrs_NumeroRadicado(int codigoPqrs);
}
