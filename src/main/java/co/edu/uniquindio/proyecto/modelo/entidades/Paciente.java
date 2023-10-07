package co.edu.uniquindio.proyecto.modelo.entidades;

import co.edu.uniquindio.proyecto.modelo.enums.TipoSangre;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Paciente extends Persona implements Serializable{

    @Column(nullable = false)
    private LocalDate fechaNacimiento;

    @Lob
    @Column(nullable = false)
    private String alergias;

    @Column(nullable = false)
    private TipoSangre tipoSangre;

    @JoinColumn(nullable = false)
    @ManyToOne
    private Eps eps;

    @OneToMany(mappedBy = "paciente")
    private List<RespuestaPaciente> respuestaPacienteList;

    @OneToMany(mappedBy = "paciente")
    private List<Cita> citas;
}
