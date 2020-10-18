package br.com.farmaciasocialapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import br.com.farmaciasocialapi.models.PharmacyModel;
import br.com.farmaciasocialapi.repository.PharmacyRepository;

@Service
public class PharmacyService {
	
	@Autowired
	private PharmacyRepository repository;
	
	//---------listar todas as farmacias---//
	
	public List<PharmacyModel> getAll() {
		return repository.findAll();
	}
	//-------------------------------------//
	
	//---------Cria uma nova Farmacia------//
	
	public PharmacyModel store(PharmacyModel entity) {
		return repository.save(entity);
	}
	
	//-------------------------------------//
	
	//---------BUSCAR UMA FARMACIA PELO ID---//
	public PharmacyModel getOne(Long id) {
		Optional<PharmacyModel> optional = repository.findById(id);
		if (optional.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Farmacia não encontrada!");
		}
		PharmacyModel entity = optional.get();
		return entity;
	}
	//-------------------------------------//
	
	//-------ATUALIZA UMA FARMACIA JA EXISTENTE-----//
	public PharmacyModel update(Long id, PharmacyModel entity) {
		this.getOne(id);
		entity.setId(id);
		return this.store(entity);
	}
	//-------------------------------------//
	
	//-------Excluir uma farmacia-----//
	public void destroy(Long id) {
		PharmacyModel entity = this.getOne(id);
		repository.delete(entity);
	}
	//-------------------------------------//



}
