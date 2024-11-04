package generation.rencapp.models;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "servicios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Servicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String nombre;
    @Column(nullable = false, unique = true)
    private String descripcion;
    @Column(name = "agregar_imagen")
    private Boolean agregarImagen;
    @Column(name = "agregar_pdf")
    private Boolean agregarPdf;
    @Column(name = "terminos_condiciones")
    private Boolean terminosCondiciones;
    @Column
    private String  textoTerminosCondiciones;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;


    @ManyToOne
    @JoinColumn(name = "departamento_id")
    private Departamento departamento;

}
