package generation.rencapp.repositories;

import generation.rencapp.models.Intervalos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;

@Repository
public interface IntervaloRepository extends JpaRepository<Intervalos, Long> {

    Intervalos findIntervalosByHoraAndHorarioId(LocalTime hora, Long horarioId);

}
