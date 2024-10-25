package generation.rencapp.security;

import generation.rencapp.models.TipoUsuario;
import generation.rencapp.models.Usuario;
import generation.rencapp.services.UsuarioServiceImpl;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class InitialDataLoader {
    @Autowired
    private UsuarioServiceImpl usuarioServiceImpl;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        if (usuarioServiceImpl.existeUsuarioPorEmail("admin@renca.cl")) {
            System.out.println("Ya existe un usuario admin");
        } else {
            Usuario admin = Usuario.builder()
                    .nombre("Administrador")
                    .email("admin@renca.cl")
                    .password(passwordEncoder.encode("admin123"))
                    .tipo(TipoUsuario.ADMIN)
                    .build();
            usuarioServiceImpl.guardarUsuario(admin);
        }
    }
}
