package generation.rencapp.repositories;
import generation.rencapp.models.Tramite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;


@Repository
public interface TramiteRepository extends JpaRepository<Tramite, Long> {

    Tramite findById(long id);

    List<Tramite> findByNombre(String nombre);

    List<Tramite> findByServicioId(Long servicioId);

    List<Tramite> findByCreatedAt(LocalDate fecha);

}
