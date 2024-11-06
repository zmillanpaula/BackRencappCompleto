package generation.rencapp.storage;

import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.storage.*;
import generation.rencapp.models.Agendamiento;
import generation.rencapp.models.Documento;
import generation.rencapp.models.Usuario;
import generation.rencapp.repositories.AgendamientoRepository;
import generation.rencapp.repositories.DocumentoRepository;
import generation.rencapp.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@Service
public class GoogleCloudStorageService {

    private String bucketName = "";
    private final String SERVICE_ACCOUNT_JSON_PATH = "";
    private final Storage storage;



    @Autowired
    private DocumentoRepository documentoRepository;

    @Autowired
    private AgendamientoRepository agendamientoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    {
        try {

            storage = StorageOptions.newBuilder()
                    .setCredentials(ServiceAccountCredentials.fromStream(new FileInputStream(SERVICE_ACCOUNT_JSON_PATH)))
                    .build().getService();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Método para subir un archivo asociado a un usuario
    public String uploadFileForUsuario(Long usuarioId, MultipartFile file) throws IOException {

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        String filePath = "usuarios/" + usuarioId + "/documentos/" + file.getOriginalFilename();

        BlobId blobId = BlobId.of(bucketName, filePath);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(file.getContentType()).build();
        storage.create(blobInfo, file.getBytes());

        // Guardar información del archivo en la base de datos
        Documento documento = new Documento();
        documento.setNombreArchivo(file.getOriginalFilename());
        documento.setUrlArchivo(filePath);
        documento.setUsuario(usuario);
        documentoRepository.save(documento);


        return String.format("File %s uploaded to bucket %s as %s", file.getOriginalFilename(), bucketName, blobId.getName());
    }

    // Método para subir un archivo asociado a una agendamiento
    public String uploadFileForAgendamiento(Long agendamientoId, MultipartFile file) throws IOException {
        Agendamiento agendamiento = agendamientoRepository.findById(agendamientoId)
                .orElseThrow(() -> new RuntimeException("agendamiento no encontrada"));

        String filePath = "agendamientos/" + agendamientoId + "/documentos/" + file.getOriginalFilename();
        BlobId blobId = BlobId.of(bucketName, filePath);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(file.getContentType()).build();
        storage.create(blobInfo, file.getBytes());

        // Guardar información del archivo en la base de datos
        Documento documento = new Documento();
        documento.setNombreArchivo(file.getOriginalFilename());
        documento.setUrlArchivo(filePath);
        documento.setAgendamiento(agendamiento);
        documentoRepository.save(documento);

        System.out.println(blobId);

        return String.format("File %s uploaded to bucket %s as %s", file.getOriginalFilename(), bucketName, blobId.getName());
    }

    // Método para descargar un archivo
    public byte[] downloadFile(String fileName) {
        Blob blob = storage.get(BlobId.of(bucketName, fileName));
        System.out.println("Aqui llega el cod" + blob.exists());
        return blob.getContent();
    }

    // Método para listar archivos de un paciente
    public List<Documento> listFilesForUsuario(Long usuarioId) {
        return documentoRepository.findByUsuarioId(usuarioId);
    }

    // Método para listar archivos de una agendamiento
    public List<Documento> listFilesForAgendamiento(Long agendamientoId) {
        return documentoRepository.findByAgendamientoId(agendamientoId);
    }

    // Método para eliminar un archivo
    public String deleteFile(String fileName) {
        BlobId blobId = BlobId.of(bucketName, fileName);
        boolean deleted = storage.delete(blobId);
        return deleted ? "File deleted successfully" : "File not found";
    }
}