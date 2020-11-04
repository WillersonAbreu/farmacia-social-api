package br.com.farmaciasocialapi.controllers;

import java.util.Optional;

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
import br.com.farmaciasocialapi.models.PharmacyModel;
import br.com.farmaciasocialapi.models.UserModel;
import br.com.farmaciasocialapi.repository.PharmacyRepository;
import br.com.farmaciasocialapi.repository.UserRepository;
import br.com.farmaciasocialapi.service.PharmacyService;
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
    @Autowired
    private PharmacyService pharmacyService;

    @PostMapping("/api/login")
    public ResponseEntity<JwtResponseDTO> createAuthenticationToken(@Valid @RequestBody UserDTO userDTO)
            throws Exception {

        Optional<UserModel> user = userService.findByEmail(userDTO.getEmail());
        Optional<PharmacyModel> pharmacy = pharmacyService.findByEmail(userDTO.getEmail());

        this.authenticate(userDTO.getEmail(), userDTO.getPassword()); // validar email e senha
        final UserDetails userDetails = userService.loadUserByUsername(userDTO.getEmail()); // recuperar o usuário

        final String token = jwtTokenUtil.generateToken(userDetails, user, pharmacy); // gerar o token
        JwtResponseDTO response = new JwtResponseDTO();
        response.setToken(token);
        return ResponseEntity.ok(response); // retorna para o usuário o token
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