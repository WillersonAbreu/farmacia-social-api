package br.com.farmaciasocialapi.models;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import br.com.farmaciasocialapi.resources.BaseEntity;

@Entity
public class PasswordResetToken extends BaseEntity {
	
	@Column(nullable = false, unique = true)
	private String token;
	
	@OneToOne(targetEntity = UserModel.class, fetch = FetchType.EAGER)
	@JoinColumn(nullable = false, name = "user_id")
	private UserModel usuario;
	
	@Column(nullable = false, name = "data_expiracao")
	private LocalDateTime dataExpiracao;
	
	public void setDataExpiracao(int minutes) {
		this.dataExpiracao = LocalDateTime.now().plusMinutes(minutes);
	}
	
	public boolean expirou() {
		return LocalDateTime.now().isAfter(this.dataExpiracao);
	}
	
	public String getToken() {
		return token;
	}
	
	public void setToken(String token) {
		this.token = token;
	}
	
	public UserModel getUsuario() {
		return usuario;
	}
	
	public void setUsuario(UserModel usuario) {
		this.usuario = usuario;
	}
	
	public LocalDateTime getDataExpiracao() {
		return dataExpiracao;
	}
	
	public void setDataExpiracao(LocalDateTime dataExpiracao) {
		this.dataExpiracao = dataExpiracao;
	}
}