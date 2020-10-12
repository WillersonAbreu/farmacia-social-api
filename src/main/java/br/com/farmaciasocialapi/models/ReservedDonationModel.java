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

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "reserved_donations")
@Getter
@Setter
public class ReservedDonationModel implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1242787280945689321L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "medicineDonationId", nullable = false)
	private String medicineDonationId;

	@Column(name = "benefitedUserId", nullable = false)
	private String benefitedUserId;

	@ManyToOne
	@JoinColumn(name = "medicineDonationId", insertable = false, updatable = false)
	private MedicineDonationModel medicineDonation;

	@ManyToOne
	@JoinColumn(name = "benefitedUserId", insertable = false, updatable = false)
	private UserModel benefitedUser;

	@CreationTimestamp
	private Timestamp createdAt;

	@CreationTimestamp
	private Timestamp updatedAt;
}
