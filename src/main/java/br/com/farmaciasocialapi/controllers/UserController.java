package br.com.farmaciasocialapi.controllers;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.farmaciasocialapi.models.UserModel;
import br.com.farmaciasocialapi.resources.Response;
import br.com.farmaciasocialapi.service.UserService;

@RestController
@RequestMapping(value = "/api")
public class UserController {

	@Autowired
	UserService userService;

	/**
	 * 
	 * @return List<User>
	 */
	@GetMapping("/users")
	public List<UserModel> getAllUsers() {
		return userService.findAll();
	}

	/**
	 * Find a specific user
	 *
	 * @param id
	 * @return {User} user
	 */
	@GetMapping("/users/{id}")
	public Optional<UserModel> findUser(@PathVariable(value = "id") Long id) {
		return userService.findById(id);
	}

	/**
	 * Store a new User in database
	 *
	 */
	@Transactional
	@PostMapping("/users")
	public ResponseEntity<Response> create(@RequestBody @Valid UserModel user) {
		userService.save(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Usuário criado com sucesso", 201, user));

	}

	/**
	 * Update an User in database
	 *
	 * @
	 */
	@Transactional
	@PutMapping("/users/{id}")
	public ResponseEntity<Response> update(@PathVariable(value = "id") Long id,
			@Valid @RequestBody UserModel userEntity) {
		UserModel user = userService.update(id, userEntity);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new Response("Usuário atualizado com sucesso", 200, user));
	}

	/**
	 *
	 * @return {Response} Boolean @
	 */
	@DeleteMapping("/users/{id}")
	@Transactional
	public ResponseEntity<Response> delete(@PathVariable(value = "id") Long id) {
		this.userService.deleteById(id);
		return ResponseEntity.ok(new Response("Usuário deletado com sucesso", 200, true));
	}
}
