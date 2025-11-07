package com.chikitsa.application.controller;

import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.chikitsa.application.entity.Notes;
import com.chikitsa.application.service.NotesService;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class NoteController {

	@Autowired
	private NotesService notesService;
	
	@GetMapping("/notes")
	public String notes(Model model) {
		model.addAttribute("notesList", notesService.getAllNotes());
		model.addAttribute("pageToLoad", "notes"); // <--- important
		model.addAttribute("activePage", "notes");
		model.addAttribute("pageTitle", "Notes");
		return "layout";
	}

	@GetMapping("/addnote")
	public String addNote(Model model) {
		Notes note = new Notes();
		model.addAttribute("note", note);
		model.addAttribute("title", "Add note");
		model.addAttribute("activePage", "addnote");
		model.addAttribute("pageToLoad", "addnote");
		return "layout";
	}

	@PostMapping("/saveNote")
	public String saveNote(@ModelAttribute Notes note, RedirectAttributes redirectAttributes) {
		notesService.save(note);
		redirectAttributes.addFlashAttribute("message", "Note saved successfully!");
		return "redirect:/addnote";
	}

	@GetMapping("/notes/search")
	public String searchNotes(@RequestParam String therapeutic, Model model) {
		List<Notes> notesList = notesService.searchByTherapeutic(therapeutic);
		model.addAttribute("notesList", notesList);
		model.addAttribute("therapeutic", therapeutic);
		model.addAttribute("pageToLoad", "notes"); // <--- important
		model.addAttribute("activePage", "notes");
		model.addAttribute("pageTitle", "Notes");
		return "layout";
	}
	
	
	@PostMapping("/updatenote")
	public String updateNote(@ModelAttribute Notes note, RedirectAttributes redirectAttributes, Model model) {
		notesService.save(note);
		redirectAttributes.addFlashAttribute("message", "Note updated successfully!");
		model.addAttribute("notesList", notesService.getAllNotes());
		model.addAttribute("pageToLoad", "notes"); // <--- important
		model.addAttribute("activePage", "notes");
		model.addAttribute("pageTitle", "Notes");
		return "layout"; // load layout.html
	}

	@GetMapping("/notes/edit/{id}")
	public String editNotes(@PathVariable Long id, Model model) {
		Notes note = notesService.getNotesById(id);
		model.addAttribute("note", note);
		model.addAttribute("title", "Update note");
		model.addAttribute("activePage", "notes");
		model.addAttribute("pageToLoad", "updatenote");
		return "layout";
	}
	
	@GetMapping("/note/delete/{id}")
	public String deleteNote(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
		try {
			notesService.deleteNotesById(id);
			redirectAttributes.addFlashAttribute("message", "Note record deleted successfully!");
			redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("message", "Error deleting record: " + e.getMessage());
			redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
		}
		return "redirect:/notes";
	}
	

	@GetMapping("/notes/details/{id}")
	public String viewNoteDetails(@PathVariable Long id, Model model) {
		Notes note = notesService.getNotesById(id);
		if (note == null) {
			return "redirect:/notes"; // If ID not found return back
		}
		model.addAttribute("note", note);
		model.addAttribute("activePage", "notes");
		model.addAttribute("pageTitle", "Note Details Page");
		model.addAttribute("pageToLoad", "detailnote");
		return "layout";
	}
	
	@GetMapping("/notes/export/excel")
	public void exportNotesReport(HttpServletResponse response) throws IOException {
	    response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
	    String headerValue = "attachment; filename=notes_report.xlsx";
	    response.setHeader("Content-Disposition", headerValue);
	    List<Notes> notesList = notesService.getAllNotesForReport();
	    XSSFWorkbook workbook = new XSSFWorkbook();
	    XSSFSheet sheet = workbook.createSheet("Notes Report");
	    Row header = sheet.createRow(0);
	    header.createCell(0).setCellValue("ID");
	    header.createCell(1).setCellValue("Therapeutic");
	    header.createCell(2).setCellValue("Proven Therapeutic");
	    int rowIndex = 1;
	    for (Notes note : notesList) {
	        Row row = sheet.createRow(rowIndex++);
	        row.createCell(0).setCellValue(note.getId());
	        row.createCell(1).setCellValue(note.getTherapeutic());
	        row.createCell(2).setCellValue(note.getProvenTherapeutic());
	    }
	    for (int i = 0; i < 3; i++) {
	        sheet.autoSizeColumn(i);
	    }
	    workbook.write(response.getOutputStream());
	    workbook.close();
	}



}

