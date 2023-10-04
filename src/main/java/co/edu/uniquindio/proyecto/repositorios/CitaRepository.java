package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.modelo.entidades.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Integer> {

    List<Cita> findAllByPaciente_Id(int idPaciente);

    @Query("SELECT c FROM Cita c WHERE c.medico.id = :codigoMedico AND c.fecha >= :fecha")
    List<Cita> findAllByMedico_IdAndFechaAfterOrEqual(int codigoMedico, LocalDate fecha);

    @Query("select c from Cita c where c.medico.id = :codigoMedico and c.fecha = :fechaDeseada")
    List<Cita> buscarCitaEnFecha(int codigoMedico, LocalDate fechaDeseada);

    @Query("select c from Cita c where c.medico.id = :codigoMedico and c.fecha = :fechaDeseada")
    List<Cita> obtenerCitasFecha(int codigoMedico, LocalDate fechaDeseada);
}
