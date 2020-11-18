package br.com.farmaciasocialapi.controllers;

import java.util.List;

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
import br.com.farmaciasocialapi.service.MedicineDonationService;
import br.com.farmaciasocialapi.service.ReservedDonationService;
import javassist.NotFoundException;

@RestController
@RequestMapping("/api/orders")
public class ReservedDonationController {
    @Autowired // serviço
    private ReservedDonationService service;
    
    @Autowired
    private MedicineDonationService medicineDonationService;

    @GetMapping // Listar todas entidades
    public ResponseEntity<List<ReservedDonationModel>> index() {
        List<ReservedDonationModel> entities = service.getAll();
        return ResponseEntity.ok(entities);
    }

    @PostMapping // Cadastrar uma entidade
    @Transactional
    public ResponseEntity<ReservedDonationModel> store(@Valid @RequestBody ReservedDonationModel entity) {
        ReservedDonationModel newEntity = service.store(entity);
        
        //inserir lógica para alterar o status da doação
        MedicineDonationModel doacao = medicineDonationService.getOne(entity.getMedicineDonationId());
        doacao.setStatusId(2l);
        MedicineDonationModel doacaoAjustada = medicineDonationService.update(entity.getMedicineDonationId(), doacao);
        return ResponseEntity.status(201).body(newEntity);
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