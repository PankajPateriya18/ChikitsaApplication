package com.chikitsa.application.service.impl;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.chikitsa.application.entity.FileEntity;
import com.chikitsa.application.repository.FileRepository;
import com.chikitsa.application.service.FileService;

@Service
public class FileServiceImpl implements FileService{

    private final FileRepository fileRepository;

    public FileServiceImpl(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public FileEntity store(MultipartFile file, String registrationNumber) throws IOException {

        FileEntity entity = new FileEntity(
            file.getOriginalFilename(),
            file.getContentType(),
            file.getBytes(),
            registrationNumber,
            file.getSize() 
        );

        return fileRepository.save(entity);
    }


    public List<FileEntity> storeAll(MultipartFile[] files, String registrationNumber) {
        return Arrays.stream(files)
                .filter(f -> f != null && !f.isEmpty())
                .map(f -> {
                    try {
                        return store(f, registrationNumber);  // ✅ Corrected
                    } catch (IOException e) {
                        throw new RuntimeException("Failed to store file: " + f.getOriginalFilename(), e);
                    }
                })
                .collect(Collectors.toList());
    }

    public List<FileEntity> getFilesByRegistrationNo(String registrationNumber) {
        return fileRepository.findByRegistrationNumber(registrationNumber); // ✅ Fetch by registration number
    }

    public FileEntity getFileById(Long id) {
        return fileRepository.findById(id).orElse(null);
    }

    public void deleteFileById(Long id) {
        fileRepository.deleteById(id);
    }

}