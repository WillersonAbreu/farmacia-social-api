package br.com.farmaciasocialapi.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
        Optional<UserModel> user = this.userRepository.findById(id);
        if (!user.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado!");
        }
        return user;
    }

    public Optional<UserModel> isEmailUsed(String email) {
        Optional<UserModel> user = this.userRepository.findByEmail(email);
        if (user.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Este email já está em uso!");
        }
        return user;
    }

    public Optional<UserModel> isCpfUsed(String cpf) {
        Optional<UserModel> user = this.userRepository.findByCpf(cpf);
        if (user.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Este CPF já está em uso!");
        }
        return user;
    }

    public UserModel save(UserModel user) {
        this.isEmailUsed(user.getEmail());
        this.isCpfUsed(user.getCpf());
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        this.userRepository.save(user);
        return user;
    }

    public UserModel update(Long id, UserModel userEntity) {
        Optional<UserModel> foundUser = this.findById(id);
        userEntity.setId(id);

        if (userEntity.getPassword().length() > 0) {
            PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
            String encodedPassword = passwordEncoder.encode(userEntity.getPassword());
            encodedPassword = encodedPassword.replaceAll("\\{bcrypt\\}", "");
            userEntity.setPassword(encodedPassword);
        }

        userEntity.setCreatedAt(foundUser.get().getCreatedAt());
        userEntity.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

        UserModel updatedUser = this.save(userEntity);
        return updatedUser;
    }

    public Boolean deleteById(Long id) {
        Optional<UserModel> user = this.findById(id);
        this.userRepository.deleteById(id);
        return user.isPresent();
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
