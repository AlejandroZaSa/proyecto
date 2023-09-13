package co.edu.uniquindio.proyecto.modelo.entidades;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Pqrs implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int numeroRadicado;

    private LocalDate fechaCreacion;
    private String detalle;

    @ManyToOne
    private Consulta consulta;
    private EstadoPqrs estadoPqrs;

    @OneToMany(mappedBy = "pqrs")
    private List<RespuestaAdmin> respuestasAdmin;

    @OneToMany(mappedBy = "pqrs")
    private List<RespuestaPaciente> respuestaPacientes;

}
