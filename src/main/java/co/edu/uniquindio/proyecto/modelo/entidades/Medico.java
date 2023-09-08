package co.edu.uniquindio.proyecto.modelo.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Medico extends Persona implements Serializable{

    private float costoConsulta;
    private Especialidad especialidad;

    @OneToMany(mappedBy = "medico")
    private List<DiaLibre> diaibres;

    @OneToMany(mappedBy = "medico")
    private List<Horario> horarios;

    @OneToMany(mappedBy = "medico")
    private List<Cita> citas;
}
