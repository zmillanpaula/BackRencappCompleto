package generation.rencapp.services;

import generation.rencapp.models.Servicio;
import generation.rencapp.repositories.ServicioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioServiceImpl implements ServicioService {

    @Autowired
    private ServicioRepository servicioRepository;

    // BUSCAR TODOS
    @Override
    public List<Servicio> findAll(){
        return servicioRepository.findAll();
    }

    // Buscar por ID
    @Override
    public Servicio findById(Long id) {
        return servicioRepository.findById(id).get();
    }

    // Buscar por ID de dep

    public List<Servicio> findByDepartamentoId(Long departamentoId) {
        return servicioRepository.findByDepartamentoId(departamentoId);
    }


    @Transactional
    public Servicio save(Servicio servicio) {
        return servicioRepository.save(servicio);
    }

    @Override
    public void deleteById(Long id){
        servicioRepository.deleteById(id);
    }
}
