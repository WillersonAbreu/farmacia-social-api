package br.com.farmaciasocialapi.repository;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import br.com.farmaciasocialapi.models.MedicineDonationModel;
import br.com.farmaciasocialapi.resources.BaseRepository;

public interface MedicineDonationRepository extends BaseRepository<MedicineDonationModel> {

<<<<<<< HEAD
	/*
	 * @Query("SELECT d FROM MedicineDonationModel d WHERE d.isActive = 1 AND d.statusId = 1 ORDER BY d.createdAt DESC"
	 * ) //List<MedicineDonationModel> customFindAll(Example<S> example, Pageable
	 * pageable); <S extends MedicineDonationModel> Page<S> customFindAll(Example<S>
	 * example, Pageable pageable);
	 */
=======
	@Query("SELECT d FROM MedicineDonationModel d WHERE d.isActive = 1 AND d.statusId = 1 ORDER BY d.createdAt DESC")
	List<MedicineDonationModel> findAll();
>>>>>>> 15c3a65cffbfef078391abb55a99f69e5ccc12cd

	List<MedicineDonationModel> findAllByUserId(Long UserId);

}
