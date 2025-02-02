package generation.rencapp.api;

import generation.rencapp.models.Agendamiento;
import generation.rencapp.services.AgendamientoServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/agendamientos")
@CrossOrigin("*")
public class AgendamientoRestController {

    /** INYECCIÓN DE DEPENDENCIAS **/
    @Autowired
    private AgendamientoServiceImpl agendamientoService;


    /** GENERAR NUEVA CITA CON EL ID DEL FUNCIONARIO Y EL ID DEL VECINO **/
    //Método post para generar la nueva cita
    @Operation (summary = "Verifica disponibilidad de hora y crea agendamiento")
    @GetMapping("/agendar/{usuarioId}/{tramiteId}")
    public ResponseEntity<Agendamiento> agendar(@PathVariable Long usuarioId,
                                            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd-HH:mm") LocalDateTime fechaHora, @PathVariable Long tramiteId) {


        return new ResponseEntity<>(agendamientoService.agendar(usuarioId, fechaHora, tramiteId), HttpStatus.OK);

    }

    @Operation (summary = "Muestra agendamiento en base a id")
    @GetMapping("/porcita/{agendamientoId}")
    public ResponseEntity<Agendamiento> verPorID(@PathVariable Long agendamientoId) {
        return new ResponseEntity<>(agendamientoService.buscarPorId(agendamientoId), HttpStatus.OK);
    }


    @Operation (summary = "Cambia estado de agendado a cancelado en agendamiento según su id")
    @GetMapping("/{agendamientoId}/suspender")
    public ResponseEntity<Agendamiento> suspenderPorID(@PathVariable Long agendamientoId) {
        return new ResponseEntity<>(agendamientoService.suspenderAgendamiento(agendamientoId), HttpStatus.OK);
    }

    @Operation (summary = "Muestra agendamientos en base a id de usuario")
    @GetMapping("/{usuarioId}/ver")
    public ResponseEntity<List<Agendamiento>> verAgendamientosPorVecino(@PathVariable Long usuarioId, @RequestParam String tipo){
        return new ResponseEntity<>(agendamientoService.agendamientosbyVecinoId(usuarioId, tipo), HttpStatus.OK);
    }


    @Operation (summary = "Muestra agendamientos para un tipo de trámite")
    @GetMapping("/{tramiteId}/veragendamientos")
    public ResponseEntity<List<Agendamiento>> verAgendamientosPorTramite(@PathVariable Long tramiteId){
        return new ResponseEntity<>(agendamientoService.agendamientosbyTramiteId(tramiteId), HttpStatus.OK);
    }

    @Operation (summary = "Muestra agendamientos para una fecha y tipo de trámite particular")
    @GetMapping("/{tramiteId}/agendaporfecha")
    public ResponseEntity<List<Agendamiento>> verAgendaPorTramiteyFecha(@RequestParam LocalDate fecha, @PathVariable Long tramiteId) {
        return new ResponseEntity<>(agendamientoService.agendamientosbyFechaAndTramiteId(fecha, tramiteId), HttpStatus.OK);
    }

}
