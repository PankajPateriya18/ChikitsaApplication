package com.chikitsa.application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.chikitsa.application.entity.Patient;


public interface PatientRepository extends JpaRepository<Patient, Long>{

	List<Patient> findAllByOrderByIdDesc();
	
	List<Patient> findByFullNameContainingIgnoreCase(String fullName);
	
	List<Patient> findAllByOrderByDateDesc();

	List<Patient> findAllByOrderByRegistrationNumberDesc();
	
	@Query("SELECT p FROM Patient p WHERE " +
	        "LOWER(p.registrationNumber) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
	        "LOWER(p.fullName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
	        "LOWER(p.fatherName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
	        "CONCAT(p.age, '') LIKE CONCAT('%', :keyword, '%') OR " +
	        "LOWER(p.sex) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
	        "LOWER(p.religion) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
	        "LOWER(p.diagnosis) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
	        "LOWER(p.address) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
	        "LOWER(p.email) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
	        "CONCAT(p.mobile, '') LIKE CONCAT('%', :keyword, '%')" + 
	        "Order by Id desc")
	List<Patient> searchPatients(String keyword);



	@Query("SELECT p FROM Patient p WHERE " +
	        "(:regNo IS NULL OR LOWER(p.registrationNumber) LIKE LOWER(CONCAT('%', :regNo, '%'))) AND " +
	        "(:name IS NULL OR LOWER(p.fullName) LIKE LOWER(CONCAT('%', :name, '%'))) AND " +
	        "(:diagnosis IS NULL OR LOWER(p.diagnosis) LIKE LOWER(CONCAT('%', :diagnosis, '%'))) AND " +
	        "(:mobile IS NULL OR CONCAT(p.mobile, '') LIKE CONCAT('%', :mobile, '%'))" + 
	        "Order by Id desc")
	List<Patient> findByFilter(
	        @Param("regNo") String regNo,
	        @Param("name") String name,
	        @Param("diagnosis") String diagnosis,
	        @Param("mobile") String mobile
	);

    
    
}
