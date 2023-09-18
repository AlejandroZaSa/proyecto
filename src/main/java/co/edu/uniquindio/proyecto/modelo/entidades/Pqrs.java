package co.edu.uniquindio.proyecto.modelo.entidades;

import co.edu.uniquindio.proyecto.modelo.enums.EstadoPqrs;
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

    @Column(nullable = false)
    private LocalDate fechaCreacion;

    @Lob
    @Column(nullable = false)
    private String detalle;

    @JoinColumn(nullable = false)
    @ManyToOne
    private Cita cita;

    @Column(nullable = false)
    private EstadoPqrs estadoPqrs;

    @OneToMany(mappedBy = "pqrs")
    private List<RespuestaAdmin> respuestasAdmin;

    @OneToMany(mappedBy = "pqrs")
    private List<RespuestaPaciente> respuestaPacientes;

}
