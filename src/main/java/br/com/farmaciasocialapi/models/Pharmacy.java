package br.com.farmaciasocialapi.models;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "pharmacies")
@Getter
@Setter
public class Pharmacy implements Serializable {
	/**
	 * 
	 */
	private static final Long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

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
	private String password;

	@CreationTimestamp
	private Timestamp createdAt;

	@CreationTimestamp
	private Timestamp updatedAt;

}
