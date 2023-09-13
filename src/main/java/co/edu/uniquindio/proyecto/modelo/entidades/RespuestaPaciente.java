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

    private String mensaje;

    private LocalDateTime fecha;

    @ManyToOne
    private Paciente paciente;

    @ManyToOne
    private Pqrs pqrs;
    @OneToOne
    private RespuestaAdmin respuestaAdmin;


}
