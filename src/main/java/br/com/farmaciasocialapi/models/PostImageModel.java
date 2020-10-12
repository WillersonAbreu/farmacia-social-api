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
public class PostImageModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "donationId")
	private MedicineDonationModel donation;

	@Column(nullable = false, length = 60)
	private String imageName;

	@CreationTimestamp
	private Timestamp createdAt;

	@CreationTimestamp
	private Timestamp updatedAt;

}
