package br.com.farmaciasocialapi.dto;

import javax.validation.constraints.Email;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PasswordForgotDTO {
	
	@Email
	private String email;
	
	

}
