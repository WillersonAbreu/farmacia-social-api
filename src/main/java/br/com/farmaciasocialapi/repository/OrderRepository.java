package br.com.farmaciasocialapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.farmaciasocialapi.models.Order;
import br.com.farmaciasocialapi.models.User;

public interface OrderRepository extends JpaRepository<Order, Long>{

	
}
