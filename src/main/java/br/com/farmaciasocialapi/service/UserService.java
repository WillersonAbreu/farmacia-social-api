package br.com.farmaciasocialapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.farmaciasocialapi.models.UserModel;
import br.com.farmaciasocialapi.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public List<UserModel> findAll() {
        List<UserModel> users = this.userRepository.findAll();
        return users;
    }

    public Optional<UserModel> findById(Long id) {
        return this.userRepository.findById(id);
    }

    public UserModel save(UserModel user) {
        this.userRepository.save(user);
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<UserModel> usuario = this.userRepository.findByEmail(email);

        if (usuario != null) {
            return new User(usuario.get().getEmail(), usuario.get().getPassword(), new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("Usuário não encontrado com o email: " + email);
        }
    }

}
