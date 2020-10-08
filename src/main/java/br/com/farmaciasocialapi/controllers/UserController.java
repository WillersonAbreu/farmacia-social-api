package br.com.farmaciasocialapi.controllers;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

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
import br.com.farmaciasocialapi.repository.UserRepository;
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
	 * @throws Exception
	 */
	@Transactional
	@PostMapping("/users")
	public ResponseEntity<Response> create(@RequestBody UserModel user) throws Exception {
		String encodedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
		user.setPassword(encodedPassword);
		userService.save(user);

		return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Usuário criado com sucesso", 201, user));

	}

	// /**
	// * Update an User in database
	// *
	// * @throws Exception
	// */
	// @Transactional
	// @PutMapping("/user/{id}")
	// public ResponseEntity<Response> update(@PathVariable(value = "id") Long id,
	// @RequestBody User user)
	// throws Exception {

	// /**
	// * Hashing the user password
	// */
	// String encodedPassword = new
	// BCryptPasswordEncoder().encode(user.getPassword());

	// hasUser.get().setName(user.getName());
	// hasUser.get().setPhone(user.getPhone());
	// hasUser.get().setPassword(encodedPassword);
	// hasUser.get().setCep(user.getCep());
	// hasUser.get().setAddress(user.getAddress());
	// hasUser.get().setUpdatedAt(new Timestamp(System.currentTimeMillis()));

	// userRepository.save(hasUser.get());

	// return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Usuário
	// criado com sucesso", 200, hasUser));

	// }

	// /**
	// *
	// * @return {Response} Boolean
	// * @throws Exception
	// */
	// @DeleteMapping("/user/{id}")
	// @Transactional
	// public ResponseEntity<Response> delete(@PathVariable(value = "id") Long id)
	// throws Exception {

	// /**
	// * Validate if user inserted a valid ID
	// */
	// if (id == null) {
	// throw new Exception("É necessário inserir um ID!");
	// }

	// Optional<User> hasUser = this.userRepository.findById(id);

	// if (!hasUser.isPresent()) {
	// throw new Exception("Usuário não encontrado!");
	// }

	// this.userRepository.deleteById(hasUser.get().getId());

	// return ResponseEntity.ok(new Response("Usuário deletado com sucesso", 200,
	// true));
	// }
}
