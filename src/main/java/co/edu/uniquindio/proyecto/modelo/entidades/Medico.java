package co.edu.uniquindio.proyecto.modelo.entidades;

import co.edu.uniquindio.proyecto.modelo.enums.Especialidad;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Medico extends Persona implements Serializable{

    @Column(nullable = false)
    private float costoConsulta;

    @Column(nullable = false)
    private Especialidad especialidad;

    @OneToMany(mappedBy = "medico")
    private List<DiaLibre> diaibres;

    @OneToMany(mappedBy = "medico")
    private List<Horario> horarios;

    @OneToMany(mappedBy = "medico")
    private List<Cita> citas;

}
