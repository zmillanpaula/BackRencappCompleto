package generation.rencapp.api;

import generation.rencapp.models.Horario;
import generation.rencapp.models.Intervalos;
import generation.rencapp.models.Tramite;
import generation.rencapp.services.FuncionarioServiceImpl;
import generation.rencapp.services.HorarioServiceImpl;
import generation.rencapp.services.TramiteServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/horarios")
@CrossOrigin("*")
public class HorarioRestController {

    /**
     * INYECCIÓN DE DEPENDENCIAS
     **/
    @Autowired
    private HorarioServiceImpl horarioServiceImpl;

    @Autowired
    private FuncionarioServiceImpl funcionarioService;
    @Autowired
    private TramiteServiceImpl tramiteServiceImpl;

    /**
     * OBTENER LISTA DE TODOS LOS HORARIOS CREADOS
     **/
    //Clase ResponseEntity<List<Horario>> permite manipular el status de la respuesta
    @GetMapping("/lista")
    public ResponseEntity<List<Horario>> findAllHorario() {
        List<Horario> listaHorarios = horarioServiceImpl.findAll();
        //Retornamos una nueva instancia de ResponseEntity
        //return new ResponseEntity<>(horarioService.findAll(), HttpStatus.OK)
        return new ResponseEntity<>(listaHorarios, HttpStatus.OK);
    }

    /**
     * OBTENER HORARIO POR ID
     **/
    //Al no conocer el tipo de dato que se va a retornar podemos indicar que se retorna un responseEntity<?>
    //@PathVariable = localhost/api/horarios/5
    @GetMapping("/{id}")
    public ResponseEntity<?> findHorarioById(@PathVariable Long id) {
        return new ResponseEntity<>(horarioServiceImpl.findById(id), HttpStatus.OK);
    }


    /** OBTENER HORARIOS POR ESTADO **/
   /* @GetMapping
    public ResponseEntity<?> findHorarioByEstado(@RequestParam String estado) {
        return new ResponseEntity<>(horarioServiceImpl.findHorarioByEstado(estado), HttpStatus.OK);
    } */
    //RequestParam = localhost/api/horarios?estado=DISPONIBLE

    /** CREAR NUEVO HORARIO PARA UN TRAMITE **/
    @Operation(summary = "Crea un rango horario para un trámite")
    @GetMapping("/nuevo/{tramiteId}/{fecha}")
    public ResponseEntity<?> saveHorarioNuevo(@PathVariable LocalDate fecha,
                                              @PathVariable Long tramiteId) {
        //Buscamos al funcionario por su id y lo guardamos en una variable
        Tramite tramiteSelecionado = tramiteServiceImpl.findById(tramiteId);
        Horario horarioNuevo = new Horario();
        horarioNuevo.setFecha(fecha);
        //Al horario que se está enviando en la petición le seteamos el doctor con la variable creada
        horarioNuevo.setTramite(tramiteSelecionado);

        //Finalmente, guardamos el horario llamando al método en el service
        horarioServiceImpl.saveHorario(horarioNuevo);
        return new ResponseEntity<>("El horario se ha creado exitosamente", HttpStatus.CREATED);
    }

    /** EDITAR HORARIO POR ID **/

    //PUT es el método HTTP para trabajar con edición
    //ID del horario va a ser la variable o criterio de búsqueda del horario a editar
    //Va a recibir un objeto de tipo Horario con los campos editados
    @PutMapping("/editar/{id}")
    public ResponseEntity<Horario> updateHorarioById(@PathVariable Long id,
                                                     @RequestBody Horario horarioEditado) {
        //Buscamos primero, el horario por ID
        Horario horarioSeleccionado = horarioServiceImpl.findById(id);

        //Al horarioEditado que viene en el cuerpo de la petición, le seteamos el ID de nuestro horarioSeleccionado
        horarioEditado.setId(horarioSeleccionado.getId());

        //Al horarioeEditado le seteamos el doctor del horarioSeleccionado
        horarioEditado.setTramite(horarioSeleccionado.getTramite());

        //Retornamos una nueva ResponseEntity pasando como argumento el método de guardar horario
        return new ResponseEntity<>(horarioServiceImpl.saveHorario(horarioEditado), HttpStatus.OK);
    }


    /** ELIMINAR HORARIO POR ID **/
    //DELETE es el método HTTP que me permite eliminar registros
    //ID del horario va a ser el atributo por el que voy a filtrar
    //Vamos a recibir un dato de tipo Long que es el ID a través de la ruta
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<String> deleteHorarioById(@PathVariable Long id) {
        //LLamamos al método del service para borrar por ID
        horarioServiceImpl.deleteHorarioById(id);
        //Retornamos un nuevo ResponseEntity indicando un mensaje en el cuerpo de la respuesta
        return new ResponseEntity<>("El horario ha sido eliminado", HttpStatus.OK);
    }



    /** GENERAR INTÉRVALOS DE UNA HORA A PARTIR DEL ID DEL FUNCIONARIO Y FECHA DEL HORARIO CREADO **/
    @Operation(summary = "Genera intervalos de una hora en base al rango horario de un trámite")
    @GetMapping("/disponibilidad/{tramiteId}/{horarioId}")
    public ResponseEntity<List<Intervalos>> findIntervalosByTramiteIdAndFecha(@PathVariable Long tramiteId, @PathVariable Long horarioId, @RequestParam String fecha) {

        String fullPath = "/Horario/" + fecha;
        System.out.println(fecha);
        LocalDate fechaSeleccionada = LocalDate.parse(fecha);
        //Creamos una variable para almacenar la lista
        List<Intervalos> intervalosDisponibles = horarioServiceImpl.obtenerIntervalosDeUnaHora(tramiteId, horarioId, fechaSeleccionada);

        return new ResponseEntity<>(intervalosDisponibles, HttpStatus.OK);
    }

}
