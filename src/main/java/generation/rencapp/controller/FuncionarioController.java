package generation.rencapp.controller;

import generation.rencapp.repositories.FuncionarioRepository;
import generation.rencapp.services.FuncionarioServiceImpl;
import generation.rencapp.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/funcionario")
public class FuncionarioController {
    @Autowired
    private FuncionarioServiceImpl funcionarioServiceImpl;

    @Autowired
    private UsuarioService usuarioService;



}
