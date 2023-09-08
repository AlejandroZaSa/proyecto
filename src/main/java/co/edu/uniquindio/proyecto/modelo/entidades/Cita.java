package co.edu.uniquindio.proyecto.modelo.entidades;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

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

    private String motivo;
    private Date fechaCreacion;
    private Date fecha;
    private String hora;
    private EstadoCita estadoCita;

    @ManyToOne
    private Medico medico;

    @ManyToOne
    private Paciente paciente;

    @OneToOne(mappedBy = "cita")
    private Consulta consulta;

}
