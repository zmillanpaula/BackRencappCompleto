package generation.rencapp.services;

import generation.rencapp.models.*;
import generation.rencapp.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

//Anotaciones
@Service
public class AgendamientoServiceImpl implements AgendamientoService{

    //Inyección de dependencias
    @Autowired
    private AgendamientoRepository agendamientoRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private VecinoRepository vecinoRepository;

    @Autowired
    private HorarioServiceImpl horarioServiceImpl;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TramiteRepository tramiteRepository;

    @Autowired
    private IntervaloRepository intervaloRepository;
    @Autowired
    private HorarioRepository horarioRepository;

    @Autowired
    private NotificacionServicesImpl notificacionService;


    //Método para agendar cita
    @Override
    public Agendamiento agendar( Long usuarioId, LocalDateTime fechaHora, Long tramiteId) {

        Usuario usuario = usuarioRepository.findById(usuarioId).get();
        Tramite tramite = tramiteRepository.findById(tramiteId).get();

        //1-) Validar si la hora esta contenida dentro del intervalo y guardamos el resultado en una variable
        Intervalos esValido = intervaloRepository.findIntervalosByHoraAndHorarioId(fechaHora.toLocalTime(), horarioRepository.findHorarioByFecha(fechaHora.toLocalDate()).getId());

        if (esValido.getEstado().equals(Intervalos.Estadointervalo.AGENDADA)) {
            throw new IllegalArgumentException("La hora indicada no está disponible");
        }

        //3-) Generar la cita y guardar
        Agendamiento nuevoAgendamiento = Agendamiento.builder()
                .usuario(usuario)
                .fechaHora(fechaHora)
                .fecha(fechaHora.toLocalDate())
                .tramite(tramite)
                .estado(Agendamiento.estadoAgendamiento.AGENDADA)
                .build();

        esValido.setEstado(Intervalos.Estadointervalo.AGENDADA);
        intervaloRepository.save(esValido);
        agendamientoRepository.save(nuevoAgendamiento);
        
        Vecino vecino = (Vecino)usuario;
        notificacionService.crearNotificacion(vecino, tramite, nuevoAgendamiento);

        return nuevoAgendamiento;
    }

    public Agendamiento buscarPorId(Long agendamientoId) {
        return agendamientoRepository.findById(agendamientoId).get();
    }

   public Agendamiento suspenderAgendamiento(Long agendamientoId) {
        Agendamiento agendamientoSeleccionado = agendamientoRepository.findById(agendamientoId).get();
        agendamientoSeleccionado.setEstado(Agendamiento.estadoAgendamiento.CANCELADA);
        return agendamientoRepository.save(agendamientoSeleccionado);
   }

   public List<Agendamiento> agendamientosbyVecinoId(Long usuarioId, String tipo) {
       TipoUsuario usuarioTipo = TipoUsuario.valueOf(tipo);
        return agendamientoRepository.findAllByUsuarioIdAndUsuarioTipo(usuarioId, usuarioTipo);
   }

    public List<Agendamiento> agendamientosbyTramiteId(Long tramiteId) {
        return agendamientoRepository.findAllByTramiteId(tramiteId);
    }

    public List<Agendamiento> agendamientosbyFechaAndTramiteId(LocalDate fecha, Long tramiteId) {
        return agendamientoRepository.findAllByFechaAndTramiteId(fecha, tramiteId);
    }

}