package com.chikitsa.application.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;


@Entity
@Table(name = "notes")
public class Notes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String therapeutic;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String provenTherapeutic;

    // Constructors
    public Notes() {}

    public Notes(String therapeutic, String provenTherapeutic) {
        this.therapeutic = therapeutic;
        this.provenTherapeutic = provenTherapeutic;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTherapeutic() {
        return therapeutic;
    }

    public void setTherapeutic(String therapeutic) {
        this.therapeutic = therapeutic;
    }

    public String getProvenTherapeutic() {
        return provenTherapeutic;
    }

    public void setProvenTherapeutic(String provenTherapeutic) {
        this.provenTherapeutic = provenTherapeutic;
    }
}
