package generation.rencapp.security;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class LoginDTO {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    public Object getEmail() {
        return username;
    }
}

