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

import com.fasterxml.jackson.annotation.JsonFormat;

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

	@Column(name = "medicine_donation_id", nullable = false)
	private Long medicineDonationId;

	@Column(name = "benefited_user_id", nullable = false)
	private Long userId;

	@ManyToOne
	@JoinColumn(name = "medicine_donation_id", referencedColumnName = "id", updatable = false, insertable = false)
	private MedicineDonationModel medicineDonation;

	@ManyToOne
	@JoinColumn(name = "benefited_user_id", referencedColumnName = "id", updatable = false, insertable = false)
	private UserModel user;

	@CreationTimestamp
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Timestamp createdAt;

	@CreationTimestamp
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Timestamp updatedAt;
}
