package br.com.farmaciasocialapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.farmaciasocialapi.models.PharmacyModel;
import br.com.farmaciasocialapi.repository.PharmacyRepository;

@Service
public class PharmacyService implements UserDetailsService {

	@Autowired
	private PharmacyRepository repository;

	// ---------listar todas as farmacias---//

	public List<PharmacyModel> getAll() {
		return repository.findAll();
	}
	// -------------------------------------//

	// ---------Cria uma nova Farmacia------//

	public PharmacyModel store(PharmacyModel entity) {
		String password = this.encodePassword(entity.getPassword());
		entity.setPassword(password);
		return repository.save(entity);
	}

	// -------------------------------------//

	// ---------BUSCAR UMA FARMACIA PELO ID---//
	public PharmacyModel getOne(Long id) {
		Optional<PharmacyModel> optional = repository.findById(id);
		if (!optional.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Farmacia não encontrada!");
		}
		PharmacyModel entity = optional.get();
		return entity;
	}
	// -------------------------------------//

	public Optional<PharmacyModel> findByEmail(String email) {
		Optional<PharmacyModel> pharmacy = this.repository.findByEmail(email);
		return pharmacy;
	}

	// -------ATUALIZA UMA FARMACIA JA EXISTENTE-----//
	public PharmacyModel update(Long id, PharmacyModel entity) {
		this.getOne(id);
		entity.setId(id);
		return this.store(entity);
	}
	// -------------------------------------//

	// -------Excluir uma farmacia-----//
	public void destroy(Long id) {
		PharmacyModel entity = this.getOne(id);
		repository.delete(entity);
	}
	// -------------------------------------//

	public String encodePassword(String password) {
		String encodedPassword = new BCryptPasswordEncoder().encode(password);
		return encodedPassword;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		Optional<PharmacyModel> pharmacy = this.repository.findByEmail(email);

		if (pharmacy != null) {
			return new User(pharmacy.get().getEmail(), pharmacy.get().getPassword(), new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("Usuário não encontrado com o email: " + email);
		}
	}

}
