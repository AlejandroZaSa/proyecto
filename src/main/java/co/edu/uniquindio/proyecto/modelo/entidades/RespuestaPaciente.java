package co.edu.uniquindio.proyecto.modelo.entidades;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class RespuestaPaciente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;

    @Column(nullable = false)
    @Lob
    private String mensaje;

    @Column(nullable = false)
    private LocalDateTime fecha;

    @ManyToOne
    @Column(nullable = false)
    private Paciente paciente;

    @ManyToOne
    @Column(nullable = false)
    private Pqrs pqrs;

    @OneToOne
    @Column(nullable = false)
    private RespuestaAdmin respuestaAdmin;


}
