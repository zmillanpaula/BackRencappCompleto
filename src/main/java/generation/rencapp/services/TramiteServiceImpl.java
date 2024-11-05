package generation.rencapp.services;
import generation.rencapp.models.Tramite;
import generation.rencapp.repositories.TramiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TramiteServiceImpl implements TramiteService {

    @Autowired
    private TramiteRepository tramiteRepository;


    public Tramite findById(Long id){
        return tramiteRepository.findById(id).get();
    }


    @Override
    public List<Tramite> findAll() {
        return tramiteRepository.findAll();
    }


    @Override
    public Tramite saveTramite(Tramite tramiteNuevo) {
        return tramiteRepository.save(tramiteNuevo);
    }


    @Override
    public void deleteById(Long id) {
        tramiteRepository.deleteById(id);
    }


    @Override
    public List<Tramite> findByServicioId(Servicio servicio) {
        if (servicio != null) {
            return tramiteRepository.findByServicioId(servicio.getId());
        }
        return new ArrayList<>();
    }


    @Override
    public List<Tramite> findByFechaCreacion(LocalDate fecha) {
        if (fecha != null) {
            return tramiteRepository.findByCreatedAt(fecha);
        }
        return new ArrayList<>();
    }






}
