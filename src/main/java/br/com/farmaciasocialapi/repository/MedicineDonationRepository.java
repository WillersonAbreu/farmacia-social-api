package br.com.farmaciasocialapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import br.com.farmaciasocialapi.models.MedicineDonationModel;
import br.com.farmaciasocialapi.resources.BaseRepository;

public interface MedicineDonationRepository extends BaseRepository<MedicineDonationModel> {

	@Query("SELECT d FROM MedicineDonationModel d WHERE d.isActive = '1' AND d.statusId = '1' ORDER BY d.createdAt DESC")
	List<MedicineDonationModel> findAll();

	List<MedicineDonationModel> findAllByUserId(Long UserId);

}
