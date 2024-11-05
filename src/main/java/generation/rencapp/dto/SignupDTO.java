package generation.rencapp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;


import java.time.LocalDate;

@Data
public class SignupDTO {


    private  String nombre;
    private  String apellido;
    private LocalDate fechaNacimiento;
    private  String numeroDeDocumento;

    private int numeroTelefono;

    private String direccion;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;


    private String departamento;
}


