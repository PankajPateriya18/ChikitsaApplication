package com.chikitsa.application.service;

import java.util.List;

import com.chikitsa.application.entity.Patient;

public interface PatientService {

	List<Patient> getAllPatients();
	
	Patient savePatient(Patient patient);
	
	Long getAllPatientCount();
	
	Long getMaximumIdPatientRecords();
	
	Patient getPatientById(Long id);
	
	void updatePatient(Patient patient);
	
	void deletePatientById(Long id);
	
	List<Patient> findPatientRecordsByFilter(String regNo, String name, String diagnosis, String mobile);
	
	String getRegistrationNumberById(Long id);
	
	
}