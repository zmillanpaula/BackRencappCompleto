package generation.rencapp.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@DiscriminatorValue("FUNCIONARIO")
public class Funcionario extends Usuario {

    private String departamento;

    //¿que más le podemos agregar?

    /****************/

    /*@OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name ="funcionario_rol",
            joinColumns = @JoinColumn(name = "funcionario_id"),
            inverseJoinColumns = @JoinColumn(name= "rol_id"))
    private Set<Rol> roles = new HashSet<>();*/

}
