/*package generation.rencapp.API;

import generation.rencapp.models.Departamento;
import generation.rencapp.service.DepartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Departamento> buscarPorId(@PathVariable Long id) {
        return new ResponseEntity<>(departamentoService.findById(id), HttpStatus.OK);
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
}*/

package generation.rencapp.API;

import generation.rencapp.models.Departamento;
import generation.rencapp.service.DepartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/departamentos")
@CrossOrigin("*")
public class DepartamentoRestController {

    @Autowired
    private DepartamentoService departamentoService;

    @GetMapping("/lista")
    public ResponseEntity<List<Departamento>> buscarLista() {
        List<Departamento> departamentos = departamentoService.findAll();
        return new ResponseEntity<>(departamentos, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Departamento> buscarPorId(@RequestParam Long search) {
        Departamento departamento = departamentoService.findById(search);
        if (departamento != null) {
            return new ResponseEntity<>(departamento, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/crear")
    public ResponseEntity<Departamento> crearDepartamento(@RequestBody Departamento departamento) {
        Departamento nuevoDepartamento = departamentoService.save(departamento);
        return new ResponseEntity<>(nuevoDepartamento, HttpStatus.CREATED);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarDepartamento(@PathVariable Long id) {
        departamentoService.deleteById(id);
        return new ResponseEntity<>("Departamento eliminado", HttpStatus.OK);
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<Departamento> editarDepartamento(@PathVariable Long id, @RequestBody Departamento departamento) {
        departamento.setId(id);
        Departamento departamentoEditado = departamentoService.save(departamento);
        return new ResponseEntity<>(departamentoEditado, HttpStatus.OK);
    }
}
