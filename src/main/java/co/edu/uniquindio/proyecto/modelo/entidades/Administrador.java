package co.edu.uniquindio.proyecto.modelo.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Administrador extends Cuenta implements Serializable{

    @OneToMany(mappedBy = "administrador")
    private List<RespuestaAdmin> respuestasAdmin;

}
