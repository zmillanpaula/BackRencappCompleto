package generation.rencapp.api;
import generation.rencapp.models.Departamento;
import generation.rencapp.models.Servicio;
import generation.rencapp.services.DepartamentoServiceImpl;
import generation.rencapp.services.ServicioServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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



    @Operation(summary = "Mostrar lista de Servicios")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de Servicios", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Servicio.class))),
            @ApiResponse(responseCode = "401", description = "Error en la creación del servicio")
    })
    @GetMapping("/lista")
    public ResponseEntity<List<Servicio>> buscarLista() {
        return new ResponseEntity<>(servicioService.findAll(), HttpStatus.OK);
    }



    @Operation(summary = "Buscar servicio por Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de Servicios", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Servicio.class))),
            @ApiResponse(responseCode = "401", description = "Error en la búsqueda de servicio")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Servicio> buscarPorId(@PathVariable Long id) {
        Servicio servicio = servicioService.findById(id);
        if (servicio != null) {
            return new ResponseEntity<>(servicio, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



    @Operation(summary = "Ver lista de servicios por departamento Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de Servicios por departamento Id", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Servicio.class))),
            @ApiResponse(responseCode = "401", description = "Error en búsqueda de servicios por departamento Id")
    })
    @GetMapping("/{idDepartamento}/verlistaservicios")
    public ResponseEntity<List<Servicio>> buscarListaServiciosPorDepartamento(@PathVariable Long idDepartamento) {
        Departamento departamento = departamentoService.findById(idDepartamento);
        if  (departamento == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<Servicio> listaServicio = servicioService.findByDepartamentoId(idDepartamento);
        return new ResponseEntity<>(listaServicio, HttpStatus.OK);
    }



    @Operation(summary = "Crear servicio por departamento Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Crear Servicio por departamento Id", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Servicio.class))),
            @ApiResponse(responseCode = "401", description = "Error en la creación del servicio por departamento Id")
    })
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



    @Operation(summary = "Eliminar servicio")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Eliminar Servicio", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Servicio.class))),
            @ApiResponse(responseCode = "401", description = "Error al eliminar el servicio")
    })
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarServicio(@PathVariable Long id) {
        servicioService.deleteById(id);
        return new ResponseEntity<>("Servicio eliminado", HttpStatus.OK);
    }



    @Operation(summary = "Modificar servicio")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Servicio modificado con éxito", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Servicio.class))),
            @ApiResponse(responseCode = "401", description = "Error al modificar el servicio")
    })
    @PutMapping("/editar/{id}")
    public ResponseEntity<Servicio> editarServicio(@PathVariable Long id, @RequestBody Servicio servicio) {
        Departamento departamento= departamentoService.findById(servicioService.findById(id).getDepartamento().getId());
        servicio.setId(id);
        servicio.setDepartamento(departamento);
        Servicio servicioEditado = servicioService.save(servicio);
        return new ResponseEntity<>(servicioEditado, HttpStatus.OK);
    }
}
