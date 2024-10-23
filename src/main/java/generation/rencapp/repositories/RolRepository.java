package generation.rencapp.repositories;

import generation.rencapp.models.Rol;
import generation.rencapp.models.TipoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long>{
    Optional<Rol> findByUser(TipoUsuario user);
}
