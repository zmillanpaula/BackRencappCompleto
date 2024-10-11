package generation.rencapp.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "departamentos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Departamento {
    // Campos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // id
    @Column(nullable = false, unique = true)
    private String nombre; // nombre del departamento
    @Column(nullable = false)
    private String descripcion; // descripcion
    @CreationTimestamp
    private LocalDateTime createdAt; // Creacion
    @UpdateTimestamp
    private LocalDateTime updatedAt; // Actualizacion

    // Relaciones
    @OneToMany(mappedBy = "departamento", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Servicio> servicios;
}
