package generation.rencapp.api;
import generation.rencapp.models.Servicio;
import generation.rencapp.models.Tramite;
import generation.rencapp.services.ServicioService;
import generation.rencapp.services.TramiteServiceImpl;
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
@CrossOrigin("*")
@RequestMapping("/api/tramites")
public class TramiteRestController {

    @Autowired
    private TramiteServiceImpl tramiteServiceImpl;

    @Autowired
    private ServicioService servicioService;


    @Operation(summary = "Obtiene lista de todos los trámites creados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de todas las plantillas de tramites creadas exitososamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthRestController.JwtResponse.class))),
            @ApiResponse(responseCode = "401", description = "Error en la obtención de la lista de todas las plantillas de tramites creadas")
    })
    @GetMapping("/lista")
    public ResponseEntity<List<Tramite>> findAllTramites() {
        List<Tramite> listaTramites = tramiteServiceImpl.findAll();
        return new ResponseEntity<>(listaTramites, HttpStatus.OK);
    }


    @Operation(summary = "Obtiene trámite por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Muestra la plantilla de trámite según id exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthRestController.JwtResponse.class))),
            @ApiResponse(responseCode = "401", description = "Error en la visualización de las plantilla de trámite según id")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> findTramiteById(@PathVariable Long id) {
        return new ResponseEntity<>(tramiteServiceImpl.findById(id), HttpStatus.OK);
    }


    @Operation(summary = "Elimina trámite por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Elimina plantilla de tramite según id exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthRestController.JwtResponse.class))),
            @ApiResponse(responseCode = "401", description = "Error en la eliminación de las plantillas de tramite según id")
    })
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<String> deleteTrámiteById(@PathVariable Long id) {
        tramiteServiceImpl.deleteById(id);
        return new ResponseEntity<>("El trámite ha sido eliminado", HttpStatus.OK);
    }


    @Operation(summary = "Crea nuevo trámite para el servicio con el id del servicio")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Crea nuevo plantilla de trámite para el servicio con el id del servicio exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthRestController.JwtResponse.class))),
            @ApiResponse(responseCode = "401", description = "Error en la creación de nuevo plantilla de trámite para el servicio con el id del servicio")
    })
    @PostMapping("/nuevo/{servicioId}")
    public ResponseEntity<?> saveTramiteNuevo(@RequestBody Tramite tramiteNuevo,
                                              @PathVariable Long servicioId) {
        Servicio servicioSeleccionado = servicioService.findById(servicioId);
        tramiteNuevo.setServicio(servicioSeleccionado);
        tramiteServiceImpl.saveTramite(tramiteNuevo);
        return new ResponseEntity<>("El trámite se ha creado exitosamente.", HttpStatus.CREATED);
    }


    @Operation(summary = "Modidificar el trámite según id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Modidifica la plantilla del trámite según id exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthRestController.JwtResponse.class))),
            @ApiResponse(responseCode = "401", description = "Error en la edición de la plantilla del trámite según id")
    })
    @PutMapping("/editar/{id}")
    public ResponseEntity<Tramite> updateTramite(@PathVariable Long id, @RequestBody Tramite tramiteEditado) {
        Tramite tramiteActual = tramiteServiceImpl.findById(id);
        tramiteEditado.setId(tramiteActual.getId());
        tramiteEditado.setServicio(tramiteActual.getServicio());
        tramiteServiceImpl.saveTramite(tramiteEditado);
        return new ResponseEntity<>(tramiteEditado, HttpStatus.OK);
    }
}
