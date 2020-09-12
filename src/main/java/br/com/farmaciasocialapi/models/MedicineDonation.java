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

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "medicine_donations")
public class MedicineDonation implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@Column(nullable = false, length = 100)
	private String title;

	@Column(nullable = false, length = 140)
	private String description;

	@Column(nullable = false, length = 10)
	private String stripe;

	@Column(nullable = false, length = 8)
	private String packing;

	@Column(nullable = false)
	private Integer amount;

	@Column(nullable = false, length = 15)
	private String dosage;

	@Column(nullable = false, length = 60)
	private String composition;

	@Column(nullable = false, length = 20)
	private String batch_code;

	@Column(nullable = false)
	private Timestamp shelf_life;

	@Column(nullable = false)
	private String picture_file;

	@Column(nullable = false)
	private Boolean is_active = true;

	@CreationTimestamp
	private Timestamp created_at;

	@CreationTimestamp
	private Timestamp updated_at;
		
	@OneToMany(mappedBy = "donation")
	private List<PostImage> images;
	
	public List<PostImage> getImages() {
		return images;
	}

	public void setImages(List<PostImage> images) {
		this.images = images;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStripe() {
		return stripe;
	}

	public void setStripe(String stripe) {
		this.stripe = stripe;
	}

	public String getPacking() {
		return packing;
	}

	public void setPacking(String packing) {
		this.packing = packing;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getDosage() {
		return dosage;
	}

	public void setDosage(String dosage) {
		this.dosage = dosage;
	}

	public String getComposition() {
		return composition;
	}

	public void setComposition(String composition) {
		this.composition = composition;
	}

	public String getBatch_code() {
		return batch_code;
	}

	public void setBatch_code(String batch_code) {
		this.batch_code = batch_code;
	}

	public Timestamp getShelf_life() {
		return shelf_life;
	}

	public void setShelf_life(Timestamp shelf_life) {
		this.shelf_life = shelf_life;
	}

	public String getPicture_file() {
		return picture_file;
	}

	public void setPicture_file(String picture_file) {
		this.picture_file = picture_file;
	}

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
