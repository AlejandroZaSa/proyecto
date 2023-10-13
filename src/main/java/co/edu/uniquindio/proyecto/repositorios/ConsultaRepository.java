package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.modelo.entidades.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Integer> {

    @Query("select c from Consulta c where c.cita.medico.nombreCompleto = :nombreMedico or c.fecha = :fecha")
    List<Consulta> buscarConsulta(String nombreMedico, LocalDate fecha);

    @Query("select c from Consulta c where c.cita.medico.nombreCompleto = :nombreMedico and c.fecha = :fecha")
    List<Consulta> buscarConsulta2(String nombreMedico, LocalDate fecha);
}
