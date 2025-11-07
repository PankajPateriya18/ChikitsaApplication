package com.chikitsa.application.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.chikitsa.application.entity.FileEntity;

public interface FileService {
	

    FileEntity store(MultipartFile file, String registrationNumber) throws IOException;

    List<FileEntity> storeAll(MultipartFile[] files, String registrationNumber);

    List<FileEntity> getFilesByRegistrationNo(String registrationNumber);

    FileEntity getFileById(Long id);
    
    void deleteFileById(Long id);

}
