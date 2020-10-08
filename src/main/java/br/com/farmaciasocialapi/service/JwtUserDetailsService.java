package br.com.farmaciasocialapi.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.farmaciasocialapi.repository.UserRepository;
import br.com.farmaciasocialapi.models.UserModel;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<UserModel> usuario = usuarioRepository.findByEmail(email);
        if (usuario != null) {
            return new User(usuario.get().getEmail(), usuario.get().getPassword(), new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("Usuário não encontrado com o email: " + email);
        }
    }
}
