package co.edu.uniquindio.proyecto.modelo.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Paciente extends Persona implements Serializable{

    private LocalDate fechaNacimiento;

    @Lob
    private String alergias;

    private TipoSangre tipoSangre;

    @ManyToOne
    private Eps eps;

    @OneToMany(mappedBy = "paciente")
    private List<RespuestaPaciente> respuestaPacienteList;

    @OneToMany(mappedBy = "paciente")
    private List<Cita> citas;
}
