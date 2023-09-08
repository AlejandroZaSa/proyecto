package co.edu.uniquindio.proyecto.modelo.entidades;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class RespuestaAdmin implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;

    private String mensaje;

    @ManyToOne
    private Administrador administrador;

    @ManyToOne
    private Pqrs pqrs;

    @OneToOne(mappedBy = "respuestaAdmin")
    private RespuestaPaciente respuestaPaciente;
}
