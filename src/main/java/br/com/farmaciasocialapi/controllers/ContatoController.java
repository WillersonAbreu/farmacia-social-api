package br.com.farmaciasocialapi.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.farmaciasocialapi.dto.ContatoDTO;
import br.com.farmaciasocialapi.service.EmailService;
import br.com.farmaciasocialapi.util.Mail;

@RestController
public class ContatoController {
	@Autowired
	private EmailService emailService;
	
	  @Value( "${api.email.from.address}" )
	    private String address;
	
	@PostMapping("api/contatos")
	public ResponseEntity<?> enviaContato(@RequestBody ContatoDTO contato){
		
		
		Mail mail = new Mail();
		mail.setTo(address);
		
		mail.setSubject("Contato externo");
		
		mail.setTemplate("contato");
		
		Map<String, Object> model = new HashMap<>();
		model.put("nome", contato.getNome());
		model.put("email", contato.getEmail());
		model.put("telefone", contato.getTelefone());
		model.put("assunto", contato.getAssunto());
		model.put("mensagem", contato.getMensagem());
		
		
		mail.setModel(model);
		emailService.sendEmail(mail);
		
		return ResponseEntity.noContent().build();
	}
	

}
