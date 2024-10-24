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



}
