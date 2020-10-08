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

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "medicine_donations")
@Getter
@Setter
public class MedicineDonationModel implements Serializable {
	/**
	 * 
	 */
	private static final Long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private Integer amount;

	@Column(nullable = false, length = 20)
	private String batchCode;

	@CreationTimestamp
	private Timestamp createdAt;

	@Column(nullable = false, length = 140)
	private String description;

	@Column(nullable = false, length = 15)
	private String dosage;

	@Column(nullable = false, length = 8)
	private String packing;

	@Column(nullable = false)
	private String pictureFile;

	@Column(nullable = false)
	private String pictureFileBack;

	@Column(nullable = false)
	private Timestamp shelfLife;

	@Column(nullable = false, length = 10)
	private String stripe;

	@Column(nullable = false, length = 100)
	private String title;

	@CreationTimestamp
	@JsonIgnore
	private Timestamp updatedAt;

	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
	private UserModel user;

	@Column(name = "user_id", nullable = false)
	private Long userId;

	@Column(nullable = false)
	private Timestamp manufacturyDate;

	@OneToMany(mappedBy = "donation")
	@JsonIgnore
	private List<PostImage> images;

	@Override
	public String toString() {
		return "MedicineDonation [id=" + id + ", amount=" + amount + ", batchCode=" + batchCode + ", createdAt="
				+ createdAt + ", description=" + description + ", dosage=" + dosage + ", packing=" + packing
				+ ", pictureFile=" + pictureFile + ", pictureFileBack=" + pictureFileBack + ", shelfLife=" + shelfLife
				+ ", stripe=" + stripe + ", title=" + title + ", updatedAt=" + updatedAt + ", user=" + user
				+ ", manufacturyDate=" + manufacturyDate + ", images=" + images + "]";
	}

}
