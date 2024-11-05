package generation.rencapp.repositories;

import generation.rencapp.models.Documento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentoRepository extends JpaRepository<Documento, Long> {
    // Declara metodo para buscar Documentos Por Id De usuario
    List<Documento> findByUsuarioId(Long usuarioId);

    //Declara metodo para buscar documentos por id del agendamiento
    List<Documento> findByAgendamientoId(Long agendamientoId);






}
