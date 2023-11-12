package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.modelo.entidades.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Integer> {

    @Query("select c from Consulta c where (c.cita.medico.nombreCompleto = :nombreMedico or c.fecha = :fecha) and c.cita.paciente.id=:idPaciente")
    List<Consulta> buscarConsulta(String nombreMedico, LocalDate fecha, int idPaciente);

    @Query("select c from Consulta c where c.cita.medico.nombreCompleto = :nombreMedico and c.fecha = :fecha and c.cita.paciente.id=:idPaciente")
    List<Consulta> buscarConsulta2(String nombreMedico, LocalDate fecha, int idPaciente);

    @Query("select c from Consulta c where c.cita.paciente.id =:codigoPaciente")
    List<Consulta> buscarConsultasPaciente(int codigoPaciente);

    @Query("select c from Consulta c where c.cita.medico.id = :codigoMedico")
    List<Consulta> buscarConsultasMedico(int codigoMedico);

    @Query("select c from Consulta c where c.cita.paciente.id =:codigoPaciente")
    List<Consulta> findAllByPaciente_Id(int codigoPaciente);
}
