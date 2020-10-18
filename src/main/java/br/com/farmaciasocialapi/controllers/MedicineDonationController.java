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

import br.com.farmaciasocialapi.models.MedicineDonationModel;
import br.com.farmaciasocialapi.service.MedicineDonationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "Doações", description = "Controller responsável pelos endpoints das doações")
@RequestMapping(value = "/api/donations")
public class MedicineDonationController {

	@Autowired
	MedicineDonationService medicineDonationService;

	// Trazer todos os anuncios
	@GetMapping
	@ApiOperation(value = "Listar doações", notes = "Método responsavel por listar todas as doações")
	public ResponseEntity<List<MedicineDonationModel>> getAllDonations() {
		List<MedicineDonationModel> donation = medicineDonationService.getAll();
		return ResponseEntity.ok(donation);
	}

	// Trazer um anuncio por id
	@GetMapping("/{id}")
	@ApiOperation(value = "Listar uma doação", notes = "Método responsavel por listar uma doação através do ID")
	public ResponseEntity<MedicineDonationModel> getOne(@PathVariable(value = "id") Long id) {
		MedicineDonationModel donation = medicineDonationService.getOne(id);
		return ResponseEntity.ok(donation);
	}

	// Trazer todos os anuncios de tal usuario
	@GetMapping("/{userId}/meusAnuncios")
	@ApiOperation(value = "Listar doações do usuário X", notes = "Método responsavel por listar todas as doações de um usuário específico")
	public ResponseEntity<List<MedicineDonationModel>> getAllDonations(@PathVariable(value = "userId") Long userId) {
		List<MedicineDonationModel> donation = medicineDonationService.getAll(userId);
		return ResponseEntity.ok(donation);
	}

	// Cadastrar um anuncio
	@PostMapping
	@Transactional
	@ApiOperation(value = "Cadastrar Anúncio", notes = "Método responsavel por cadastrar um novo anúncio")
	public ResponseEntity<MedicineDonationModel> store(@Valid @RequestBody MedicineDonationModel medicineDonation) {
		System.out.println(medicineDonation);
		MedicineDonationModel entity = medicineDonationService.save(medicineDonation);
		return ResponseEntity.ok(entity);
	}

	// Alterar um anuncio
	@PutMapping("/{id}")
	@Transactional
	@ApiOperation(value = "Alterar Anúncio", notes = "Método responsavel por alterar um anúncio específico")
	public ResponseEntity<MedicineDonationModel> update(@PathVariable(value = "id") Long id,
			@Valid @RequestBody MedicineDonationModel medicineDonation) {
		MedicineDonationModel entity = medicineDonationService.update(id, medicineDonation);
		return ResponseEntity.ok(entity);
	}

	// Deletar um anúncio
	@DeleteMapping("/{id}")
	@Transactional
	@ApiOperation(value = "Deletar Anúncio", notes = "Método responsavel por apagar um anúncio específico")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
		medicineDonationService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
