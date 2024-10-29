package generation.rencapp.api;

import generation.rencapp.models.Funcionario;
import generation.rencapp.services.FuncionarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/funcionario")
public class FuncionarioRestController {

    @Autowired
    private FuncionarioServiceImpl funcionarioServiceImpl;

    @GetMapping("/lista")
    public List<Funcionario> listaFuncionarios() {
        return funcionarioServiceImpl.findAllFuncionario();
    }
}
