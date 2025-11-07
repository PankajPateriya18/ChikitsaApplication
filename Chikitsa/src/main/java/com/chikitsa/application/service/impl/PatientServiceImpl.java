package com.chikitsa.application.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.chikitsa.application.entity.Patient;
import com.chikitsa.application.repository.PatientRepository;
import com.chikitsa.application.service.PatientService;



@Service
public class PatientServiceImpl implements PatientService {

	private PatientRepository patientRepository;
	
	public PatientServiceImpl(PatientRepository patientRepository) {
		super();
		this.patientRepository = patientRepository;
	}
	
	@Override
	public List<Patient> getAllPatients() {
		List<Patient> patientList = patientRepository.findAllByOrderByIdDesc();
		return patientList;
	}

    public List<Patient> searchPatients(String keyword) {
        return patientRepository.findByFullNameContainingIgnoreCase(keyword);
    }

    public Patient getPatientById(Long id) {
        return patientRepository.findById(id).orElse(null);
    }

    public Patient savePatient(Patient patient) {
        patientRepository.save(patient);
        return patient;
    }

    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }
    
    public Long getAllPatientCount() {
        return patientRepository.count();
    }


    public void updatePatient(Patient patient) {
    	patientRepository.save(patient);
    }
    
    public void deletePatientById(Long id) {
        // Spring Data JPA provides this method automatically
        patientRepository.deleteById(id); 
    }
    
    public List<Patient> findPatientRecordsByFilter(String regNo, String name, String diagnosis, String mobile){
    	return patientRepository.findByFilter(regNo, name, diagnosis, mobile);
    }
    
    public Long getMaximumIdPatientRecords() {
    	Long id = patientRepository.findAll().stream().map(Patient :: getId).max(Long :: compareTo).orElse(0L);
        return id;    	
    }
    
    public String getRegistrationNumberById(Long id) {
    	String regNo = patientRepository.findAll().stream().filter(p -> p.getId().equals(id)).map(Patient :: getRegistrationNumber).findFirst().orElse(null);  
        return regNo;	
    }
    
	
}

