package br.com.farmaciasocialapi.controllers;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.farmaciasocialapi.models.User;
import br.com.farmaciasocialapi.repository.UserRepository;

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
	 * Find an specific user
	 * 
	 * @param id
	 * @return {User} user
	 */
	@GetMapping("/user/{id}")
	public User getUser(@PathVariable(value = "id") long id) {
		return userRepository.findById(id);
	}

	/**
	 * Store a new User in database
	 */
	@PostMapping("/user")
	public String create(@RequestBody User user) {
		try {
			String encodedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
			user.setPassword(encodedPassword);
			userRepository.save(user);
			return "Usuário registrado com sucesso!";
		} catch (ConstraintViolationException e) {
			return e.getMessage();
		} catch (Exception e) {
			return e.getMessage();
		}
	}
}
