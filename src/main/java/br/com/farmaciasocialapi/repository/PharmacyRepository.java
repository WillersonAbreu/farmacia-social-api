package br.com.farmaciasocialapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.farmaciasocialapi.models.Pharmacy;

public interface PharmacyRepository extends JpaRepository<Pharmacy, Long> {

}
