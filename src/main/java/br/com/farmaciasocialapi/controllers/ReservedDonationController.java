package br.com.farmaciasocialapi.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.farmaciasocialapi.models.MedicineDonationModel;
import br.com.farmaciasocialapi.models.ReservedDonationModel;
import br.com.farmaciasocialapi.models.UserModel;
import br.com.farmaciasocialapi.service.EmailService;
import br.com.farmaciasocialapi.service.MedicineDonationService;
import br.com.farmaciasocialapi.service.ReservedDonationService;
import br.com.farmaciasocialapi.util.Mail;
import javassist.NotFoundException;

@RestController
@RequestMapping("/api/orders")
public class ReservedDonationController {
    @Autowired // serviço
    private ReservedDonationService service;

    @Autowired
    private MedicineDonationService medicineDonationService;

    @Autowired
    private EmailService emailService;

    @GetMapping // Listar todas entidades
    public ResponseEntity<List<ReservedDonationModel>> index() {
        List<ReservedDonationModel> entities = service.getAll();
        return ResponseEntity.ok(entities);
    }

    @PostMapping // Cadastrar uma entidade
    @Transactional
    public ResponseEntity<ReservedDonationModel> store(@Valid @RequestBody ReservedDonationModel entity) {
        ReservedDonationModel newEntity = service.store(entity);

        // inserir lógica para alterar o status da doação
        MedicineDonationModel doacao = medicineDonationService.getOne(entity.getMedicineDonationId());
        doacao.setStatusId(2l);
        MedicineDonationModel doacaoAjustada = medicineDonationService.update(entity.getMedicineDonationId(), doacao);
        enviaEmailReserva(doacaoAjustada.getUser(), doacaoAjustada);
        return ResponseEntity.status(201).body(newEntity);
    }

    public ResponseEntity<?> enviaEmailReserva(@RequestBody UserModel usuario, MedicineDonationModel doacao) {

        Mail mail = new Mail();
        mail.setTo(usuario.getEmail());

        mail.setSubject("Medicação Reservada com Sucesso");

        mail.setTemplate("reserved-donation");

        Map<String, Object> model = new HashMap<>();
        model.put("status", "Medicação Reservada");
        model.put("nome", usuario.getName());
        model.put("medicacao", doacao.getTitle());
        model.put("descricao", doacao.getDescription());
        model.put("dosagem", doacao.getDosage());
        model.put("embalagem", doacao.getPacking());
        model.put("tarja", doacao.getStripe());
        model.put("datafab", doacao.getManufacturyDate());
        model.put("dataval", doacao.getShelfLife());

        mail.setModel(model);
        emailService.sendEmail(mail);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{id}") // Listar todas entidades
    public ResponseEntity<List<ReservedDonationModel>> getAllByUserId(@PathVariable(value = "id") Long id) {
        List<ReservedDonationModel> entities = service.getAllByUserId(id);
        return ResponseEntity.ok(entities);
    }

    @GetMapping("/{id}") // Detalhar uma entidade
    public ResponseEntity<ReservedDonationModel> show(@PathVariable(value = "id") Long id) throws NotFoundException {
        ReservedDonationModel entity = service.getOne(id);
        return ResponseEntity.ok(entity);
    }

    @PutMapping("/{id}") // Atualizar uma entidade
    @Transactional
    public ResponseEntity<ReservedDonationModel> update(@Valid @PathVariable(value = "id") Long id,
            @RequestBody ReservedDonationModel entity) {
        ReservedDonationModel updatedEntity = service.update(id, entity);
        return ResponseEntity.ok(updatedEntity);
    }

    @DeleteMapping("/{id}") // Deletar uma entidade
    @Transactional
    public ResponseEntity<?> destroy(@PathVariable(value = "id") Long id) throws NotFoundException {
        service.destroy(id);
        return ResponseEntity.noContent().build();
    }
}