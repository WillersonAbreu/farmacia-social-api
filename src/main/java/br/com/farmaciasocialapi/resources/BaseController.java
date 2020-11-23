package br.com.farmaciasocialapi.resources;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javassist.NotFoundException;

public abstract class BaseController<E extends BaseEntity, R extends BaseRepository<E>, S extends BaseService<E, R>> {

	@Autowired // serviço
	protected S service;

	/*
	 * @GetMapping("/pageable") // Listar todas entidades com paginação public
	 * ResponseEntity<Page<E>> index(E filter, @PageableDefault Pageable pageable) {
	 * Page<E> entities = service.getAllPageable(filter, pageable); return
	 * ResponseEntity.ok(entities); }
	 */

	/*
	 * @GetMapping // Listar todas entidades public ResponseEntity<List<E>> index()
	 * { List<E> entities = service.getAll(); return ResponseEntity.ok(entities); }
	 * 
	 * @PostMapping // Cadastrar uma entidade
	 * 
	 * @Transactional public ResponseEntity<E> store(@Valid @RequestBody E entity) {
	 * E newEntity = service.store(entity); return
	 * ResponseEntity.status(201).body(newEntity); }
	 * 
	 * @GetMapping("/{id}") // Detalhar uma entidade public ResponseEntity<E>
	 * show(@PathVariable(value = "id") Integer id) throws NotFoundException { E
	 * entity = service.getOne(id); return ResponseEntity.ok(entity); }
	 * 
	 * @PutMapping("/{id}") // Atualizar uma entidade
	 * 
	 * @Transactional public ResponseEntity<E> update(@Valid @PathVariable(value =
	 * "id") Integer id, @RequestBody E entity) { E updatedEntity =
	 * service.update(id, entity); return ResponseEntity.ok(updatedEntity); }
	 * 
	 * @DeleteMapping("/{id}") // Deletar uma entidade
	 * 
	 * @Transactional public ResponseEntity<?> destroy(@PathVariable(value = "id")
	 * Integer id) throws NotFoundException { service.destroy(id); return
	 * ResponseEntity.noContent().build(); }
	 */

}
