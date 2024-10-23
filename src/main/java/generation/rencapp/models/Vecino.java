package generation.rencapp.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@DiscriminatorValue("VECINO")
public class Vecino extends Usuario {

    @Column (name = "direccion")
    private String direccion;

    private String numeroTelefono;

    @Enumerated(EnumType.STRING)//Anotación para indicar que el valor del atributo va a tomarse de una enumeración
    @Column(nullable = false)
    private TipoDocumento tipoDocumento;

    @Column(nullable = false)
    private String numeroDeDocumento;

    @Column (nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fechaNacimiento;

    /***********************/
    /*@OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;*/

}
