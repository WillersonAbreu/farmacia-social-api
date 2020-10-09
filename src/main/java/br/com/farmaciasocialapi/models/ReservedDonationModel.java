package br.com.farmaciasocialapi.models;

import java.io.Serializable;
import java.sql.Timestamp;

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

	@ManyToOne
	@JoinColumn(name = "medicineDonationId")
	private MedicineDonationModel medicineDonation;

	@ManyToOne
	@JoinColumn(name = "benefitedUserId")
	private UserModel benefitedUser;

	@CreationTimestamp
	private Timestamp createdAt;

	@CreationTimestamp
	private Timestamp updatedAt;
}