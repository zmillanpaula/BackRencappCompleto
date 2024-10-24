package generation.rencapp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_usuario", discriminatorType = DiscriminatorType.STRING)
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("_id")
    private Long id;

    private String nombre;

    private String apellido;


    @Column(nullable = false, unique = true)//Restricciones para la columna, no permitir null y sólo registros únicos
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)//Anotación para indicar que el valor del atributo va a tomarse de una enumeración
    @Column(nullable = false)
    private TipoUsuario tipo;

    //Atributos de auditoría, me permiten conocer la fecha de creación y de la última edición
    @CreationTimestamp//Anotación para generar el almacenamiento de la fecha de creación
    private LocalDateTime createdAt;

    @UpdateTimestamp//Anotación para generar el almacenamiento de la fecha de actualización
    private LocalDateTime updatedAt;
    


    /***********Relaciones************/

    /*@JsonIgnore
    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)//Relación de 1 a 1
    private Funcionario funcionario;


    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private Vecino vecino;*/

}
