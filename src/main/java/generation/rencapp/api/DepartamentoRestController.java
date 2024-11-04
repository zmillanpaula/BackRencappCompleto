package generation.rencapp.api;
import generation.rencapp.models.Departamento;
import generation.rencapp.services.DepartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;

@RestController
@RequestMapping("api/departamentos")
@CrossOrigin("*")
public class DepartamentoRestController {

    @Autowired
    private DepartamentoService departamentoService;



    @Operation (summary = "Mostrar lista de Departamentos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de Departamentos", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Departamento.class))),
            @ApiResponse(responseCode = "401", description = "Error en la búsqueda de lista de departamento")
    })
    @GetMapping("/lista")
    public ResponseEntity<List<Departamento>> buscarLista() {
        List<Departamento> departamentos = departamentoService.findAll();
        return new ResponseEntity<>(departamentos, HttpStatus.OK);
    }



    @Operation (summary = "Buscar departamento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Departamento encontrado con éxito", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Departamento.class))),
            @ApiResponse(responseCode = "401", description = "Error en la búsqueda del departamento")
    })
    @GetMapping
    public ResponseEntity<Departamento> buscarPorId(@RequestParam Long search) {
        Departamento departamento = departamentoService.findById(search);
        if (departamento != null) {
            return new ResponseEntity<>(departamento, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



    @Operation (summary = "Crear departamento como administrador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Creación de departamento exitoso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Departamento.class))),
            @ApiResponse(responseCode = "401", description = "Error en la creación del departamento")
    })
    @PostMapping("/crear")
    public ResponseEntity<Departamento> crearDepartamento(@RequestBody Departamento departamento) {
        Departamento nuevoDepartamento = departamentoService.save(departamento);
        return new ResponseEntity<>(nuevoDepartamento, HttpStatus.CREATED);
    }



    @Operation (summary = "Eliminar departamento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Eliminación del departamento exitoso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Departamento.class))),
            @ApiResponse(responseCode = "401", description = "Error en la eliminación del departamento")
    })
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarDepartamento(@PathVariable Long id) {
        departamentoService.deleteById(id);
        return new ResponseEntity<>("Departamento eliminado", HttpStatus.OK);
    }



    @Operation (summary = "Editar departamento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Edición del departamento exitoso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Departamento.class))),
            @ApiResponse(responseCode = "401", description = "Error en la edición del departamento")
    })
    @PutMapping("/editar/{id}")
    public ResponseEntity<Departamento> editarDepartamento(@PathVariable Long id, @RequestBody Departamento departamento) {
        departamento.setId(id);
        Departamento departamentoEditado = departamentoService.save(departamento);
        return new ResponseEntity<>(departamentoEditado, HttpStatus.OK);
    }
}
