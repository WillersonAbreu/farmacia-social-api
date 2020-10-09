package br.com.farmaciasocialapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.farmaciasocialapi.models.Order;
import br.com.farmaciasocialapi.repository.OrderRepository;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository repository;

	public List<Order> getAll() {
		return repository.findAll();
	}

	public Order store(Order entity) {
		return repository.save(entity);
	}

	public Order getOne(Long id) {
		Optional<Order> optional = repository.findById(id);
		if (optional.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não foi encontrado!");
		}
		Order entity = optional.get();
		return entity;
	}

	public Order update(Long id, Order entity) {
		this.getOne(id);
		entity.setId(id);
		return this.store(entity);
	}

	public void destroy(Long id) {
		Order entity = this.getOne(id);
		repository.delete(entity);
	}

}
