package generation.rencapp.services;

import generation.rencapp.models.Usuario;
import generation.rencapp.repositories.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    @Override
    public Usuario guardarUsuario(Usuario newUsuario) {
        return usuarioRepository.save(newUsuario);
    }

    public Optional<Usuario> buscarUsuarioPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }


    public Boolean existeUsuarioPorEmail(String email) {
        return usuarioRepository.existsByEmail(email);
    }


}
