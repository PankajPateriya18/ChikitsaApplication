package com.chikitsa.application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chikitsa.application.entity.Notes;

public interface NotesRepository extends JpaRepository<Notes, Long> {

	  List<Notes> findByTherapeuticContainingIgnoreCase(String therapeutic);

//	List<Notes> findByTherapeuticContainingIgnoreCaseOrProvenTherapeuticContainingIgnoreCase(String therapeutic, String provenTherapeutic);
}
