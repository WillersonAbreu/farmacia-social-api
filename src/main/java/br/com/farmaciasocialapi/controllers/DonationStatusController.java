package br.com.farmaciasocialapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.farmaciasocialapi.models.DonationStatusModel;
import br.com.farmaciasocialapi.service.DonationStatusService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "Status das Doações", description = "Controller responsável pelos endpoints de status das doações")
@RequestMapping(value = "/api/donation-status")
public class DonationStatusController {

    @Autowired
    DonationStatusService donationStatusService;

    // Trazer todos os anuncios
    @GetMapping
    @ApiOperation(value = "Listar todos os status", notes = "Método responsavel por listar todas as opções de status")
    public ResponseEntity<List<DonationStatusModel>> getAllDonations() {
        List<DonationStatusModel> status = donationStatusService.getAll();
        return ResponseEntity.ok(status);
    }
}
