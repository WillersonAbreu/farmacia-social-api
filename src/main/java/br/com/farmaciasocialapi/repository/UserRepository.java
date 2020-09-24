package br.com.farmaciasocialapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.farmaciasocialapi.models.User;

public interface UserRepository extends JpaRepository<User, Long>  {
	
	Optional<User> findById(Long id);
	
	User findByCpf(String cpf);
	
	User findByEmail(String email);
}
