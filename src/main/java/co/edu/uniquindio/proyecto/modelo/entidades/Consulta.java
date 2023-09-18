package co.edu.uniquindio.proyecto.modelo.entidades;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Consulta implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(nullable = false)
    @Lob
    private String notasMedico;

    @Column(nullable = false)
    @Lob
    private String diagnostico;

    @Column(nullable = false)
    @Lob
    private String sintomas;

    @JoinColumn(nullable = false)
    @OneToOne
    private Cita cita;

    @OneToOne(mappedBy = "consulta")
    private Factura factura;

    @OneToMany(mappedBy = "consulta")
    private List<Tratamiento> tratamientos;
}
