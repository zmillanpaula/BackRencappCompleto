package generation.rencapp.controller;

import generation.rencapp.models.Funcionario;
import generation.rencapp.models.TipoUsuario;
import generation.rencapp.models.Usuario;
import generation.rencapp.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/menu")
    public String menu() {
        return "index.html";
    }

    @GetMapping("/crear")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("tipoUsuario", TipoUsuario.values());
        return "registro-usuario";
    }
    @PostMapping("/Crear")
    public String guardarUsuarioNuevo(@ModelAttribute Usuario usuario) {
        Usuario usuarioNuevo = usuarioService.guardarUsuario(usuario);
        return "Usuario Creado";
    }
    @PostMapping("/crear/funcionario")
    public String guardarFuncionarioNuevo(@ModelAttribute Funcionario funcionario) {
        Usuario usuarioNuevo = usuarioService.guardarUsuario(funcionario);
        usuarioNuevo.setTipo(TipoUsuario.FUNCIONARIO);
        return "Usuario Creado";
    }
    @PostMapping("/crear/admin")
    public String guardarAdmin(@ModelAttribute Usuario usuario) {
        Usuario usuarioNuevo = usuarioService.guardarUsuario(usuario);
        usuarioNuevo.setTipo(TipoUsuario.ADMIN);
        return "Usuario Creado";
    }

}
