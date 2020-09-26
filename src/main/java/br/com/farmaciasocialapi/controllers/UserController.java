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

import br.com.farmaciasocialapi.models.User;
import br.com.farmaciasocialapi.repository.UserRepository;
import br.com.farmaciasocialapi.resources.Response;

@RestController
@RequestMapping(value = "/api")
public class UserController {

	@Autowired
	UserRepository userRepository;

	/**
	 * 
	 * @return List<User>
	 */
	@GetMapping("/users")
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	/**
	 * Find a specific user
	 * 
	 * @param id
	 * @return {User} user
	 */
	@GetMapping("/user/{id}")
	public Optional<User> getUser(@PathVariable(value = "id") Long id) {
		return userRepository.findById(id);
	}

	/**
	 * Store a new User in database
	 * 
	 * @throws Exception
	 */
	@Transactional
	@PostMapping("/user")
	public ResponseEntity<Response> create(@RequestBody User user) throws Exception {
		/**
		 * Validate another inputs
		 */
		if (user.getName().length() <= 0 || user.getName() == "0") {
			throw new Exception("É necessário inserir um nome");
		}

		if (user.getEmail().length() <= 0) {
			throw new Exception("É necessário inserir um email");
		}

		if (user.getPhone().length() <= 0) {
			throw new Exception("É necessário inserir um número de celular");
		}

		if (user.getPassword().length() <= 0) {
			throw new Exception("É necessário inserir uma senha");
		}

		if (user.getCpf().length() <= 0) {
			throw new Exception("É necessário inserir um CPF");
		}

		if (user.getCep().length() <= 0) {
			throw new Exception("É necessário inserir um CEP");
		}

		if (user.getAddress().length() <= 0) {
			throw new Exception("É necessário inserir um endereço");
		}

		Optional<User> hasEmail = this.userRepository.findByEmail(user.getEmail());
		Optional<User> hasCpf = this.userRepository.findByCpf(user.getCpf());

		/**
		 * Validate if the email is already used
		 */
		if (!hasEmail.isEmpty()) {
			throw new Exception("Já existe um usuário com esse Email!");
		}

		/**
		 * Validate if the CPF is already used
		 */
		if (!hasCpf.isEmpty()) {
			throw new Exception("Já existe um usuário com esse CPF!");
		}

		String encodedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
		user.setPassword(encodedPassword);
		this.userRepository.save(user);

		return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Usuário criado com sucesso", 201, user));

	}

	/**
	 * Update an User in database
	 * 
	 * @throws Exception
	 */
	@Transactional
	@PutMapping("/user/{id}")
	public ResponseEntity<Response> update(@PathVariable(value = "id") Long id, @RequestBody User user)
			throws Exception {
		/**
		 * Validate if user inserted a valid ID
		 */
		if (id == null) {
			throw new Exception("É necessário inserir um ID!");
		}

		/**
		 * Validate another inputs
		 */
		if (user.getName().length() <= 0) {
			throw new Exception("É necessário inserir um nome");
		}

		if (user.getPhone().length() <= 0) {
			throw new Exception("É necessário inserir um número de celular");
		}

		if (user.getPassword().length() <= 0) {
			throw new Exception("É necessário inserir uma senha");
		}

		if (user.getCep().length() <= 0) {
			throw new Exception("É necessário inserir um CEP");
		}

		if (user.getAddress().length() <= 0) {
			throw new Exception("É necessário inserir um endereço");
		}

		Optional<User> hasUser = this.userRepository.findById(id);
		Optional<User> hasEmail = this.userRepository.findByEmail(user.getEmail());
		Optional<User> hasCpf = this.userRepository.findByCpf(user.getCpf());

		/**
		 * Validate if the user is already exists
		 */
		if (hasUser.isEmpty()) {
			throw new Exception("Usuário não encontrado!");
		}

		/**
		 * Validate if the inserted email is different from the current email and if
		 * another user is using it or not
		 */
		if (!hasEmail.isEmpty()) {
			if (hasUser.get().getEmail().equals(user.getEmail()) == false) {
				throw new Exception("Este email já está em uso");
			}
		} else {
			hasUser.get().setEmail(user.getEmail());
		}

		/**
		 * Validate if the inserted CPF is different from the current CPF and if another
		 * user is using it or not
		 */
		if (!hasCpf.isEmpty()) {
			if (hasUser.get().getCpf().equals(user.getCpf()) == false) {
				throw new Exception("Este CPF já está em uso");
			}
		} else {
			hasUser.get().setCpf(user.getCpf());
		}

		/**
		 * Hashing the user password
		 */
		String encodedPassword = new BCryptPasswordEncoder().encode(user.getPassword());

		hasUser.get().setName(user.getName());
		hasUser.get().setPhone(user.getPhone());
		hasUser.get().setPassword(encodedPassword);
		hasUser.get().setCep(user.getCep());
		hasUser.get().setAddress(user.getAddress());
		hasUser.get().setUpdatedAt(new Timestamp(System.currentTimeMillis()));

		userRepository.save(hasUser.get());

		return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Usuário criado com sucesso", 200, hasUser));

	}

	/**
	 * 
	 * @return {Response} Boolean
	 * @throws Exception
	 */
	@DeleteMapping("/user/{id}")
	@Transactional
	public ResponseEntity<Response> delete(@PathVariable(value = "id") Long id) throws Exception {

		/**
		 * Validate if user inserted a valid ID
		 */
		if (id == null) {
			throw new Exception("É necessário inserir um ID!");
		}

		Optional<User> hasUser = this.userRepository.findById(id);

		if (hasUser.isEmpty()) {
			throw new Exception("Usuário não encontrado!");
		}

		this.userRepository.deleteById(hasUser.get().getId());

		return ResponseEntity.ok(new Response("Usuário deletado com sucesso", 200, true));
	}
}
