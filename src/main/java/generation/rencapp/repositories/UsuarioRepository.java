package generation.rencapp.repositories;

import generation.rencapp.models.TipoUsuario;
import generation.rencapp.models.Usuario;
import generation.rencapp.models.Vecino;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);

    Boolean existsByEmail(String email);

    Vecino findByIdAndTipoUsuario(Long id, TipoUsuario tipoUsuario);


}
