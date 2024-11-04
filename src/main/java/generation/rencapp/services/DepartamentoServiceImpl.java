package generation.rencapp.services;

import generation.rencapp.models.Departamento;
import generation.rencapp.repositories.DepartamentoRepository;
import generation.rencapp.repositories.ServicioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartamentoServiceImpl implements DepartamentoService {

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Autowired
    private ServicioRepository servicioRepository;

    // Buscar Todos
    @Override
    public List<Departamento> findAll() {
        return departamentoRepository.findAll();
    }

    // Buscar Por ID
    @Override
    public Departamento findById(Long id) {
        return departamentoRepository.findById(id).get();
    }

    // Guardar departamento
    @Transactional
    public Departamento save(Departamento departamento) {
        return departamentoRepository.save(departamento);
    }

    @Override
    public void deleteById(Long id){
        if(servicioRepository.existsByDepartamentoId(id)) {
            throw new RuntimeException("No se puede eliminar el departamento porque tiene un servicio asociado.");
        }
        departamentoRepository.deleteById(id);
    }
}
