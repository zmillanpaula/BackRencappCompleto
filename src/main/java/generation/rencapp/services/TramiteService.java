package generation.rencapp.services;
import generation.rencapp.models.Tramite;
import java.time.LocalDate;
import java.util.List;

public interface TramiteService {

    List<Tramite> findByServicioId(Servicio servicio);

    List<Tramite> findAll();

    void deleteById(Long id);

    Tramite saveTramite(Tramite tramiteNuevo);

    List<Tramite> findByFechaCreacion(LocalDate fecha);

}
