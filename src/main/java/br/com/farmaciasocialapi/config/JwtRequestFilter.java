package br.com.farmaciasocialapi.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.farmaciasocialapi.service.JwtUserDetailsService;
import br.com.farmaciasocialapi.service.PharmacyDetailsService;
import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;

	@Autowired
	private PharmacyDetailsService pharmacyUserDetailsService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		final String requestTokenHeader = request.getHeader("Authorization");

		String username = null;
		String jwtToken = null;

		// Get the JWT Token from the Header Authentication property
		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			jwtToken = requestTokenHeader.substring(7);
			try {
				username = jwtTokenUtil.getUsernameFromToken(jwtToken);
			} catch (IllegalArgumentException e) {
				System.out.println("Não foi possível decifrar o token");
			} catch (ExpiredJwtException e) {
				System.out.println("O Token foi expirado");
			}
		} else {
			//logger.warn("JWT Token does not begin with Bearer String");
		}

		// Validate the token
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails;
			try {

				try {
					userDetails = this.jwtUserDetailsService.loadUserByUsername(username);
				} catch (Exception e) {
					userDetails = this.pharmacyUserDetailsService.loadUserByUsername(username);
				}

				if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
					UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
							userDetails, null, userDetails.getAuthorities());
					usernamePasswordAuthenticationToken
							.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				}
			} catch (Exception e) {
				System.out.println(e);
			}

		}
		chain.doFilter(request, response);
	}

}