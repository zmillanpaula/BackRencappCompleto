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

    private int numeroTelefono;

    @Column()
    private String numeroDeDocumento;

    @Column ()//nullable=false
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fechaNacimiento;


}
