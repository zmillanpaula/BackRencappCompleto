package generation.rencapp.security;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;


import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

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
    @Size(max = 50)
    @Email
    private String confirmarEmail;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    @NotBlank
    @Size(min = 6, max = 40)
    private String confirmarPassword;


    private String departamento;
}


