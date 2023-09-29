package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.modelo.entidades.Horario;
import co.edu.uniquindio.proyecto.modelo.enums.Dia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HorarioRepository extends JpaRepository<Horario, Integer> {

    List<Horario> findAllByMedico_Id(int id);

    @Query("select h from Horario h where h.medico.id = :codMedico and h.dia = :dia")
    Horario obtenerHorarioFecha(int codMedico, Dia dia);
}
