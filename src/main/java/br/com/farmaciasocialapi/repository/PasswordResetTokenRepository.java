package br.com.farmaciasocialapi.repository;

import br.com.farmaciasocialapi.models.PasswordResetToken;
import br.com.farmaciasocialapi.resources.BaseRepository;

public interface PasswordResetTokenRepository extends BaseRepository<PasswordResetToken>{

	PasswordResetToken findByToken(String token);
	
}
