package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.modelo.entidades.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Integer> {

    @Query("select m from Medico m where m.email = :email")
    Medico buscarPorCorreo(String email);

    @Query("select m from Medico m where m.cedula = :cedula")
    Medico buscarPorCedula(String cedula);


}
