package br.com.farmaciasocialapi.config;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import br.com.farmaciasocialapi.models.PharmacyModel;
import br.com.farmaciasocialapi.models.UserModel;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil {

	public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

	@Value("${jwt.secret}")
	private String secret;

	// Return the username stored on JWT Token
	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	// Return the expiration time from JWT Token
	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);

	}

	// To Return any information from Token, we need the JWT Secret
	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}

	// Check if the token has expired
	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	// Generate token to User
	public String generateToken(UserDetails userDetails, Optional<UserModel> user, Optional<PharmacyModel> pharmacy) {
		Map<String, Object> claims = new HashMap<>();

		if (!user.isPresent() && pharmacy.isPresent()) {
			claims.put("name", pharmacy.get().getFantasyName());
			claims.put("cep", pharmacy.get().getCep());
			claims.put("address", pharmacy.get().getAddress());
			claims.put("user_type", pharmacy.get().getRoleId());
		} else if (user.isPresent() && !pharmacy.isPresent()) {
			claims.put("name", user.get().getName());
			claims.put("cep", user.get().getCep());
			claims.put("address", user.get().getAddress());
			claims.put("user_type", user.get().getRoleId());
		}

		return doGenerateToken(claims, userDetails.getUsername());
	}

	// Create the token and define the expiration time
	private String doGenerateToken(Map<String, Object> claims, String subject) {
		return Jwts.builder().setClaims(claims).setSubject(subject)
				// .claim("name", this.name)
				// .claim("cep", this.cep)
				// .claim("address", this.address)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}

	// Validate the token
	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = getUsernameFromToken(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

}
