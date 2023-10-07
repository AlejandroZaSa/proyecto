package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.modelo.entidades.DiaLibre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface DiaLibreRepository extends JpaRepository<DiaLibre, Integer> {

    @Query("select d from DiaLibre d where d.medico.id = :codigoMedico and d.fecha = :fecha")
    DiaLibre obtenerDiaLibreFecha(int codigoMedico, LocalDate fecha);

    @Query("SELECT d FROM DiaLibre d WHERE d.medico.id = :codigoMedico AND d.fecha >= :fecha")
    Optional<DiaLibre> findByMedico_IdAndFechaAfterOrEqual(int codigoMedico, LocalDate fecha);
}
