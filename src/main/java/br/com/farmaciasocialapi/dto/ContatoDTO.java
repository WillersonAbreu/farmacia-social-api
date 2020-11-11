package br.com.farmaciasocialapi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContatoDTO {
	private String nome;
	private  String telefone;
	private String email;
	private String assunto;
	private String mensagem;

}
