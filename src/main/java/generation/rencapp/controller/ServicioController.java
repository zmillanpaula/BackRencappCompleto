package generation.rencapp.controller;

import generation.rencapp.models.Servicio;
import generation.rencapp.service.ServicioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/servicio")
public class ServicioController {
    @Autowired
    private ServicioServiceImpl servicioServiceImpl;

    // mostrar todos los servicios asociados a al id del dep
    @GetMapping("/lista")
    public String listarServicio(Model model, @RequestParam Long departamentoId) {
        List<Servicio> listaServicio = servicioServiceImpl.findByDepartamentoId(departamentoId);
        model.addAttribute("servicio", listaServicio);
        return "lista-servicios.html";
    }
}
