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
@Table(name = "post_images")
public class PostImage implements Serializable {
	/**
	 * 
	 */
	private static final Long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "donationId")
	private MedicineDonation donation;

	@Column(nullable = false, length = 60)
	private String imageName;

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

	public MedicineDonation getDonation() {
		return donation;
	}

	public void setDonation(MedicineDonation donation) {
		this.donation = donation;
	}

	public String getImage_name() {
		return imageName;
	}

	public void setImage_name(String imageName) {
		this.imageName = imageName;
	}

	public Boolean getIs_active() {
		return isActive;
	}

	public void setIs_active(Boolean isActive) {
		this.isActive = isActive;
	}

	public Timestamp getCreated_at() {
		return createdAt;
	}

	public void setCreated_at(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getUpdated_at() {
		return updatedAt;
	}

	public void setUpdated_at(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

}
