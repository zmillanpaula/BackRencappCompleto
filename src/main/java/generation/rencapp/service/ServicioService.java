package generation.rencapp.service;

import generation.rencapp.models.Servicio;

import java.util.List;

public interface ServicioService {
    // declaracion de metodos
    Servicio findById(Long id); //TODO: buscar por id
    // Servicio findByNombre(String nombre); TODO: PREGUNTAR
    List<Servicio> findAll(); //TODO: buscar todos
    Servicio save(Servicio servicio); // TODO: GUARDAR SERVICIO

}
