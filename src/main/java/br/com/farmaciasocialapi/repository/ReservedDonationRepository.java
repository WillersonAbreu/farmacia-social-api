package br.com.farmaciasocialapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.farmaciasocialapi.models.ReservedDonationModel;

public interface ReservedDonationRepository extends JpaRepository<ReservedDonationModel, Long> {
    @Query("SELECT donation FROM ReservedDonationModel donation WHERE donation.userId = :user_id ORDER BY donation.createdAt DESC")
    List<ReservedDonationModel> findAllById(@Param("user_id") Long id);
    // List<ReservedDonationModel> findAllByBenefitedUserId(Long);
}