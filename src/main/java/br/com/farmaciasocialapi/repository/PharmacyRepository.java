package br.com.farmaciasocialapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import br.com.farmaciasocialapi.models.PharmacyModel;

public interface PharmacyRepository extends JpaRepository<PharmacyModel, Long> {

}
