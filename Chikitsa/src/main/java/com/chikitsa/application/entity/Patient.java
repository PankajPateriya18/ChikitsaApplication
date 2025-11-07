package com.chikitsa.application.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "patients")
public class Patient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "registration_number", nullable = false)
	private String registrationNumber;

	@Column(name = "full_name", nullable = false)
	private String fullName;

	@Column(name = "father_name")
	private String fatherName;

	@Column(name = "age")
	private String age;

	@Column(name = "sex")
	private String sex;

	@Column(name = "religion")
	private String religion;

	@Column(name = "year")
	private Integer year = LocalDate.now().getYear();

	@Column(name = "date")
	private LocalDate date = LocalDate.now();

	@Column(name = "diagnosis")
	private String diagnosis;

	@Column(name = "address")
	private String address;

	@Column(name = "mobile")
	private Long mobile;

	@Column(name = "email")
	private String email;

	@Lob
	@Column(name = "case_taking", columnDefinition = "TEXT")
	private String casetaking;


	@Column(name = "next_appointment")
	private String nextAppointment;

//	@OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
//	private List<PatientDocument> documents;

	public Patient() {}

	public Patient(String registrationNumber, String fullName, String fatherName, String age, String sex,
			String religion, String diagnosis, String address, Long mobile, String email, String comment) {
		this.registrationNumber = registrationNumber;
		this.fullName = fullName;
		this.fatherName = fatherName;
		this.age = age;
		this.sex = sex;
		this.religion = religion;
		this.diagnosis = diagnosis;
		this.address = address;
		this.mobile = mobile;
		this.email = email;
		this.casetaking = comment;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getReligion() {
		return religion;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getMobile() {
		return mobile;
	}

	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


//	public List<PatientDocument> getDocuments() {
//		return documents;
//	}
//
//	public void setDocuments(List<PatientDocument> documents) {
//		this.documents = documents;
//	}
	
	public String getNextAppointment() {
		return nextAppointment;
	}

	public void setNextAppointment(String nextAppointment) {
		this.nextAppointment = nextAppointment;
	}

	public String getCasetaking() {
	    return casetaking;
	}

	public void setCasetaking(String casetaking) {
	    this.casetaking = casetaking;
	}
}
