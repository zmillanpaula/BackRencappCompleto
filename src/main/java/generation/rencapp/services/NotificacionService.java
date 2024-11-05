package generation.rencapp.services;

import generation.rencapp.models.*;

import java.util.List;
import java.util.Map;

public interface NotificacionService {

    Notificacion findById(Long id);
    List<Notificacion> findAll();
   List<Notificacion> findAllByUsuarioId(Long idUsuario);
    Notificacion crearNotificacion(Vecino vecino, Tramite tramite, Agendamiento agendamiento);


}
