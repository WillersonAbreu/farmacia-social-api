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

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "reserved_donations")
public class ReservedDonation implements Serializable {
	/**
	 * 
	 */
	private static final Long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "medicineDonationId")
	private MedicineDonation medicineDonation;

	@ManyToOne
	@JoinColumn(name = "benefitedUserId")
	private User benefitedUser;

	@Column(nullable = false)
	private Boolean isActive = true;

	@CreationTimestamp
	private Timestamp createdAt;

	@CreationTimestamp
	private Timestamp updatedAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public MedicineDonation getMedicineDonation() {
		return medicineDonation;
	}

	public void setMedicineDonation(MedicineDonation medicineDonation) {
		this.medicineDonation = medicineDonation;
	}

	public User getBenefitedUser() {
		return benefitedUser;
	}

	public void setBenefitedUser(User benefitedUser) {
		this.benefitedUser = benefitedUser;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public static Long getSerialversionuid() {
		return serialVersionUID;
	}

}
