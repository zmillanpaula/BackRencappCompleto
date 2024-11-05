package generation.rencapp.services;

import generation.rencapp.models.Horario;
import generation.rencapp.models.Intervalos;
import generation.rencapp.repositories.HorarioRepository;
import generation.rencapp.repositories.IntervaloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class HorarioServiceImpl implements HorarioService {

    @Autowired
    private HorarioRepository horarioRepository;

    @Autowired
    private IntervaloRepository intervaloRepository;

    //es conveniente inyectar la java class o la interfaz?
    @Autowired
    private FuncionarioServiceImpl funcionarioServiceImpl;

    //buscar horario por id

    public Horario findById(Long id) {
        return horarioRepository.findById(id).get();
    }

    //buscar todos los horarios

    public List<Horario> findAll() {
        return horarioRepository.findAll();
    }

    //guardar un horario

    public Horario saveHorario(Horario horarioNuevo) {
        return horarioRepository.save(horarioNuevo);
    }

    //eliminar un horario

    public void deleteHorarioById(Long id) {
        horarioRepository.deleteById(id);
    }

    //encontrar un horario a partir del atributo estado

    /*public List<Horario> findHorarioByEstado(String estado) {
        return horarioRepository.findByEstado(estado);
    } */



    public List<Intervalos> obtenerIntervalosDeUnaHora(Long tramiteId, Long horarioId, LocalDate fecha) {
        //horario creado se guarda en una variable

        Horario horario = horarioRepository.findByTramiteIdAndFecha(tramiteId,fecha);

        if(horario == null) {
            return new ArrayList<>();
        }

        List<Intervalos> intervalosUnaHora = generarIntervalos(horario.getId(), horario.getHoraInicio(), horario.getHoraFin());
        horario.setIntervalos(intervalosUnaHora);
        horarioRepository.save(horario);

        return intervalosUnaHora;
    }

    public List<Intervalos> generarIntervalos(Long horarioId, LocalTime horaInicio, LocalTime horaFin) {
        List<Intervalos> intervalos = new ArrayList<>();

        LocalTime horaActual = horaInicio;

        while (horaActual.isBefore(horaFin)) {
            Intervalos intervalo= new Intervalos();
            intervalo.setEstado(Intervalos.Estadointervalo.DISPONIBLE);
            intervalo.setHora(horaActual);
            intervalos.add(intervalo);
            horaActual = horaActual.plusHours(1);
            intervalo.setHorario(horarioRepository.findById(horarioId).get());
            intervaloRepository.save(intervalo);
        }

        return intervalos;
    }

    public boolean validarAgendamientoDentroDelHorario(Long tramiteId, Long horarioId,  LocalDate fecha) {
        List<Intervalos> intervalosDisponibles = obtenerIntervalosDeUnaHora(tramiteId, horarioId, fecha);
        return intervalosDisponibles.contains(fecha);
    }


}
