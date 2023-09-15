package co.edu.uniquindio.proyecto.modelo.entidades;

import co.edu.uniquindio.proyecto.modelo.enums.EstadoCita;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cita implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;

    @Lob
    @Column(nullable = false)
    private String motivo;

    @Column(nullable = false)
    private LocalDate fechaCreacion;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(nullable = false)
    private LocalTime hora;

    @Column(nullable = false)
    private EstadoCita estadoCita;

    @Column(nullable = false)
    @ManyToOne
    private Medico medico;

    @Column(nullable = false)
    @ManyToOne
    private Paciente paciente;

    @OneToMany(mappedBy = "cita")
    private List<Pqrs> pqrsList;

    @Column(nullable = false)
    @OneToOne(mappedBy = "cita")
    private Consulta consulta;

}
