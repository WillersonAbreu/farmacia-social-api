package br.com.farmaciasocialapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.farmaciasocialapi.models.UserModel;

public interface UserRepository extends JpaRepository<UserModel, Long> {

	Optional<UserModel> findById(Long id);

	Optional<UserModel> findByCpf(String cpf);

	Optional<UserModel> findByEmail(String email);

	public Optional<UserModel> findByTokenConfirmation(String tokenConfirmation);

	@Modifying
	@Query("update UserModel u set u.password = :password where u.id = :id")
	void updateSenha(@Param("password") String password, @Param("id") Long id);

}
