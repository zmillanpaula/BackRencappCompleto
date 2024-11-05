package generation.rencapp.models;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalTime;

@Data
@Entity
@Table(name="horarios_intervalos")
public class Intervalos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalTime hora;

    @Enumerated(EnumType.STRING)
    private Estadointervalo estado;

    public enum Estadointervalo {
        DISPONIBLE,
        AGENDADA
    }

    @ManyToOne
    @JoinColumn(name = "horario_id", nullable = false)
    private Horario horario;
}
