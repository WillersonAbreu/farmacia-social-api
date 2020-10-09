package br.com.farmaciasocialapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.farmaciasocialapi.models.MedicineDonationModel;

public interface MedicineDonationRepository extends JpaRepository<MedicineDonationModel, Long> {

	List<MedicineDonationModel> findAllByUserId(Long UserId);

}
