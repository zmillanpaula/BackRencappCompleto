package generation.rencapp.services;

import generation.rencapp.models.Agendamiento;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface AgendamientoService {

    Agendamiento agendar(Long usuarioId, LocalDateTime fechaHora, Long tramiteId);

    Agendamiento buscarPorId(Long agendamientoId);

    Agendamiento suspenderAgendamiento(Long agendamientoId);

    List<Agendamiento> agendamientosbyVecinoId(Long usuarioId, String tipo);

    List<Agendamiento> agendamientosbyTramiteId(Long tramiteId);

    List<Agendamiento> agendamientosbyFechaAndTramiteId(LocalDate fecha, Long tramiteId);
}
