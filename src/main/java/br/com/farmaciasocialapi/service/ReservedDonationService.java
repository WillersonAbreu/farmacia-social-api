package br.com.farmaciasocialapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.farmaciasocialapi.models.MedicineDonationModel;
import br.com.farmaciasocialapi.models.ReservedDonationModel;
import br.com.farmaciasocialapi.repository.ReservedDonationRepository;

@Service
public class ReservedDonationService {

    @Autowired
    private ReservedDonationRepository repository;

    @Autowired
    private MedicineDonationService medicineDonationService;

    public List<ReservedDonationModel> getAll() {
        return repository.findAll();
    }

    public ReservedDonationModel store(ReservedDonationModel entity) {
        return repository.save(entity);
    }

    public ReservedDonationModel getOne(Long id) {
        Optional<ReservedDonationModel> optional = repository.findById(id);
        if (!optional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não foi encontrado!");
        }
        ReservedDonationModel entity = optional.get();
        return entity;
    }

    public ReservedDonationModel update(Long id, ReservedDonationModel entity) {
        this.getOne(id);
        entity.setId(id);
        return this.store(entity);
    }

    public void destroy(Long id) {

        ReservedDonationModel entity = this.getOne(id);
        MedicineDonationModel medicineDonation = this.medicineDonationService.getOne(entity.getMedicineDonationId());
        medicineDonation.setStatusId(1L);
        medicineDonation.setIsActive(true);
        this.medicineDonationService.update(medicineDonation.getId(), medicineDonation);
        repository.delete(entity);
    }

    public List<ReservedDonationModel> getAllByUserId(Long id) {
        List<ReservedDonationModel> reservedDonations = this.repository.findAllById(id);
        return reservedDonations;
    }

}