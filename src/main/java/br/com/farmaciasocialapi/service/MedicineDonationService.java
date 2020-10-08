package br.com.farmaciasocialapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.farmaciasocialapi.models.MedicineDonation;
import br.com.farmaciasocialapi.models.User;
import br.com.farmaciasocialapi.repository.MedicineDonationRepository;

@Service
public class MedicineDonationService {
	
	//esse cara fica com a regra de negocio toda a logica, o controller so entrega
	
	@Autowired
	private MedicineDonationRepository medicineDonationRepository;
	
	//Buscar todos as doações
	public List<MedicineDonation> getAll(){
		List<MedicineDonation> donations = medicineDonationRepository.findAll();
		return donations;
	}
	
	//Cadastrar novo anuncio
	public MedicineDonation save(MedicineDonation medicineDonation) {
		return medicineDonationRepository.save(medicineDonation);
	}
	
	// procurar um anuncio pelo id do anuncio
	public MedicineDonation getOne(Long id) {
		Optional<MedicineDonation> entity = medicineDonationRepository.findById(id);
		
		if(!entity.isPresent()) {
			throw new ResponseStatusException( HttpStatus.NOT_FOUND, "Doação não encontrada!");
		}
		MedicineDonation donation = entity.get();
		return donation;
		
	}
	
	// trazer todos os anuncios de um usuário
	public List<MedicineDonation> getAll(Long userId) {
		return medicineDonationRepository.findAllByUserId(userId);
	}
	
	//modificar um anuncio
	public MedicineDonation update(Long id, MedicineDonation medicineDonation) {
		this.getOne(id);
		medicineDonation.setId(id);
		MedicineDonation donationUpdated = this.save(medicineDonation);
		return donationUpdated;
	}
	
	//Deletar um anúncio
	public void delete(Long id) {
		MedicineDonation donation = this.getOne(id);
		medicineDonationRepository.delete(donation);
	}
	
	

}
