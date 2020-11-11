package br.com.farmaciasocialapi.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.farmaciasocialapi.dto.PasswordForgotDTO;
import br.com.farmaciasocialapi.dto.PasswordResetDTO;
import br.com.farmaciasocialapi.models.PasswordResetToken;
import br.com.farmaciasocialapi.models.PharmacyModel;
import br.com.farmaciasocialapi.models.UserModel;
import br.com.farmaciasocialapi.repository.PasswordResetTokenRepository;
import br.com.farmaciasocialapi.repository.PharmacyRepository;
import br.com.farmaciasocialapi.repository.UserRepository;
import br.com.farmaciasocialapi.util.Mail;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PharmacyRepository pharmacyRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private EmailService emailService;
    
    @Autowired
	private PasswordResetTokenRepository tokenRepository;

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

    public Optional<UserModel> findByEmail(String email) {
        Optional<UserModel> user = this.userRepository.findByEmail(email);
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
        String encodedPassword = this.encodePassword(user.getPassword());
        user.setPassword(encodedPassword);
        this.userRepository.save(user);
        return user;
    }

    public UserModel update(Long id, UserModel userEntity) {
        Optional<UserModel> currentUser = this.findById(id);

        userEntity.setId(id);
        userEntity.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

        if(userEntity.getEmail() != currentUser.get().getEmail()){
            this.isEmailUsed(userEntity.getEmail());
        }

        if(userEntity.getCpf() != currentUser.get().getCpf()){
            this.isCpfUsed(userEntity.getCpf());
        }

        UserModel updatedUser = this.userRepository.save(userEntity);
        return updatedUser;
    }

    public Boolean deleteById(Long id) {
        Optional<UserModel> user = this.findById(id);
        this.userRepository.deleteById(id);
        return user.isPresent();
    }

    public String encodePassword(String password) {
        String encodedPassword = new BCryptPasswordEncoder().encode(password);
        return encodedPassword;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<UserModel> usuario = this.userRepository.findByEmail(email);

        if (usuario.isPresent()) {
        	
        	UserModel user = usuario.get();
        	
        	if(user.getTokenConfirmation() != null) {
        		throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Email não foi autenticado");
        	}
        	
            return new User(usuario.get().getEmail(), usuario.get().getPassword(), new ArrayList<>());
        } else if (!usuario.isPresent()) {

            Optional<PharmacyModel> pharmacy = this.pharmacyRepository.findByEmail(email);
            if (pharmacy.isPresent()) {
                return new User(pharmacy.get().getEmail(), pharmacy.get().getPassword(), new ArrayList<>());
            } else {
                throw new UsernameNotFoundException("Usuário não encontrado com o email: " + email);
            }
        } else {
            throw new UsernameNotFoundException("Usuário não encontrado com o email: " + email);
        }
    }
    
    public UserModel getUser() {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Optional<UserModel> optional = userRepository.findByEmail(user.getUsername());
		return optional.get();
	}
    
    public UserModel registro(UserModel usuarioDto) {
		Optional<UserModel> emailExistente = userRepository.findByEmail(usuarioDto.getEmail());
		Optional<UserModel> cpfExistente = userRepository.findByCpf(usuarioDto.getEmail());
		if(emailExistente.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email já existe");
		}
		if(cpfExistente.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CPF já cadastrado");
		}
		
		usuarioDto.setTokenConfirmation(UUID.randomUUID().toString());
		UserModel usuario = this.save(usuarioDto);
		
		
		Mail mail = new Mail();
		mail.setTo(usuario.getEmail());
		mail.setSubject("Confirmação de cadastro");
		// mail.setTemplate("Welcome");
		mail.setTemplate("confirm-register-email");
		
		Map<String, Object> model = new HashMap<>();
		model.put("usuario", usuario);
		model.put("token", "confirm-register?token="+ usuario.getTokenConfirmation());
		
		mail.setModel(model);
		emailService.sendEmail(mail);
		
		return usuario;
	}
    
	public UserModel confirmRegister(String token) {
		Optional<UserModel> optional = userRepository.findByTokenConfirmation(token);

		if (!optional.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não encontrado nenhum usuário com esse token.");
		}
		UserModel usuario = optional.get();
		usuario.setTokenConfirmation(null);
		userRepository.save(usuario);
		return usuario;
	}
	
	public void esqueceuSenha(PasswordForgotDTO dto) {
	
		
		UserModel usuario = userRepository.findByEmail(dto.getEmail()).orElseThrow(()->{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Email não encontrado!"); 
		});
		PasswordResetToken token = new PasswordResetToken();
		token.setToken(UUID.randomUUID().toString());
		token.setUsuario(usuario);
		token.setDataExpiracao(1); //tempo em horas
		tokenRepository.save(token);
		
		Mail mail = new Mail();
		mail.setTo(usuario.getEmail());
		mail.setSubject("Recuperação de senha");
		mail.setTemplate("forgot-password-email");
		
		Map<String, Object> model = new HashMap<>();
		model.put("usuario", usuario);
		model.put("token", "reset-password?token=" + token.getToken());
		mail.setModel(model);
		emailService.sendEmail(mail);
	
	}
	
	public void resetaSenha(PasswordResetDTO dto){
		PasswordResetToken token = tokenRepository.findByToken(dto.getToken());
		if (token == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Token não encontrado!");
		}
		if (token.expirou()) {
			tokenRepository.delete(token);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Token expirado!");
		}
		UserModel usuario = token.getUsuario();
		String senhaAtualizada = passwordEncoder.encode(dto.getPassword());
		userRepository.updateSenha(senhaAtualizada, usuario.getId());
		tokenRepository.delete(token);
	}

}
