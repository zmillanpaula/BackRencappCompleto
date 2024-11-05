package generation.rencapp.api;
import generation.rencapp.models.Solicitud;
import generation.rencapp.models.Tramite;
import generation.rencapp.security.AuthRestController;
import generation.rencapp.services.SolicitudServiceImpl;
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
@RequestMapping("/api/solicitudes")
@CrossOrigin("*")
public class SolicitudRestController {

    @Autowired
    private SolicitudServiceImpl solicitudServiceImpl;

    @Autowired
    private TramiteServiceImpl tramiteServiceImpl;


    @Operation(summary = "Obtiene lista de todas las solicitudes creadas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de todas las solicitudes, obtenidas exitosamente ", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthRestController.JwtResponse.class))),
            @ApiResponse(responseCode = "401", description = "Error en la obtencion en la lista de solicitudes")
    })
    @GetMapping("/lista")
    public ResponseEntity<List<Solicitud>> findAllSolicitudes() {
        List<Solicitud> listaSolicitudes = solicitudServiceImpl.findAll();
        return new ResponseEntity<>(listaSolicitudes, HttpStatus.OK);
    }


    @Operation(summary = "Obtiene la lista de lista de solicitudes según la id del vecino")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de solicitudes del usuario/vecino obtenida exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthRestController.JwtResponse.class))),
            @ApiResponse(responseCode = "401", description = "Error en la obtención de la lista de solicitudes del usuario/vecino")
    })
    @GetMapping("/lista/{vecinoid}")
    public ResponseEntity<List<Solicitud>> findByVecinoId(@PathVariable Long vecinoid) {
        List<Solicitud> listaSolicitudesVecino = solicitudServiceImpl.findByVecinoId(vecinoid);
        return new ResponseEntity<>(listaSolicitudesVecino, HttpStatus.OK);
    }


    @Operation(summary = "Obtiene solicitudes por estado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Muestra todos los estados de solicitudes creados", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthRestController.JwtResponse.class))),
            @ApiResponse(responseCode = "401", description = "Error en la visualización de todos los estados de solicitudes creados")
    })
    /** OBTENER SOLICITUDES POR ESTADO **/
    @GetMapping("/estado")
    public ResponseEntity<?> findSolicitudByEstado(@RequestParam String estado) {

        return new ResponseEntity<>(solicitudServiceImpl.findByEstado(estado), HttpStatus.OK);
    }


    @Operation(summary = "Edita el estado de la solicitud según su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Edita exitosamente el estado de de una solicitud ya creada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthRestController.JwtResponse.class))),
            @ApiResponse(responseCode = "401", description = "Error en la edición del estado de la solicitud según su id")
    })
    @PutMapping("/editarSolicitud/{id}")
    public ResponseEntity<Solicitud> updateSolicitudById(@PathVariable Long id,
                                                     @RequestBody Solicitud solicitudEditada) {
        Solicitud solicitudSeleccionada = solicitudServiceImpl.findById(id);
        solicitudEditada.setId(solicitudSeleccionada.getId());
        return new ResponseEntity<>(solicitudServiceImpl.saveSolicitud(solicitudEditada), HttpStatus.OK);
    }


    @Operation(summary = "Crea nueva solicitud para la plantilla del trámite con el id de tramite")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Crea nueva solicitud para la plantilla del trámite con el id de tramite exitosamente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthRestController.JwtResponse.class))),
            @ApiResponse(responseCode = "401", description = "Error en la creación de nueva solicitud para la plantilla del trámite con el id de tramite ")
    })
    @PostMapping("/nuevo/{tramiteId}")
    public ResponseEntity<?> saveSolicitudNueva(@RequestBody Solicitud solicitudNueva,
                                                @PathVariable Long tramiteId) {
        Tramite tramiteSeleccionado = tramiteServiceImpl.findById(tramiteId);
        solicitudNueva.setTramite(tramiteSeleccionado);
        solicitudServiceImpl.saveSolicitud(solicitudNueva);
        return new ResponseEntity<>("La solicitud se ha creado exitosamente", HttpStatus.CREATED);
    }


}
