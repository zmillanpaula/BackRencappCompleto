package generation.rencapp.api;

import generation.rencapp.models.Funcionario;
import generation.rencapp.models.Vecino;
import generation.rencapp.services.VecinoService;
import generation.rencapp.services.VecinoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/vecinos")
public class VecinoRestController {
    @Autowired
    private VecinoServiceImpl vecinoService;

    @GetMapping("/lista")
    public List<Vecino> listaVecinos() {
        return vecinoService.findAllVecino();
    }
}
