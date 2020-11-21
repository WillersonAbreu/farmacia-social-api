package br.com.farmaciasocialapi.models;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "pharmacies")
@Getter
@Setter
public class PharmacyModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "role_id", referencedColumnName = "id", insertable = false, updatable = false)
	private RoleModel role;

	@Column(name = "role_id", nullable = false)
	private Long roleId = (long) 2;

	@Column(nullable = false)
	private String fantasyName;

	@Column(nullable = false)
	private String address;

	@Column(nullable = false)
	private String cep;

	@Column(nullable = false)
	private String region;

	@Column(nullable = false)
	private String cnpj;

	@Column(nullable = false)
	private String pharmaceutical;

	@Column(nullable = false)
	private String email;

	@Column(nullable = false, length = 15)
	private String phone;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private String latitude;

	@Column(nullable = false)
	private String longitude;

	@CreationTimestamp
	private Timestamp createdAt;

	@CreationTimestamp
	private Timestamp updatedAt;

	@JsonIgnore
	public String getPassword() {
		return password;
	}

	@JsonProperty
	public void setPassword(String password) {
		this.password = password;
	}
}
