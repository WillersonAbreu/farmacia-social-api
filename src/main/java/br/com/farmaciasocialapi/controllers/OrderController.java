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

import br.com.farmaciasocialapi.models.Order;
import br.com.farmaciasocialapi.service.OrderService;
import javassist.NotFoundException;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
	@Autowired // serviço
	private OrderService service;

	@GetMapping // Listar todas entidades
	public ResponseEntity<List<Order>> index() {
		List<Order> entities = service.getAll();
		return ResponseEntity.ok(entities);
	}

	@PostMapping // Cadastrar uma entidade
	@Transactional
	public ResponseEntity<Order> store(@Valid @RequestBody Order entity) {
		Order newEntity = service.store(entity);
		return ResponseEntity.status(201).body(newEntity);
	}

	@GetMapping("/{id}") // Detalhar uma entidade
	public ResponseEntity<Order> show(@PathVariable(value = "id") Long id) throws NotFoundException {
		Order entity = service.getOne(id);
		return ResponseEntity.ok(entity);
	}

	@PutMapping("/{id}") // Atualizar uma entidade
	@Transactional
	public ResponseEntity<Order> update(@Valid @PathVariable(value = "id") Long id, @RequestBody Order entity) {
		Order updatedEntity = service.update(id, entity);
		return ResponseEntity.ok(updatedEntity);
	}

	@DeleteMapping("/{id}") // Deletar uma entidade
	@Transactional
	public ResponseEntity<?> destroy(@PathVariable(value = "id") Long id) throws NotFoundException {
		service.destroy(id);
		return ResponseEntity.noContent().build();
	}
}
