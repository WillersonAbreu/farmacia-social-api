package br.com.farmaciasocialapi.models;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
public class UserModel implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -7235180941196495384L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 150)
	@NotBlank(message = "É necessário inserir o nome!")
	private String name;

	@Column(nullable = false, length = 80)
	@Email(message = "É necessário inserir um email válido!")
	@NotBlank(message = "É necessário inserir o email!")
	private String email;

	@Column(nullable = false, length = 15)
	@NotBlank(message = "É necessário inserir o número do celular!")
	private String phone;
	
	
	@ManyToOne
	@JoinColumn(name = "role_id", referencedColumnName = "id", insertable = false, updatable = false)
	private RoleModel role;
	
	
	@Column(name = "role_id", nullable = false)
	private Long roleId = (long) 1;
	


	@Column(nullable = false)
	// @NotNull(message = "É necessário inserir uma senha!")
	private String password;
	
	private String tokenConfirmation;

	@Column(nullable = false, length = 14, unique = true)
	@NotBlank(message = "É necessário inserir o número do CPF!")
	private String cpf;

	@Column(nullable = false, length = 9)
	@NotBlank(message = "É necessário inserir o número do CEP!")
	private String cep;

	@Column(nullable = false, length = 100)
	@NotBlank(message = "É necessário inserir o endereço!")
	private String address;

	@CreationTimestamp
	private Timestamp createdAt = new Timestamp(System.currentTimeMillis());

	@CreationTimestamp
	private Timestamp updatedAt = new Timestamp(System.currentTimeMillis());

	@OneToMany(mappedBy = "user")
	private List<MedicineDonationModel> donations;

	@OneToMany(mappedBy = "userId")
	@JsonIgnore
	private List<ReservedDonationModel> reservedDonations;

	private List<ReservedDonationModel> getReservedDonations() {
		return this.reservedDonations;
	}

	private void setReservedDonations(List<ReservedDonationModel> reservedDonations) {
		this.reservedDonations = reservedDonations;
	}

	private List<MedicineDonationModel> getDonations() {
		return this.donations;
	}

	private void setDonations(List<MedicineDonationModel> donations) {
		this.donations = donations;
	}

	@JsonIgnore
	public String getPassword() {
		return password;
	}

	@JsonProperty
	public void setPassword(String password) {
		this.password = password;
	}
}
