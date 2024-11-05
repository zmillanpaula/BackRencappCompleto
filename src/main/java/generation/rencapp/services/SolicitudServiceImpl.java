package generation.rencapp.services;
import generation.rencapp.models.Solicitud;
import generation.rencapp.models.Tramite;
import generation.rencapp.repositories.SolicitudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class SolicitudServiceImpl implements SolicitudService {

    @Autowired
    private SolicitudRepository solicitudRepository;


    @Override
    public List<Solicitud> findByEstado( String estado) {
        List <Solicitud.EstadoSolicitud> estados = List.of(Solicitud.EstadoSolicitud.values());
        for (int i = 0; i <= estados.size(); i++ ) {
            if (estados.get(i).toString().equals(estado)) {
                return solicitudRepository.findAllByEstado(estados.get(i));
            }
        }
            return null;
    }


    public Solicitud findById(Long id){
        return solicitudRepository.findById(id).get();
    }


    @Override
    public List<Solicitud> findAll() {
        return solicitudRepository.findAll();
    }


    @Override
    public Solicitud saveSolicitud(Solicitud solicitudNueva) {
        return solicitudRepository.save(solicitudNueva);
    }


    @Override
    public List<Solicitud> findByTramiteId(Tramite tramite) {
        if (tramite != null) {
            return solicitudRepository.findByTramiteId(tramite.getId());
        }
        return new ArrayList<>();
    }


    @Override
    public List<Solicitud> findByFechaCreacion(LocalDate fecha) {
        if (fecha != null) {
            return solicitudRepository.findByCreatedAt(fecha);
        }
        return new ArrayList<>();
    }


    @Override
    public void deleteById(Long id) {
        solicitudRepository.deleteById(id);
    }

    @Override
    public List<Solicitud> findByVecinoId(Long vecinoId) {
        return solicitudRepository.findByVecinoId(vecinoId);
    }




}
