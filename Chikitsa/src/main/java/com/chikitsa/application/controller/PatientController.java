package com.chikitsa.application.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.chikitsa.application.entity.FileEntity;
import com.chikitsa.application.entity.Patient;
import com.chikitsa.application.pdf.PatientPdfGenerator;
import com.chikitsa.application.service.FileService;
import com.chikitsa.application.service.PatientService;

import jakarta.servlet.http.HttpServletResponse;


@Controller
public class PatientController {

	@Autowired
	private PatientService patientService;
	@Autowired
	private FileService fileService;

	@GetMapping("/patients")
	public String listPatients(Model model) {
		model.addAttribute("activePage", "patients");
		model.addAttribute("pageTitle", "All Patients");
		model.addAttribute("pageToLoad", "patients");
		List<Patient> patientsList = patientService.getAllPatients();
		model.addAttribute("patientsList", patientsList);
		return "layout";
	}

	@GetMapping("/search")
	public String listPatients(@RequestParam(required = false) String regNo,
			@RequestParam(required = false) String name, @RequestParam(required = false) String diagnosis,
			@RequestParam(required = false) String mobile, Model model) {
		List<Patient> patientsList = patientService.findPatientRecordsByFilter(regNo, name, diagnosis, mobile);
		model.addAttribute("patientsList", patientsList);
		model.addAttribute("regNo", regNo);
		model.addAttribute("name", name);
		model.addAttribute("diagnosis", diagnosis);
		model.addAttribute("mobile", mobile);
		model.addAttribute("activePage", "patients");
		model.addAttribute("pageTitle", "All Patients");
		model.addAttribute("pageToLoad", "patients");
		return "layout";
	}

	@GetMapping("/addpatient")
	public String addPatient(Model model) {
		Patient patient = new Patient();
		patient.setDate(LocalDate.now());
		model.addAttribute("patient", patient);
		model.addAttribute("title", "Add Patient");
		model.addAttribute("activePage", "addpatient");
		model.addAttribute("pageToLoad", "addpatient");
		return "layout";
	}

	@PostMapping(value = "/savePatient")
	public String savePatient(@ModelAttribute("patient") Patient patient, 
			RedirectAttributes redirectAttributes,
			@RequestParam("files") MultipartFile[] files,
			@RequestParam(name = "patientImage", required = false) MultipartFile patientImage, 
			Model model) {
		if (patient.getDate() == null || patient.getDate().toString().trim().isEmpty()) {
	        patient.setDate(LocalDate.now());
	    }
		LocalDate today = LocalDate.now();
		int currentYear = today.getYear();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM", Locale.ENGLISH);
		String currentMonth = today.format(formatter);
		String registrationNumber = currentYear + "-" + currentMonth + "-" + (patientService.getMaximumIdPatientRecords() + 1);
		patient.setRegistrationNumber(registrationNumber);
		patientService.savePatient(patient);
        if (files == null || files.length == 0) {
        } else {
            try {
            	fileService.storeAll(files, registrationNumber);
                System.out.println("File Saved successfully");
            } catch (Exception e) {
            }
        }
		redirectAttributes.addFlashAttribute("message", "New Patient added successfully!");
		model.addAttribute("title", "Add Patient");
		model.addAttribute("activePage", "addpatient");
		model.addAttribute("pageToLoad", "addpatient");
		return "redirect:/addpatient";
	}    
    
	
	@GetMapping("/patients/export/excel")
	@ResponseBody
	public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=patients.xlsx");
        List<Patient> patients = patientService.getAllPatients();
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Patients");
        Row header = sheet.createRow(0);
        String[] columns = { "ID", "Registration No", "Name", "Father Name", "Age", "Sex",
                "Religion", "Diagnosis", "Address", "Mobile", "Email", "Case Taking", "Next Appointment", "Date" };
        for (int i = 0; i < columns.length; i++) {
            header.createCell(i).setCellValue(columns[i]);
        }
        int rowIdx = 1;
        for (Patient p : patients) {
            Row row = sheet.createRow(rowIdx++);
            row.createCell(0).setCellValue(p.getId());
            row.createCell(1).setCellValue(p.getRegistrationNumber());
            row.createCell(2).setCellValue(p.getFullName());
            row.createCell(3).setCellValue(p.getFatherName());
            row.createCell(4).setCellValue(p.getAge());
            row.createCell(5).setCellValue(p.getSex());
            row.createCell(6).setCellValue(p.getReligion());
            row.createCell(7).setCellValue(p.getDiagnosis());
            row.createCell(8).setCellValue(p.getAddress());
            row.createCell(9).setCellValue(p.getMobile());
            row.createCell(10).setCellValue(p.getEmail());
            row.createCell(11).setCellValue(p.getCasetaking());
            row.createCell(11).setCellValue(p.getNextAppointment());
            row.createCell(12).setCellValue(p.getDate().toString());
        }
        workbook.write(response.getOutputStream());
        response.getOutputStream().flush();
        workbook.close();
    }

	@GetMapping("/patient/edit/{id}")
	public String editPatient(@PathVariable Long id, Model model) {
		Patient patient = patientService.getPatientById(id);
		model.addAttribute("patient", patient);
		String regNo = patientService.getRegistrationNumberById(id);
		List<FileEntity> files = fileService.getFilesByRegistrationNo(regNo);
        model.addAttribute("files", files);
		model.addAttribute("activePage", "patients");
		model.addAttribute("pageTitle", "Update Patient Details");
		model.addAttribute("pageToLoad", "updatepatient");
		return "layout";
	}

	@PostMapping("/updatePatient")
	public String updatePatient(@ModelAttribute Patient patient, RedirectAttributes redirectAttributes, Model model,
			@RequestParam("files") MultipartFile[] files) {
		patient.setDate(LocalDate.now());
		patientService.updatePatient(patient);
        if (files == null || files.length == 0) {
      } else {
          try {
          	fileService.storeAll(files, patient.getRegistrationNumber());
              System.out.println("File Saved successfully");
          } catch (Exception e) {
          }
      }
		redirectAttributes.addFlashAttribute("message", "Patient updated successfully!");
		model.addAttribute("activePage", "patients");
		model.addAttribute("pageTitle", "All Patients");
		model.addAttribute("pageToLoad", "patients");
		model.addAttribute("patientsList", patientService.getAllPatients());
		return "redirect:/patients";
	}

	@GetMapping("/patients/delete/{id}")
	public String deletePatient(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
		try {
			patientService.deletePatientById(id);
			redirectAttributes.addFlashAttribute("message", "Patient record deleted successfully!");
			redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("message", "Error deleting record: " + e.getMessage());
			redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
		}
		return "redirect:/patients";
	}

	@GetMapping("/patient/details/{id}")
	public String viewPatientDetails(@PathVariable Long id, Model model) {
		Patient patient = patientService.getPatientById(id);
		if (patient == null) {
			return "redirect:/patients";
		}
		model.addAttribute("patient", patient);
		String regNo = patientService.getRegistrationNumberById(id);
		List<FileEntity> files = fileService.getFilesByRegistrationNo(regNo);
        model.addAttribute("files", files);
		model.addAttribute("activePage", "patients");
		model.addAttribute("pageTitle", "Patient Details Page");
		model.addAttribute("pageToLoad", "detailpatient");
		return "layout"; 
	}

	@PostMapping("/patient/{patientId}/uploadDocument")
	public String uploadDocument(@PathVariable Long patientId,
			@RequestParam(value = "file", required = false) MultipartFile file, RedirectAttributes redirectAttributes,
			Model model) {
		try {
			if (file == null || file.isEmpty()) {
				redirectAttributes.addFlashAttribute("error", "Please select a file to upload.");
				return "redirect:/patient/documents/" + patientId;
			}
			String uploadDir = "patient-documents/" + patientId;
			Path uploadPath = Paths.get(uploadDir);
			if (!Files.exists(uploadPath)) {
				Files.createDirectories(uploadPath);
			}
			Path filePath = uploadPath.resolve(file.getOriginalFilename());
			Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
			redirectAttributes.addFlashAttribute("success", "File uploaded successfully!");
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("error", "Upload failed. Try again.");
		}
		model.addAttribute("title", "Add Patient");
		model.addAttribute("activePage", "addpatient");
		model.addAttribute("pageToLoad", "addpatient");
		return "layout";
	}
	
	@GetMapping("/patients/export/pdf/{id}")
	@ResponseBody
	public void exportPatientPdf(@PathVariable Long id, HttpServletResponse response) throws Exception {
	    Patient patient = patientService.getPatientById(id);
	    response.setContentType("application/pdf");
	    response.setHeader("Content-Disposition", "attachment; filename=patient_" + patient.getId() + ".pdf");
	    PatientPdfGenerator. generatePdf(response.getOutputStream(), patient);
	}
	
    @GetMapping("/patient/document/download/{fileId}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable Long fileId) {
        FileEntity fileEntity = fileService.getFileById(fileId);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileEntity.getFileName() + "\"")
                .body(fileEntity.getData());
    }
    
    @GetMapping("/patient/document/delete/{patientId}/{fileId}")
    public String deleteFile(@PathVariable Long patientId, Model model,
            @PathVariable Long fileId, RedirectAttributes redirectAttributes) {
        fileService.deleteFileById(fileId);
        redirectAttributes.addFlashAttribute("message", "Document deleted successfully!");
        Patient patient = patientService.getPatientById(patientId);
		model.addAttribute("patient", patient);
		String regNo = patientService.getRegistrationNumberById(patientId);
		List<FileEntity> files = fileService.getFilesByRegistrationNo(regNo);
        model.addAttribute("files", files);
		model.addAttribute("activePage", "patients");
		model.addAttribute("pageTitle", "Update Patient Details");
		model.addAttribute("pageToLoad", "updatepatient");
		return "layout";
//        return "redirect:/patient/edit/" + patientId; 
    }

}