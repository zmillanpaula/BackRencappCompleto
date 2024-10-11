package generation.rencapp.service;

import generation.rencapp.models.Departamento;
import generation.rencapp.repository.DepartamentoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartamentoServiceImpl implements DepartamentoService {

    @Autowired
    private DepartamentoRepository departamentoRepository;

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
}
