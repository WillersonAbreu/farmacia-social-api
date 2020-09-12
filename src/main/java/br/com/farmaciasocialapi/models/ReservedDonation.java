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
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne
	@JoinColumn(name = "medicine_donation_id")
	private MedicineDonation medicine_donation;

	@ManyToOne
	@JoinColumn(name = "benefited_user_id")
	private User benefited_user;

	@Column(nullable = false)
	private Boolean is_active = true;

	@CreationTimestamp
	private Timestamp created_at;
	
	@CreationTimestamp
	private Timestamp updated_at;

	public User getBenefited_user() {
		return benefited_user;
	}

	public void setBenefited_user(User benefited_user) {
		this.benefited_user = benefited_user;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public MedicineDonation getMedicine_donation() {
		return medicine_donation;
	}

	public void setMedicine_donation(MedicineDonation medicine_donation) {
		this.medicine_donation = medicine_donation;
	}

//	public User getMedicine_donation_user() {
//		return medicine_donation_user;
//	}
//
//	public void setMedicine_donation_user(User medicine_donation_user) {
//		this.medicine_donation_user = medicine_donation_user;
//	}

	public Boolean getIs_active() {
		return is_active;
	}

	public void setIs_active(Boolean is_active) {
		this.is_active = is_active;
	}

	public Timestamp getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}

	public Timestamp getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Timestamp updated_at) {
		this.updated_at = updated_at;
	}

}
