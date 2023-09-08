package co.edu.uniquindio.proyecto.modelo.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Paciente extends Persona implements Serializable{

    private Date fechaNacimiento;
    private String alergias;
    private TipoSangre tipoSangre;
    private Eps eps;

    @OneToMany(mappedBy = "paciente")
    private List<RespuestaPaciente> respuestaPacienteList;

    @OneToMany(mappedBy = "paciente")
    private List<Cita> citas;
}
