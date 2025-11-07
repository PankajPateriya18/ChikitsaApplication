package com.chikitsa.application.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.chikitsa.application.entity.Notes;
import com.chikitsa.application.repository.NotesRepository;
import com.chikitsa.application.service.NotesService;

@Service
public class NotesServiceImpl implements NotesService {

	private final NotesRepository notesRepository;

	public NotesServiceImpl(NotesRepository notesRepository) {
		this.notesRepository = notesRepository;
	}

	@Override
	public List<Notes> getAllNotes() {
		return notesRepository.findAll();
	}

	@Override
	public Notes getNotesById(Long id) {
		return notesRepository.findById(id).orElse(null);
	}

	@Override
	public Notes saveNotes(Notes notes) {
		return notesRepository.save(notes);
	}

	@Override
	public void deleteNotesById(Long id) {
		notesRepository.deleteById(id);
	}

	@Override 
	public List<Notes> searchByTherapeutic(String therapeutic) { 
		if (therapeutic == null || therapeutic.trim().isEmpty()) { 
			return notesRepository.findAll(); 
		}
		 List<Notes> results = notesRepository.findByTherapeuticContainingIgnoreCase(therapeutic);
	 return results;
	 }

	@Override
	public void save(Notes note) {
		notesRepository.save(note);
	}
	
	public List<Notes> getAllNotesForReport() {
	    return notesRepository.findAll();
	}
}