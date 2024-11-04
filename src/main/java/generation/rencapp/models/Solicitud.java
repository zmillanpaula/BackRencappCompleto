package generation.rencapp.models;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "solicitudes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Solicitud {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("_id")
    private long id;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoSolicitud estado;

    public enum EstadoSolicitud {
        PENDIENTE,
        EVALUADO,
        PAGADO,
        FINALIZADO
    }


    @ManyToOne
    @JoinColumn(name = "vecino_id")
    private Vecino vecino;


    @ManyToOne
    @JoinColumn(name = "tramite_id")
    private Tramite tramite;



}
