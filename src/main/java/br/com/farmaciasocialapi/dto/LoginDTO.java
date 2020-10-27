package br.com.farmaciasocialapi.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class LoginDTO {
    @Email(message = "Email incorreto!")
    @NotNull(message = "O email é obrigatório")
    private String email;

    @NotNull(message = "A senha é obrigatória!")
    private String password;
}
