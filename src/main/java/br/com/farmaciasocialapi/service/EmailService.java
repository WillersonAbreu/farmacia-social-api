package br.com.farmaciasocialapi.service;
import java.nio.charset.StandardCharsets;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import br.com.farmaciasocialapi.util.Mail;
@Service
public class EmailService {
	@Autowired
    private JavaMailSender emailSender;
    @Autowired
    private SpringTemplateEngine templateEngine;
    @Value( "${api.email.from.address}" )
    private String address;
    @Value( "${api.email.from.name}" )
    private String name;
    public void sendEmail(Mail mail) {
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());
            if(mail.getFromEmail() == null)
            	mail.setFromEmail(address);
            if(mail.getFromName() == null)
            	mail.setFromName(name);
            Context context = new Context();
            context.setVariables(mail.getModel());
            String html = templateEngine.process(mail.getTemplate(), context);
            helper.setTo(mail.getTo());
            helper.setText(html, true);
            helper.setSubject(mail.getSubject());
            helper.setFrom(mail.getFromEmail(), mail.getFromName());
            emailSender.send(message);
        } catch (Exception e){
        	e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Email não pode ser enviado");
        }
    }
}
