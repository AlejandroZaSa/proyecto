package co.edu.uniquindio.proyecto.modelo.entidades;

import co.edu.uniquindio.proyecto.modelo.enums.EstadoCita;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
    private LocalDateTime fechaCreacion;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(nullable = false)
    private LocalTime hora;

    @Column(nullable = false)
    private EstadoCita estadoCita;

    @JoinColumn(nullable = false)
    @ManyToOne
    private Medico medico;

    @JoinColumn(nullable = false)
    @ManyToOne
    private Paciente paciente;

    @OneToMany(mappedBy = "cita")
    private List<Pqrs> pqrsList;

    @OneToOne(mappedBy = "cita")
    private Consulta consulta;

}
