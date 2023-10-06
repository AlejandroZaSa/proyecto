package co.edu.uniquindio.proyecto.modelo.entidades;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Factura implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;

    @Column(nullable = false)
    private LocalDate fecha;

    @Lob
    @Column(nullable = false)
    private String concepto;

    @Column(nullable = false)
    private float valor;

    @JoinColumn(nullable = false)
    @OneToOne
    private Consulta consulta;
}
