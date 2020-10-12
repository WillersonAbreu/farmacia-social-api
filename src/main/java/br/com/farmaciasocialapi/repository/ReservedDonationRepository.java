package br.com.farmaciasocialapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.farmaciasocialapi.models.ReservedDonationModel;

public interface ReservedDonationRepository extends JpaRepository<ReservedDonationModel, Long> {

}