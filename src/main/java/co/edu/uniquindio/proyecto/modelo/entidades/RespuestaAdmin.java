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
public class RespuestaAdmin implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;

    @Lob
    @Column(nullable = false)
    private String mensaje;

    @Column(nullable = false)
    private LocalDateTime fecha;

    @JoinColumn(nullable = false)
    @ManyToOne
    private Administrador administrador;

    @JoinColumn(nullable = false)
    @ManyToOne
    private Pqrs pqrs;

    @OneToOne(mappedBy = "respuestaAdmin")
    private RespuestaPaciente respuestaPaciente;
}
