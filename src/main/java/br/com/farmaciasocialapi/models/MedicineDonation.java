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
	private static final Long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "userId")
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
	private String batchCode;

	@Column(nullable = false)
	private Timestamp shelfLife;

	@Column(nullable = false)
	private String pictureFile;

	@CreationTimestamp
	private Timestamp createdAt;

	@CreationTimestamp
	private Timestamp updatedAt;

	@OneToMany(mappedBy = "donation")
	private List<PostImage> images;

}
