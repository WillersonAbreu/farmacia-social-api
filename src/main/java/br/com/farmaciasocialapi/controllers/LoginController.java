package br.com.farmaciasocialapi.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.farmaciasocialapi.config.JwtTokenUtil;
import br.com.farmaciasocialapi.dto.JwtResponseDTO;
import br.com.farmaciasocialapi.dto.UserDTO;
import br.com.farmaciasocialapi.service.UserService;

@RestController
@CrossOrigin
public class LoginController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserService userService;

    @PostMapping("/api/login")
    public ResponseEntity<?> createAuthenticationToken(@Valid @RequestBody UserDTO userDTO) throws Exception {
        this.authenticate(userDTO.getEmail(), userDTO.getPassword()); // validar email e senha

        final UserDetails userDetails = userService.loadUserByUsername(userDTO.getEmail()); // recuperar o usuário
        final String token = jwtTokenUtil.generateToken(userDetails); // gerar o token
        return ResponseEntity.ok(new JwtResponseDTO(token)); // retorna para o usuário o token
    }

    private void authenticate(String email, String senha) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, senha));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("BAD_CREDENTIALS", e);
        }
    }
}