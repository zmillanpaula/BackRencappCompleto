package generation.rencapp.service;

import generation.rencapp.models.Servicio;
import generation.rencapp.repository.ServicioRepository;
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

    // Buscar por ID como dijos sebas
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

}
