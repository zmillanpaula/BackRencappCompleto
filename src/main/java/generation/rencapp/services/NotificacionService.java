package generation.rencapp.services;

import generation.rencapp.models.Agendamiento;
import generation.rencapp.models.Notificacion;
import generation.rencapp.models.Tramite;
import generation.rencapp.models.Usuario;

import java.util.List;
import java.util.Map;

public interface NotificacionService {

    Notificacion findById(Long id);
    List<Notificacion> findAll();
   List<Notificacion> findAllByUsuarioId(Long idUsuario);
    Notificacion crearNotificacion(Usuario usuario, Tramite tramite, Agendamiento agendamiento);


}
