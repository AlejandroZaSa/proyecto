package co.edu.uniquindio.proyecto.modelo.entidades;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

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
    private String motivo;

    private LocalDate fechaCreacion;
    private LocalDate fecha;
    private LocalTime hora;
    private EstadoCita estadoCita;

    @ManyToOne
    private Medico medico;

    @ManyToOne
    private Paciente paciente;

    @OneToOne(mappedBy = "cita")
    private Consulta consulta;

}
