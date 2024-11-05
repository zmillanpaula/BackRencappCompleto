package generation.rencapp.services;

import generation.rencapp.models.Horario;
import generation.rencapp.models.Intervalos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface HorarioService {

    Horario findById(Long id);

    List<Horario> findAll();

    Horario saveHorario(Horario horarioNuevo);

    //los metodos void o boolean se dejan en una interfaz?

    //List<Horario> findHorarioByEstado(String estado);

    List<Intervalos> obtenerIntervalosDeUnaHora(Long tramiteId, Long horarioId, LocalDate fecha);

    List<Intervalos> generarIntervalos(Long horarioId, LocalTime horaInicio, LocalTime horaFin);



}
