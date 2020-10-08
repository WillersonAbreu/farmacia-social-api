package br.com.farmaciasocialapi.controllers;

import java.util.List;
import java.util.Optional;

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

import br.com.farmaciasocialapi.models.MedicineDonationModel;
import br.com.farmaciasocialapi.models.UserModel;
import br.com.farmaciasocialapi.service.MedicineDonationService;

@RestController
@RequestMapping(value = "/api/donations")
public class MedicineDonationController {

	@Autowired
	MedicineDonationService medicineDonationService;

	// Trazer todos os anuncios
	@GetMapping
	public ResponseEntity<List<MedicineDonationModel>> getAllDonations() {
		List<MedicineDonationModel> donation = medicineDonationService.getAll();
		return ResponseEntity.ok(donation);
	}

	// Trazer um anuncio por id
	@GetMapping("/{id}")
	public ResponseEntity<MedicineDonationModel> getOne(@PathVariable(value = "id") Long id) {
		MedicineDonationModel donation = medicineDonationService.getOne(id);
		return ResponseEntity.ok(donation);
	}

	// Trazer todos os anuncios de tal usuario
	@GetMapping("/meusAnuncios")
	public ResponseEntity<List<MedicineDonationModel>> getAllDonations(@RequestBody UserModel user) {
		List<MedicineDonationModel> donation = medicineDonationService.getAll(user);
		return ResponseEntity.ok(donation);
	}

	// Cadastrar um anuncio
	@PostMapping
	@Transactional
	public ResponseEntity<MedicineDonationModel> store(@Valid @RequestBody MedicineDonationModel medicineDonation) {
		System.out.println(medicineDonation);
		MedicineDonationModel entity = medicineDonationService.save(medicineDonation);
		return ResponseEntity.ok(entity);
	}

	// Alterar um anuncio
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<MedicineDonationModel> update(@PathVariable(value = "id") Long id,
			@Valid @RequestBody MedicineDonationModel medicineDonation) {
		MedicineDonationModel entity = medicineDonationService.update(id, medicineDonation);
		return ResponseEntity.ok(entity);
	}

	// Deletar um anúncio
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
		medicineDonationService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
