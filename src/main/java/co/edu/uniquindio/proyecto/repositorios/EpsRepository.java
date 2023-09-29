package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.modelo.entidades.Eps;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface EpsRepository extends JpaRepository<Eps, Integer> {

    @Query("select e from Eps e where e.id = :id")
    Eps buscarEps(int id);

}
