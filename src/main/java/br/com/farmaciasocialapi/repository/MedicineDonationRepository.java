package br.com.farmaciasocialapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.farmaciasocialapi.models.MedicineDonation;
import br.com.farmaciasocialapi.models.User;

public interface MedicineDonationRepository extends JpaRepository<MedicineDonation, Long>{

	List<MedicineDonation> findAllByUserId(Long UserId);
	
}
