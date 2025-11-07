package com.chikitsa.application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chikitsa.application.entity.FileEntity;

public interface FileRepository extends JpaRepository<FileEntity, Long> {
    List<FileEntity> findByRegistrationNumber(String registrationNumber);
}
