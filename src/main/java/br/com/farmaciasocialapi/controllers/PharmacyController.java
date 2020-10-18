package br.com.farmaciasocialapi.controllers;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import br.com.farmaciasocialapi.models.PharmacyModel;
import br.com.farmaciasocialapi.service.PharmacyService;
import javassist.NotFoundException;

@RestController
@RequestMapping("/api/pharmacies")
public class PharmacyController {
	
	@Autowired
	private PharmacyService service;
	
	@GetMapping // Listar todas entidades
	public ResponseEntity<List<PharmacyModel>> index() {
		List<PharmacyModel> entities = service.getAll();
		return ResponseEntity.ok(entities);
	}
	
	//-------------------------------------------------------//
	
	@PostMapping // Cadastrar uma entidade
	@Transactional
	public ResponseEntity<PharmacyModel> store(@Valid @RequestBody PharmacyModel entity) {
		PharmacyModel newEntity = service.store(entity);
		return ResponseEntity.status(201).body(newEntity);
	}
	
	
	//------------------------------------------------------//

	@GetMapping("/{id}") // Detalhar uma entidade
	public ResponseEntity<PharmacyModel> show(@PathVariable(value = "id") Long id) throws NotFoundException {
		PharmacyModel entity = service.getOne(id);
		return ResponseEntity.ok(entity);
	}
	
	//------------------------------------------------------//
	
	@PutMapping("/{id}") // Atualizar uma entidade
	@Transactional
	public ResponseEntity<PharmacyModel> update(@Valid @PathVariable(value = "id") Long id, @RequestBody PharmacyModel entity) {
		PharmacyModel updatedEntity = service.update(id, entity);
		return ResponseEntity.ok(updatedEntity);
	}
	
	//------------------------------------------------------//

	@DeleteMapping("/{id}") // Deletar uma entidade
	@Transactional
	public ResponseEntity<?> destroy(@PathVariable(value = "id") Long id) throws NotFoundException {
		service.destroy(id);
		return ResponseEntity.noContent().build();
	}
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

