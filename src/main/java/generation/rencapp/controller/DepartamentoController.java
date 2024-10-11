package generation.rencapp.controller;

import generation.rencapp.models.Departamento;
import generation.rencapp.service.DepartamentoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
@RequestMapping("/departamento")
public class DepartamentoController {

    @Autowired
    private DepartamentoServiceImpl departamentoServiceImpl;

    //Obtener una ruto para listar en las vistas (que se vea en el html)
    @GetMapping("/lista")
    public String listarDepartamento(Model model) {
        List<Departamento> departamento = departamentoServiceImpl.findAll();
        model.addAttribute ("departamento", departamento);
        return "lista-departamentos.html";
    }

    // Obtener la ruta para crear los departamento (get mapping
    @GetMapping("/crear")
    public String crearDepartamento(Model model, @RequestParam Long id) {
        Departamento departamento = departamentoServiceImpl.findById(id);
        model.addAttribute ("departamento", departamento);
        return "crear-departamento.html";
    }

    // post de esa ruta (postmating)⬆
    @PostMapping("/crear")
    public String guardarDepartamento(@ModelAttribute Departamento departamento) {
        departamentoServiceImpl.save(departamento);
        return "redirect:/departamento/lista";
    }
}


