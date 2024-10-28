package generation.rencapp.service;

import generation.rencapp.models.Departamento;

import java.util.List;

public interface DepartamentoService {

    // Declaracion de metodos
    Departamento findById(Long id); //TODO: buscar por id
    List<Departamento> findAll(); //TODO: buscar todos
    Departamento save(Departamento departamento); //TODO: guardar
    void deleteById(Long id);

}
