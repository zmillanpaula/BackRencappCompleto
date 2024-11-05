package generation.rencapp.repositories;
import generation.rencapp.models.Solicitud;
import generation.rencapp.models.Tramite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SolicitudRepository extends JpaRepository<Solicitud, Long> {

    Solicitud findById(long id);

    List<Solicitud> findAllByEstado(Solicitud.EstadoSolicitud estado);

    List<Solicitud> findByTramiteId(Long tramiteId);

    List<Solicitud> findByCreatedAt(LocalDate fecha);

    List<Solicitud> findByVecinoId(Long vecinoId);
}
