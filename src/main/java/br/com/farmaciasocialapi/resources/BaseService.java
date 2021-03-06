package br.com.farmaciasocialapi.resources;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseService<E extends BaseEntity, R extends BaseRepository<E>> {

	@Autowired
	protected R repository;

	/*
	 * public Page<E> getAllPageable(E filter, Pageable pageable) { ExampleMatcher
	 * matcher = ExampleMatcher.matching().withIgnoreNullValues().withIgnoreCase()
	 * .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING); Example<E>
	 * example = Example.of(filter, matcher);
	 * 
	 * return repository.findAll(example, pageable); }
	 */

	/*
	 * public List<E> getAll() { return repository.findAll(); }
	 */

	/*
	 * public E store(E entity) { return repository.save(entity); }
	 * 
	 * public E getOne(Integer id) { Optional<E> optional = repository.findById(id);
	 * if (!optional.isPresent()) { throw new
	 * ResponseStatusException(HttpStatus.NOT_FOUND,
	 * "Entidade não foi encontrada!"); } E entity = optional.get(); return entity;
	 * }
	 * 
	 * public E update(Integer id, E entity) { this.getOne(id); entity.setId(id);
	 * return this.store(entity); }
	 * 
	 * public void destroy(Integer id) { E entity = this.getOne(id);
	 * repository.delete(entity); }
	 */

}
