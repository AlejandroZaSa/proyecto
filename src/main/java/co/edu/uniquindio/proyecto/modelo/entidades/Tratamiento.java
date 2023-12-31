package co.edu.uniquindio.proyecto.modelo.entidades;

import co.edu.uniquindio.proyecto.modelo.enums.Medicamento;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Tratamiento implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;

    private int dosis;

    @Lob
    @Column(nullable = false)
    private String observaciones;

    @JoinColumn(nullable = false)
    @ManyToOne
    private Consulta consulta;

    private Medicamento medicamento;


}
