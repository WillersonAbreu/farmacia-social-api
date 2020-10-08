package br.com.farmaciasocialapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.farmaciasocialapi.models.MedicineDonationModel;
import br.com.farmaciasocialapi.models.UserModel;
import br.com.farmaciasocialapi.repository.MedicineDonationRepository;

@Service
public class MedicineDonationService {

	// esse cara fica com a regra de negocio toda a logica, o controller so entrega

	@Autowired
	private MedicineDonationRepository medicineDonationRepository;

	// Buscar todos as doações
	public List<MedicineDonationModel> getAll() {
		List<MedicineDonationModel> donations = medicineDonationRepository.findAll();
		return donations;
	}

	// Cadastrar novo anuncio
	public MedicineDonationModel save(MedicineDonationModel medicineDonation) {
		return medicineDonationRepository.save(medicineDonation);
	}

	// procurar um anuncio pelo id do anuncio
	public MedicineDonationModel getOne(Long id) {
		Optional<MedicineDonationModel> entity = medicineDonationRepository.findById(id);

		if (!entity.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Doação não encontrada!");
		}
		MedicineDonationModel donation = entity.get();
		return donation;

	}

	// trazer todos os anuncios de um usuário
	public List<MedicineDonationModel> getAll(Long userId) {
		return medicineDonationRepository.findAllByUserId(userId);
	}

	// modificar um anuncio
	public MedicineDonationModel update(Long id, MedicineDonationModel medicineDonation) {
		this.getOne(id);
		medicineDonation.setId(id);
		MedicineDonationModel donationUpdated = this.save(medicineDonation);
		return donationUpdated;
	}

	// Deletar um anúncio
	public void delete(Long id) {
		MedicineDonationModel donation = this.getOne(id);
		medicineDonationRepository.delete(donation);
	}

}
