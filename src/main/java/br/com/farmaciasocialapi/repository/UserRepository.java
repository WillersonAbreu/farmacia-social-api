package br.com.farmaciasocialapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.farmaciasocialapi.models.UserModel;

public interface UserRepository extends JpaRepository<UserModel, Long> {

	Optional<UserModel> findById(Long id);

	Optional<UserModel> findByCpf(String cpf);

	Optional<UserModel> findByEmail(String email);
}
