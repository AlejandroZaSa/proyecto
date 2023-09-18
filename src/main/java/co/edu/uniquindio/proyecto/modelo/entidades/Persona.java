package co.edu.uniquindio.proyecto.modelo.entidades;

import co.edu.uniquindio.proyecto.modelo.enums.Ciudad;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@MappedSuperclass
public class Persona extends Cuenta implements Serializable{

    @Column(nullable = false, length = 10, unique = true)
    private String cedula;

    @Column(nullable = false, length = 30)
    private String nombreCompleto;

    @Column(nullable = false, length = 10)
    private String telefono;

    @Column(nullable = false)
    private String foto;

    @Column(nullable = false)
    private Ciudad ciudad;

    //estado de la persona podría ser enum activo o inactivo
    @Column(nullable = false)
    private boolean estado;

}
