package generation.rencapp.repositories;

import generation.rencapp.models.Vecino;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VecinoRepository extends JpaRepository<Vecino, Long> {
}
