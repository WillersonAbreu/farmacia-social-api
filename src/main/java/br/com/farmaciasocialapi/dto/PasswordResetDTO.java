package br.com.farmaciasocialapi.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordResetDTO {
	
	@NotEmpty
	private String token;
	
	@NotEmpty
	private String password;
	

}
