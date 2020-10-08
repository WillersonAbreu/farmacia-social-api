package br.com.farmaciasocialapi.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.com.farmaciasocialapi.models.UserModel;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

	@Value("${jwt.secret}")
	private static String jwtSecret;// = "44298851e896fa6fc50462c44893c45a";
	private static final Date currentTime = new Date(System.currentTimeMillis());
	private static final Date expirationTime = new Date(
			currentTime.getTime() + 604800000); /* 604800000 = 7 dias em milissegundos */

	public String generateToken(UserModel user) {
		return Jwts.builder().setIssuedAt(currentTime).claim("email", user.getEmail()).claim("name", user.getName())
				.claim("cep", user.getCep()).setSubject(user.getId().toString()).setExpiration(expirationTime)
				.signWith(SignatureAlgorithm.HS256, jwtSecret).compact();
	}

	public Boolean validateToken(String jwt) {

		return true;
	}
}
