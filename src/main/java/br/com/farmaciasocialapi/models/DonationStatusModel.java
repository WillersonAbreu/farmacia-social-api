package br.com.farmaciasocialapi.models;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.CreationTimestamp;

import br.com.farmaciasocialapi.resources.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "status_code")
@Getter
@Setter
public class DonationStatusModel implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 2282473662619447233L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String status_string;

    @CreationTimestamp
    @JsonIgnore
    private Timestamp createdAt;

    @CreationTimestamp
    @JsonIgnore
    private Timestamp updatedAt;
}
