package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.modelo.entidades.Pqrs;
import co.edu.uniquindio.proyecto.modelo.enums.EstadoPqrs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PqrsRepository extends JpaRepository<Pqrs, Integer> {

    List<Pqrs> findAllByCita_Paciente_Id(int idPaciente);


    @Query("select p from Pqrs p where p.cita.paciente.id = :idPaciente and (p.estadoPqrs =:estadoPqrs1 or p.estadoPqrs =:estadoPqrs2)")
    List<Pqrs> findAllByCita_Paciente_IdAndEstadoPqrsOrEstadoPqrs(int idPaciente, EstadoPqrs estadoPqrs1, EstadoPqrs estadoPqrs2);
}
