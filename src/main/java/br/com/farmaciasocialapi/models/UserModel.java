package br.com.farmaciasocialapi.models;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	private static final Long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 150)
	private String name;

	@Column(nullable = false, length = 80)
	private String email;

	@Column(nullable = false, length = 15)
	private String phone;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false, length = 14, unique = true)
	private String cpf;

	@Column(nullable = false, length = 9)
	private String cep;

	@Column(nullable = false, length = 100)
	private String address;

	@CreationTimestamp
	private Timestamp createdAt = new Timestamp(System.currentTimeMillis());

	@CreationTimestamp
	private Timestamp updatedAt = new Timestamp(System.currentTimeMillis());

	@OneToMany(mappedBy = "user")
	// @JsonIgnoreProperties("user")
	private List<MedicineDonationModel> donations;

	@OneToMany(mappedBy = "benefitedUser")
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
}
