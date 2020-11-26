package br.com.farmaciasocialapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import br.com.farmaciasocialapi.models.MedicineDonationModel;
import br.com.farmaciasocialapi.resources.BaseRepository;

public interface MedicineDonationRepository extends BaseRepository<MedicineDonationModel> {

	List<MedicineDonationModel> findAll();

	List<MedicineDonationModel> findAllByUserId(Long UserId);

	@Query("SELECT medicine FROM MedicineDonationModel medicine WHERE medicine.userId = ?1 ORDER BY medicine.createdAt DESC")
	List<MedicineDonationModel> getAllByUserId(Long id);

	@Query("SELECT medicine FROM MedicineDonationModel medicine WHERE medicine.pharmacyId = ?1 AND medicine.statusId = 4 OR medicine.pharmacyId = ?1 AND medicine.statusId = 5 OR medicine.pharmacyId = ?1 AND medicine.statusId = 6 ORDER BY medicine.createdAt DESC")
	List<MedicineDonationModel> findAllDoneDonationsByPharmacyId(Long id);

	@Query("SELECT medicine FROM MedicineDonationModel medicine WHERE medicine.pharmacyId = ?1 AND medicine.statusId = 1 OR medicine.pharmacyId = ?1 AND medicine.statusId = 2 OR medicine.pharmacyId = ?1 AND medicine.statusId = 3 ORDER BY medicine.createdAt DESC")
	List<MedicineDonationModel> findAllPendentDonationsByPharmacyId(Long id);
}
