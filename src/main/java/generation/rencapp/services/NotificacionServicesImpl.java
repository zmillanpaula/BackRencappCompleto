package generation.rencapp.services;

import generation.rencapp.email.EmailService;
import generation.rencapp.email.MensajesService;
import generation.rencapp.models.*;
import generation.rencapp.repositories.NotificacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NotificacionServicesImpl implements NotificacionService {

    @Autowired
    private NotificacionRepository notificacionRepository;
    @Autowired
    private EmailService emailService;
    @Autowired
    private MensajesService mensajesService;

    public Notificacion crearNotificacion(Vecino vecino, Tramite tramite, Agendamiento agendamiento) {

        /***NOTIFICACIÓN DENTRO DEL SISTEMA***/

        Notificacion notificacion = Notificacion.builder()
                .contenidoMensaje("Tu hora ha sido agendada para el día" + agendamiento.getFecha())
                .leida(false)
                .build();
        notificacionRepository.save(notificacion);

        Map<String, String>datos = new HashMap<>();
        datos.put("nombre", vecino.getNombre());
        datos.put("apellido" , vecino.getApellido());
        datos.put("fecha", agendamiento.getFecha().toString());// esto mismo los hacemos a los demás campos  -PRUEBA
        datos.put("horacita",agendamiento.getFechaHora().toLocalTime().toString());
        datos.put("tiposervicios",tramite.getServicio().getNombre());


        /***NOTIFICACIÓN POR MAIL***/
        if (vecino.getEmail() != null && !vecino.getEmail().isEmpty()) {
            try {
                emailService.enviarCorreo(vecino.getEmail(), "Nueva Notificación",tramite.getServicio().getNombre() ,datos);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        /***NOTIFICACIÓN POR SMS**/
            String numero= "+"+ String.valueOf(vecino.getNumeroTelefono());
        if (numero != null && !numero.isEmpty()) {
            try {
                mensajesService.enviarMensaje(numero, "Tu hora ha sido agendada para el día" + agendamiento.getFecha());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return notificacion;
    }

    @Override
    public Notificacion findById(Long id) {
        return notificacionRepository.findById(id)
                .orElse(null);
    }

    @Override
    public List<Notificacion> findAll() {
        return notificacionRepository.findAll();
    }

    @Override
    public List<Notificacion> findAllByUsuarioId(Long idUsuario) {
        return notificacionRepository.findNotificacionsByUsuarioId(idUsuario);

    }

}

