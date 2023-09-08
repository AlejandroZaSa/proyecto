package co.edu.uniquindio.proyecto.modelo.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Persona extends Cuenta implements Serializable{

    @Id
    @EqualsAndHashCode.Include
    private int cedula;

    private String nombreCompleto;
    private String telefono;
    private String foto;
    private Ciudad ciudad;
    private boolean estado;

}
