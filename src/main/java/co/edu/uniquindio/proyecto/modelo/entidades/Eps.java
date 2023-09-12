package co.edu.uniquindio.proyecto.modelo.entidades;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Eps implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;

    private String nombre;
    private float porcentajeConsulta;

    @OneToMany(mappedBy = "eps")
    private List<Paciente> pacientes;

    public Eps(String nombre, float porcentajeConsulta) {
        this.nombre = nombre;
        this.porcentajeConsulta = porcentajeConsulta;
    }
}
