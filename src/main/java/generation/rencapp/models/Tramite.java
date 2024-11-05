package generation.rencapp.models;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tramites")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Tramite {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("_id")
    private long id;

    @Column(nullable = false)
    private String nombre;

    private String descripcion;

    private Boolean agendamiento;

    private Boolean pagoAsociado;

    private Boolean terminosYCondiciones;

    private Boolean cargaDeArchivo;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;


    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "servicio_id")
    private Servicio servicio;

    @JsonIgnore
    @OneToMany(mappedBy = "tramite", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Solicitud> solicitudes;



}
