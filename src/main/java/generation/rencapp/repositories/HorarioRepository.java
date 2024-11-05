package generation.rencapp.repositories;

import generation.rencapp.models.Horario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface HorarioRepository extends JpaRepository<Horario, Long> {

    //List<Horario> findByEstado(String estado);

    //List<Horario> findByFuncionarioId(Long id);

    Horario findHorarioByFecha(LocalDate fecha);

    Horario findByTramiteIdAndFecha(Long tramiteId, LocalDate fechaHora);



}
