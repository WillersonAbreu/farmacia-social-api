package br.com.farmaciasocialapi.repository;

import java.util.List;

import br.com.farmaciasocialapi.models.MedicineDonationModel;
import br.com.farmaciasocialapi.resources.BaseRepository;

public interface MedicineDonationRepository extends BaseRepository<MedicineDonationModel> {

	List<MedicineDonationModel> findAllByUserId(Long UserId);

}
