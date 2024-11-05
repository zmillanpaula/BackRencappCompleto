package generation.rencapp.api;

import generation.rencapp.models.Funcionario;
import generation.rencapp.models.TipoUsuario;
import generation.rencapp.models.Usuario;
import generation.rencapp.models.Vecino;
import generation.rencapp.security.JwtUtils;
import generation.rencapp.dto.LoginDTO;
import generation.rencapp.dto.SignupDTO;
import generation.rencapp.security.UsuarioDetailsImpl;
import generation.rencapp.services.UsuarioServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @Operation(summary = "Inicia sesión con un usuario existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Inicio de sesión exitoso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = JwtResponse.class))),
            @ApiResponse(responseCode = "401", description = "Credenciales inválidas")
    })
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser (@RequestBody LoginDTO loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UsuarioDetailsImpl userDetails = (UsuarioDetailsImpl) authentication.getPrincipal();

        String jwtToken = jwtUtils.generateJwtToken(userDetails);
        Long userId = usuarioService.buscarUsuarioPorEmail(loginRequest.getEmail()).get().getId();
        return new ResponseEntity<>(new JwtResponse(userId, jwtToken, userDetails.getEmail(), userDetails.getAuthorities().toString()), HttpStatus.OK);
    }

    @Operation(summary = "Registro de un usuario como Vecino")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Registro exitoso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Vecino.class))),
            @ApiResponse(responseCode = "400", description = "Error en el registro (usuario ya existe)")
    })
    @PostMapping("/registro")
    public ResponseEntity<?> registerUser(@RequestBody SignupDTO solicitud) {

        Vecino nuevoVecino = new Vecino();
        nuevoVecino.setNombre(solicitud.getNombre());
        nuevoVecino.setApellido(solicitud.getApellido());
        nuevoVecino.setFechaNacimiento(solicitud.getFechaNacimiento());
        nuevoVecino.setNumeroDeDocumento(solicitud.getNumeroDeDocumento());
        nuevoVecino.setNumeroTelefono(solicitud.getNumeroTelefono());
        nuevoVecino.setDireccion(solicitud.getDireccion());
        nuevoVecino.setEmail(solicitud.getEmail());
        nuevoVecino.setPassword(passwordEncoder.encode(solicitud.getPassword()));
        nuevoVecino.setTipo(TipoUsuario.VECINO);

        Usuario usuario = usuarioService.guardarUsuario(nuevoVecino);

        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }

    @Operation(summary = "Registro de un usuario como Funcionario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Registro exitoso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Funcionario.class))),
            @ApiResponse(responseCode = "400", description = "Error en el registro (usuario ya existe)")
    })
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

    @Operation(summary = "Registro de un usuario como Administrador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Registro exitoso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Usuario.class))),
            @ApiResponse(responseCode = "400", description = "Error en el registro (usuario ya existe)")
    })
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
        private long id;
        private String token;
        private String email;
        private String tipoUser;
    }

}