package co.edu.uniquindio.proyecto.modelo.entidades;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@MappedSuperclass
public class Persona extends Cuenta implements Serializable{

    private String nombreCompleto;
    private String telefono;
    private String foto;
    private Ciudad ciudad;
    private boolean estado;

}
