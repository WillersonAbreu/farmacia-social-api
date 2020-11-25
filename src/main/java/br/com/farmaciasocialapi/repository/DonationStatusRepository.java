package br.com.farmaciasocialapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.farmaciasocialapi.models.DonationStatusModel;

public interface DonationStatusRepository extends JpaRepository<DonationStatusModel, Long> {

}
