package generation.rencapp.API;

import generation.rencapp.models.Departamento;
import generation.rencapp.service.DepartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/departamentos")
@CrossOrigin("*")

public class DepartamentoRestController {

    @Autowired
    private DepartamentoService departamentoService;

    @GetMapping("/lista")
    public List <Departamento> buscarLista() {
        return departamentoService.findAll();
    }

    @GetMapping("/{id}")
    public Departamento buscarPorId(@PathVariable Long id) {
        return departamentoService.findById(id);
    }

    @PostMapping("/crear")
    public Departamento crearDepartamento(@RequestBody Departamento departamento) {
        return departamentoService.save(departamento);
    }

    @DeleteMapping("/eliminar/{id}")
    public String eliminarDepartamento(@PathVariable Long id) {
        departamentoService.deleteById(id);
        return "Departamento eliminado";
    }

    @PutMapping("/editar/{id}")
    public Departamento editarDepartamento(@PathVariable Long id, @RequestBody Departamento departamento) {
        departamento.setId(id);
        return departamentoService.save(departamento);
    }
}
