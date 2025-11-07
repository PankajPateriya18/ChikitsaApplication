package com.chikitsa.application.service;

import java.util.List;

import com.chikitsa.application.entity.Notes;

public interface NotesService {

	List<Notes> getAllNotes();

	Notes getNotesById(Long id);

	Notes saveNotes(Notes notes);

	void deleteNotesById(Long id);

	List<Notes> searchByTherapeutic(String therapeutic);

	void save(Notes note);
	
	List<Notes> getAllNotesForReport();
}
