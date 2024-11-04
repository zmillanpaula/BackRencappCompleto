/*package generation.rencapp.API;
import generation.rencapp.models.Servicio;
import generation.rencapp.service.ServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("api/servicios")
@CrossOrigin("*")
public class ServicioRestController {
    @Autowired
    private ServicioService servicioService;

    @GetMapping("/lista")
    public ResponseEntity<List<Servicio>> buscarLista() {
        return new ResponseEntity<>(servicioService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Servicio buscarPorId(@PathVariable Long id) {
        return servicioService.findById(id);}
    @PostMapping("/crear")
    public Servicio crearServicio(@RequestBody Servicio Servicio) {
        return servicioService.save(Servicio);
    }
    @DeleteMapping("/eliminar/{id}")
    public String eliminarServicio(@PathVariable Long id) {
        servicioService.deleteById(id);
        return "Servicio eliminado";
    }
    @PutMapping("/editar/{id}")
    public Servicio editarServicio(@PathVariable Long id, @RequestBody Servicio Servicio) {
        Servicio.setId(id);
        return servicioService.save(Servicio);
    }
}*/

package generation.rencapp.API;

import generation.rencapp.models.Departamento;
import generation.rencapp.models.Servicio;
import generation.rencapp.service.DepartamentoServiceImpl;
import generation.rencapp.service.ServicioService;
import generation.rencapp.service.ServicioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/servicios")
@CrossOrigin("*")
public class ServicioRestController {

    @Autowired
    private ServicioServiceImpl servicioService;

    @Autowired
    private DepartamentoServiceImpl departamentoService;

    @GetMapping("/lista")
    public ResponseEntity<List<Servicio>> buscarLista() {
        return new ResponseEntity<>(servicioService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Servicio> buscarPorId(@PathVariable Long id) {
        Servicio servicio = servicioService.findById(id);
        if (servicio != null) {
            return new ResponseEntity<>(servicio, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /*@PostMapping("/crear")
    public ResponseEntity<Servicio> crearServicio(@RequestBody Servicio servicio) {
        Servicio nuevoServicio = servicioService.save(servicio);
        return new ResponseEntity<>(nuevoServicio, HttpStatus.CREATED);
    }*/


    @GetMapping("/{idDepartamento}/verlistaservicios")
    public ResponseEntity<List<Servicio>> buscarListaServiciosPorDepartamento(@PathVariable Long idDepartamento) {
        Departamento departamento = departamentoService.findById(idDepartamento);
        if  (departamento == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<Servicio> listaServicio = servicioService.findByDepartamentoId(idDepartamento);
        return new ResponseEntity<>(listaServicio, HttpStatus.OK);
    }
    @PostMapping("/{idDepartamento}/crear")
    public ResponseEntity<Servicio> crearServicio(@RequestBody Servicio servicio, @PathVariable Long idDepartamento) {
        Departamento departamento = departamentoService.findById(idDepartamento);
        if (servicio.getNombre() == null || servicio.getDescripcion() == null || departamento == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        servicio.setDepartamento(departamento);
        Servicio nuevoServicio = servicioService.save(servicio);
        return new ResponseEntity<>(nuevoServicio, HttpStatus.CREATED);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarServicio(@PathVariable Long id) {
        servicioService.deleteById(id);
        return new ResponseEntity<>("Servicio eliminado", HttpStatus.OK);
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<Servicio> editarServicio(@PathVariable Long id, @RequestBody Servicio servicio) {
        Departamento departamento= departamentoService.findById(servicioService.findById(id).getDepartamento().getId());
        servicio.setId(id);
        servicio.setDepartamento(departamento);
        Servicio servicioEditado = servicioService.save(servicio);
        return new ResponseEntity<>(servicioEditado, HttpStatus.OK);
    }
}
