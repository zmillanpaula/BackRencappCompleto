package generation.rencapp.security;

import generation.rencapp.models.Funcionario;
import generation.rencapp.models.TipoUsuario;
import generation.rencapp.models.Usuario;
import generation.rencapp.models.Vecino;
import generation.rencapp.services.UsuarioServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthRestController {
    private final AuthenticationManager authenticationManager;
    private final UsuarioServiceImpl usuarioService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser (@RequestBody LoginDTO loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UsuarioDetailsImpl userDetails = (UsuarioDetailsImpl) authentication.getPrincipal();

        String jwtToken = jwtUtils.generateJwtToken(userDetails);

        return new ResponseEntity<>(new JwtResponse(jwtToken, userDetails.getEmail()), HttpStatus.OK);
    }

    @PostMapping("/registro")
    public ResponseEntity<?> registerUser(@RequestBody SignupDTO solicitud) {
        Vecino nuevoVecino = new Vecino();
        nuevoVecino.setNombre(solicitud.getNombre());
        nuevoVecino.setApellido(solicitud.getApellido());
        nuevoVecino.setEmail(solicitud.getEmail());
        nuevoVecino.setPassword(passwordEncoder.encode(solicitud.getPassword()));
        nuevoVecino.setTipo(TipoUsuario.VECINO);

        Usuario usuario = usuarioService.guardarUsuario(nuevoVecino);

        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }

    @PostMapping("/registro/funcionario")
    public ResponseEntity<Usuario> registerFuncionario(@RequestBody SignupDTO solicitud) {
        Funcionario funcionarioNuevo = new Funcionario();
        funcionarioNuevo.setNombre(solicitud.getNombre());
        funcionarioNuevo.setApellido(solicitud.getApellido());
        funcionarioNuevo.setEmail(solicitud.getEmail());
        funcionarioNuevo.setPassword(passwordEncoder.encode(solicitud.getPassword()));
        funcionarioNuevo.setDepartamento(solicitud.getDepartamento());
        funcionarioNuevo.setTipo(TipoUsuario.FUNCIONARIO);


        Usuario usuario = usuarioService.guardarUsuario(funcionarioNuevo);

        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }

    @PostMapping("/registro/admin")
    public ResponseEntity<Usuario> registerAdmin(@RequestBody SignupDTO solicitud) {
        Usuario usuarioNuevo = new Usuario();
        usuarioNuevo.setNombre(solicitud.getNombre());
        usuarioNuevo.setApellido(solicitud.getApellido());
        usuarioNuevo.setEmail(solicitud.getEmail());
        usuarioNuevo.setPassword(passwordEncoder.encode(solicitud.getPassword()));
        usuarioNuevo.setTipo(TipoUsuario.ADMIN);

        Usuario usuario = usuarioService.guardarUsuario(usuarioNuevo);

        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }

    @AllArgsConstructor
    @Getter
    @Setter
    public class JwtResponse {
        private String token;
        private String email;
    }

    //Branco ponte a escribir
}