package br.com.farmaciasocialapi.models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table (name="orders")
@Getter
@Setter
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column (name="medicine_donation_id",nullable=false)
	private Long medicineDonationId;
	
	@Column (name="user_id",nullable=false)
	private Long userId;
	
	private LocalDateTime createdAt;

	@ManyToOne
	@JoinColumn(name = "medicine_donation_id",updatable = false,insertable=false)
	private MedicineDonation medicineDonation; 
	
	@ManyToOne
	@JoinColumn(name = "user_id",updatable = false,insertable=false)
	private User userid;
}
