package br.com.farmaciasocialapi.resources;

import org.springframework.data.jpa.repository.JpaRepository;

public abstract interface BaseRepository<E extends BaseEntity> extends JpaRepository<E, Long>{

}
