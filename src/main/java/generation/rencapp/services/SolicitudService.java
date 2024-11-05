package generation.rencapp.services;
import generation.rencapp.models.Solicitud;
import generation.rencapp.models.Tramite;

import java.time.LocalDate;
import java.util.List;

public interface SolicitudService {

    List<Solicitud> findAll();

    void deleteById(Long id);

    Solicitud saveSolicitud(Solicitud solicitudNueva);

    List<Solicitud> findByFechaCreacion(LocalDate fecha);

    List<Solicitud> findByVecinoId(Long vecino);

    List<Solicitud> findByEstado(String estado);

    List<Solicitud> findByTramiteId(Tramite tramite);


}
