package com.chikitsa.application.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "files")
public class FileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;

    private String fileType;

    private Long size; 

    @Column(length = 50)
    private String registrationNumber;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] data;

    public FileEntity() { }

    public FileEntity(String fileName, String fileType, byte[] data, String registrationNumber, Long size) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.data = data;
        this.registrationNumber = registrationNumber;
        this.size = size;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

    
}
