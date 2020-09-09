package br.com.farmaciasocialapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.farmaciasocialapi.models.User;

public interface UserRepository extends JpaRepository<User, Long>  {
	
	User findById(long id);
	
}
