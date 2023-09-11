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

    private LocalDate fecha;
    private String notasMedico;
    private String diagnostico;
    private String sintomas;

    @OneToOne
    private Cita cita;

    @OneToMany(mappedBy = "consulta")
    private List<Pqrs> pqrsList;

    @OneToOne(mappedBy = "consulta")
    private Factura factura;

    @OneToMany(mappedBy = "consulta")
    private List<Tratamiento> tratamientos;
}
