package br.com.farmaciasocialapi.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.farmaciasocialapi.repository.PharmacyRepository;
import br.com.farmaciasocialapi.repository.UserRepository;
import br.com.farmaciasocialapi.models.PharmacyModel;
import br.com.farmaciasocialapi.models.UserModel;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository usuarioRepository;

    @Autowired
    private PharmacyRepository pharmacyRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<UserModel> usuario = usuarioRepository.findByEmail(email);

        if (usuario.isPresent()) {
            return new User(usuario.get().getEmail(), usuario.get().getPassword(), new ArrayList<>());
        } else if (!usuario.isPresent()) {
            Optional<PharmacyModel> pharmacy = pharmacyRepository.findByEmail(email);
            if (pharmacy.isPresent()) {
                return new User(pharmacy.get().getEmail(), pharmacy.get().getPassword(), new ArrayList<>());
            } else {
                throw new UsernameNotFoundException("Usuário não encontrado com o email: " + email);
            }
        } else {
            throw new UsernameNotFoundException("Usuário não encontrado com o email: " + email);
        }
    }
}
