package com.digitalhouse.users_service.model.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequest {

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 3, max =20, message = "El nombre debe tener entre 3 y 20 caracteres")
    private String firstname;

    @NotBlank(message = "El apellido es obligatorio")
    @Size(min = 3, max =20, message = "El apellido debe tener entre 3 y 20 caracteres")
    private String lastname;

    @NotBlank(message = "El dni es obligatorio")
    @Size(min = 8, max =8, message = "El dni debe tener 8 caracteres")
    private String dni;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "Debe ser un email válido")
    private String email;

    @NotBlank(message = "La contraseña no puede estar vacía")
    @Size(min = 8, max = 20, message = "La contraseña debe tener entre 8 y 20 caracteres")
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=]).*$",
            message = "La contraseña debe contener al menos una mayúscula, una minúscula, un número y un carácter especial"
    )
    private String password;

    @Size(min = 10, max = 20, message = "El telefono debe tener entre 10 y 20 caracteres")
    private String phone;
}
