package generation.rencapp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    // Campos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // id
    @Column(nullable = false, unique = true)
    private String nombre; // nombre del servicio
    @Column(nullable = false, unique = true)
    private String descripcion; // descripcion
    /** Checkboxs **/
    @Column(name = "agregar_imagen")
    private Boolean agregarImagen; // imagen
    @Column(name = "agregar_pdf")
    private Boolean agregarPdf; // PDF
    /*@Column(name = "agregar_comentario")
    private Boolean agregarComentario;*/
    @Column(name = "terminos_condiciones")
    private Boolean terminosCondiciones; // Terminos & Condiciones
    @Column
    private String  textoTerminosCondiciones;
    @CreationTimestamp
    private LocalDateTime createdAt; // Creacion
    @UpdateTimestamp
    private LocalDateTime updatedAt; // Actualizacion

    // Relaciones
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "departamento_id")
    private Departamento departamento;

}
