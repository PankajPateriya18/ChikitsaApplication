package com.chikitsa.application.pdf;

//import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Base64; // <-- Add this import

//import java.nio.charset.StandardCharsets;
//
import org.springframework.core.io.ClassPathResource;

import com.chikitsa.application.controller.PatientController;
//
import com.chikitsa.application.entity.Patient;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
//import com.itextpdf.text.Document;
//import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
//import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
//import java.io.OutputStream;
//import java.nio.file.Paths;

//--- PASTE ALL OF THESE IMPORTS AT THE TOP OF YOUR FILE ---
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;

import java.io.ByteArrayInputStream; // <-- FIX for ByteArrayInputStream error
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class PatientPdfGenerator {

	public static void generatePdf(OutputStream out, Patient patient) throws Exception {
//	    Document document = new Document(PageSize.A4);
//	    PdfWriter writer = PdfWriter.getInstance(document, out);
//	    document.open();
//	    String imagePath = Paths.get("src/main/resources/static/images/chikitsa.png").toAbsolutePath().toString();
//	    String html = "<html>" +
//	            "<head>" +
//	            "<style>" +
//	            "body { font-family: Arial, sans-serif; font-size: 14pt; padding: 20px; }" +
//	            ".header { font-size: 14pt; padding-bottom: 10px; margin-bottom: 20px; }" +
//	            ".header-line { height: 3px; background: #000; margin-top: 6px; margin-bottom: 20px; }" +
//	            ".doctor-info { line-height: 1.0; font-size: 14pt; }" +
//	            ".logo-section { text-align: center; }" +
//	            ".logo-section img { height: 80px; }" +
//	            ".info-row { margin-bottom: 12px; font-size: 12pt; }" +
//	            ".patient-label { font-weight: bold; width: 160px; display: inline-block; }" +
//	            ".patient-value { font-style: italic; }" +
//	            ".footer { text-align: center; background-color: #0066cc; color: #fff; padding: 5px 0; font-size: 10px; font-weight: 500; text-transform: uppercase; }" +
//                ".app-footer{ bottom:0; left:0; right:0; height: var; display:flex; align-items:center; justify-content: center; background: linear-gradient(0deg,#ffffff, #fafcfe); border-top: 1px solid rgba(0,0,0,0.06); z-index: 1000; font-size: 10px; color: var(--muted);}" + 
//	            ".dashed-line { border-top: 1px dashed #ccc; margin: 14px 0; }" +
//	            ".signature-line { border-top: 1px solid #000; padding-top: 5px; }" +
//	            "</style>" +
//	            "</head>" +
//	            "<body>" +
//	            "<div class='header'>" +
//	            "<table width='100%'>" +
//	            "<tr>" +
//	            "   <td width='57%' class='doctor-info'>" +
//	            "       <b>Dr. Pradeep Tiwari</b><br />" +
//	            "       Consulting Homeopath &amp; General Physician<br />" +
//	            "       B.H.M.S, M.D <br />" +
//	            "       Registration No. 26878 <br />" +
//	            "       Mob: +91 8878547608 <br />" + 
//	            "       Date: " + patient.getDate() +
//	            "   </td>" +
//	            "   <td width='30%' class='logo-section'>" +
//	            "       <img src='file:" + imagePath + "' />" +
//	            "       <div style='font-size: 12pt;'>FAMILY WELLNESS CLINIC</div>" +
//	            "   </td>" +
//	            "</tr>" +
//	            "</table>" +
//	            "<div class='header-line'></div>" +
//	            "</div>" +
//	            "<div class='info-row'><span class='patient-label'>Reg. No. :  </span><span class='patient-value'>" + patient.getRegistrationNumber() + "</span></div>" +
//	            "<div class='info-row'><span class='patient-label'>Name :  </span><span class='patient-value'>" + patient.getFullName() + "</span></div>" +
//	            "<div class='info-row'><span class='patient-label'>Father's Name :  </span><span class='patient-value'>" + patient.getFatherName() + "</span></div>" +
//	            "<div class='info-row'><span class='patient-label'>Age/Gender/Religion :  </span><span class='patient-value'>" + patient.getAge() + "/" + patient.getSex() + "/" + patient.getReligion() + "</span></div>" +
//	            "<div class='info-row'><span class='patient-label'>Address :  </span><span class='patient-value'>" + patient.getAddress() + "</span></div>" +
//	            "<div class='info-row'><span class='patient-label'>Diagnosis :  </span><span class='patient-value'>" + patient.getDiagnosis() + "</span></div>" +
//	            "<div class='info-row'><span class='patient-label'>Case Taking :  </span><span class='patient-value'>" + patient.getCasetaking() + "</span></div>" +
//	            "<div class='info-row'><span class='patient-label'>Next Appointment :  </span><span class='patient-value'>" + ((patient.getNextAppointment() == null) ? "" : patient.getNextAppointment())  + "</span></div>"  +        
//	            "</body></html>";	    
//	    InputStream is = new ByteArrayInputStream(html.getBytes(StandardCharsets.UTF_8));
//	    XMLWorkerHelper.getInstance().parseXHtml(writer, document, is, StandardCharsets.UTF_8);
//	    document.close();

		
//		
//        // 1. Initialize PDF Writer and Document
//        PdfWriter writer = new PdfWriter(out);
//        PdfDocument pdf = new PdfDocument(writer);
//        pdf.setDefaultPageSize(PageSize.A4);
//        Document document = new Document(pdf);
//
//        // 2. Prepare HTML content
//        String imagePath = Paths.get("src/main/resources/static/images/chikitsa.png").toAbsolutePath().toString();
//        
//        // --- Using a StringBuilder for better readability and performance ---
//        StringBuilder html = new StringBuilder();
//        html.append("<html><head><style>")
//            .append("body { font-family: Arial, sans-serif; font-size: 11pt; }")
//            .append(".header-table, .info-table { width: 100%; border-collapse: collapse; }")
//            .append(".header-line { height: 2px; background: #000; margin-top: 10px; margin-bottom: 20px; }")
//            .append(".doctor-info { line-height: 1.2; font-size: 12pt; vertical-align: top; }")
//            .append(".logo-section { text-align: center; vertical-align: top; }")
//            .append(".logo-section img { height: 80px; }")
//            .append(".info-row { padding-bottom: 10px; }")
//            .append(".patient-label { font-weight: bold; width: 180px; display: inline-block; }")
//            .append(".patient-value { }")
//            .append("</style></head><body>")
//            
//            // --- Header Section ---
//            .append("<table class='header-table'><tr>")
//            .append("<td style='width: 60%;' class='doctor-info'>")
//            .append("<b>Dr. Pradeep Tiwari</b><br />")
//            .append("Consulting Homeopath &amp; General Physician<br />")
//            .append("B.H.M.S, M.D <br />")
//            .append("Registration No. 26878 <br />")
//            .append("Mob: +91 8878547608 <br />")
//            .append("Date: ").append(patient.getDate())
//            .append("</td>")
//            .append("<td style='width: 40%;' class='logo-section'>")
//            .append("<img src='").append(imagePath).append("' />") // Ensure the image path is correct
//            .append("<div style='font-size: 12pt;'>FAMILY WELLNESS CLINIC</div>")
//            .append("</td>")
//            .append("</tr></table>")
//            .append("<div class='header-line'></div>")
//
//            // --- Patient Information Section ---
//            .append("<div class='info-row'><span class='patient-label'>Reg. No. :</span><span class='patient-value'>").append(patient.getRegistrationNumber()).append("</span></div>")
//            .append("<div class='info-row'><span class='patient-label'>Name :</span><span class='patient-value'>").append(patient.getFullName()).append("</span></div>")
//            .append("<div class='info-row'><span class='patient-label'>Father's Name :</span><span class='patient-value'>").append(patient.getFatherName()).append("</span></div>")
//            .append("<div class='info-row'><span class='patient-label'>Age/Gender/Religion :</span><span class='patient-value'>").append(patient.getAge() + "/" + patient.getSex() + "/" + patient.getReligion()).append("</span></div>")
//            .append("<div class='info-row'><span class='patient-label'>Address :</span><span class='patient-value'>").append(patient.getAddress()).append("</span></div>")
//            .append("<div class='info-row'><span class='patient-label'>Diagnosis :</span><span class='patient-value'>").append(patient.getDiagnosis()).append("</span></div>")
//            
//            // This is the field most likely to cause overflow
//            .append("<div class='info-row'><span class='patient-label'>Case Taking :</span><span class='patient-value'>").append(patient.getCasetaking()).append("</span></div>")
//            
//            .append("<div class='info-row'><span class='patient-label'>Next Appointment :</span><span class='patient-value'>").append(patient.getNextAppointment() == null ? "" : patient.getNextAppointment()).append("</span></div>")
//            
//            .append("</body></html>");
//
//        // 3. Configure Converter Properties (important for resolving local file paths like images)
//        ConverterProperties properties = new ConverterProperties();
//        properties.setBaseUri(Paths.get("src/main/resources/static/").toAbsolutePath().toString());
//
//        // 4. Convert HTML to PDF
//        HtmlConverter.convertToPdf(html.toString(), pdf, properties);
//
//        // Document is automatically closed by HtmlConverter

//		 PdfWriter writer = new PdfWriter(out);
//	        PdfDocument pdf = new PdfDocument(writer);
//	        pdf.setDefaultPageSize(PageSize.A4);
//	        Document document = new Document(pdf);
//
//	        // --- START OF CHANGE: Load image from classpath and encode ---
////	        String imageResourcePath = "/static/images/chikitsa.png"; // Path from the classpath root
//	        
//	        // Use the class loader to get the resource as a stream
////	        InputStream imageStream = PdfGenerator.class.getResourceAsStream(imageResourcePath);
////	        if (imageStream == null) {
////	            throw new IOException("Resource not found: " + imageResourcePath);
////	        }
////	        
//	        // Read the stream into a byte array and encode it
////	        byte[] imageBytes = imageStream.readAllBytes();
////	        String base64Image = Base64.getEncoder().encodeToString(imageBytes);
////	        String imageSrcDataUri = "data:image/png;base64," + base64Image;
//	        // --- END OF CHANGE ---
//	        
//	        // Load and encode the image (this part remains the same)
//	        String imageResourcePath = "/static/images/chikitsa.png";
//	        InputStream imageStream = PdfGenerator.class.getResourceAsStream(imageResourcePath);
//	        if (imageStream == null) {
//	            throw new IOException("Resource not found: " + imageResourcePath);
//	        }
//	        byte[] imageBytes = imageStream.readAllBytes();
//	        String base64Image = Base64.getEncoder().encodeToString(imageBytes);
//	        String imageSrcDataUri = "data:image/png;base64," + base64Image;
//
//	        StringBuilder html = new StringBuilder();
//	        html.append("<html><head><style>")
//	            // ... (Your CSS remains unchanged) ...
//	            .append("body { font-family: Arial, sans-serif; font-size: 11pt; }")
//	            .append(".header-table, .info-table { width: 100%; border-collapse: collapse; }")
//	            // ... etc. ...
//	            .append("</style></head><body>")
//	            
//	            .append("<table class='header-table'><tr>")
//	            .append("<td style='width: 60%;' class='doctor-info'>")
//	            // ... (doctor info) ...
//	            .append("</td>")
//	            .append("<td style='width: 40%;' class='logo-section'>")
//
//	            // --- KEY CHANGE: Use the data URI in the image tag ---
////	            .append("<img src='").append(imageSrcDataUri).append("' />")
//	            .append("<img src='").append(imageSrcDataUri).append("' />")
//	            .append("<div style='font-size: 12pt;'>FAMILY WELLNESS CLINIC</div>")
//	            .append("</td>")
//	            .append("</tr></table>")
//	            .append("<div class='header-line'></div>")
//
//	            // ... (Rest of your HTML string remains unchanged) ...
//	            .append("<div class='info-row'><span class='patient-label'>Reg. No. :</span><span class='patient-value'>").append(patient.getRegistrationNumber()).append("</span></div>")
//	            // ... etc. ...
//	            .append("</body></html>");
//	        
//	        // With the embedded image, you no longer need ConverterProperties for this purpose.
////	        HtmlConverter.convertToPdf(html.toString(), pdf);
//	        // --- START OF FIX ---
//	        // Convert the final HTML string to an InputStream
//	        InputStream htmlStream = new ByteArrayInputStream(html.toString().getBytes(StandardCharsets.UTF_8));
//	        
//	        // Now call the converter with the InputStream and the PdfDocument.
//	        // This method signature is available in more versions of the library.
//	        HtmlConverter.convertToPdf(htmlStream, pdf);
//	        // --- END OF FIX ---
		 PdfWriter writer = new PdfWriter(out);
	        PdfDocument pdf = new PdfDocument(writer);
	        pdf.setDefaultPageSize(PageSize.A4);
	        Document document = new Document(pdf);

	        // Load and encode the image
	        String imageResourcePath = "/static/images/chikitsa.png";
	        
	        // --- FIX for 'PdfGenerator cannot be resolved' error ---
	        // Replace 'YOUR_CLASS_NAME.class' with the name of your class.
	        // For example, if your class is 'PdfService', use 'PdfService.class'.
	        InputStream imageStream = PatientController.class.getResourceAsStream(imageResourcePath);

	        if (imageStream == null) {
	            throw new IOException("Resource not found: " + imageResourcePath + ". Ensure it's in src/main/resources/static/images/");
	        }
	        
	        byte[] imageBytes = imageStream.readAllBytes();
	        String base64Image = Base64.getEncoder().encodeToString(imageBytes);
	        String imageSrcDataUri = "data:image/png;base64," + base64Image;

	        // Build the HTML string
	        StringBuilder html = new StringBuilder();
	        // (This is your full HTML string from before)
	        html.append("<html><head><style>")
	            .append("body { font-family: Arial, sans-serif; font-size: 14pt; padding: 20px; }") // etc...

	            .append(".header { font-size: 14pt; padding-bottom: 10px; margin-bottom: 20px; }")
	            .append(".header-line { height: 3px; background: #000; margin-top: 6px; margin-bottom: 20px; }")
	            .append(".doctor-info { line-height: 1.0; font-size: 14pt; }")
	            .append(".logo-section { text-align: right; vertical-align: top; }")
	            .append(".logo-section img { height: 120px;}")
	            .append(".info-row { margin-bottom: 12px; font-size: 12pt;}")
	            .append(".patient-label { font-weight: bold; width: 160px; display: inline-block;")
	            .append(".patient-value { font-style: italic; }")
	            .append(".dashed-line { border-top: 1px dashed #ccc; margin: 14px 0; }")
	            .append(".signature-line { border-top: 1px solid #000; padding-top: 5px;}")
	            .append("</style></head><body>")
	            .append("<table class='header-table'><tr>")
	            .append("<td style='width: 70%;' class='doctor-info'>")
	            .append("<b>Dr. Pradeep Tiwari</b><br />")
	            .append("Consulting Homeopath &amp; General Physician<br />")
	            .append("B.H.M.S, M.D <br />")
	            .append("Registration No. 26878 <br />")
	            .append("Mob: +91 8878547608 <br />")
	            .append("Date: ").append(patient.getDate())
	            .append("</td>")
	            .append("<td style='width: 100%;' class='logo-section'>")
	            .append("<img src='").append(imageSrcDataUri).append("' />")
	            .append("</td>")
	            .append("</tr></table>")
	            .append("<div class='header-line'></div>")
	            .append("<div class='info-row'><span class='patient-label'>Reg. No.            :  </span><span class='patient-value'>").append(patient.getRegistrationNumber()).append("</span></div>")
	            .append("<div class='info-row'><span class='patient-label'>Name                :  </span><span class='patient-value'>").append(patient.getFullName()).append("</span></div>")
	            .append("<div class='info-row'><span class='patient-label'>Father's Name       :  </span><span class='patient-value'>").append(patient.getFatherName()).append("</span></div>")
	            .append("<div class='info-row'><span class='patient-label'>Age/Gender/Religion :  </span><span class='patient-value'>").append(patient.getAge() + "/" + patient.getSex() + "/" + patient.getReligion() ).append("</span></div>")
	            .append("<div class='info-row'><span class='patient-label'>Address             :  </span><span class='patient-value'>").append(patient.getAddress()).append("</span></div>")
	            .append("<div class='info-row'><span class='patient-label'>Diagnosis           :  </span><span class='patient-value'>").append(patient.getDiagnosis()).append("</span></div>")
	            .append("<div class='info-row'><span class='patient-label'>Case Taking         :  </span><span class='patient-value'>").append(patient.getCasetaking()).append("</span></div>")
	            .append("<div class='info-row'><span class='patient-label'>Next Appointment    :  </span><span class='patient-value'>").append(((patient.getNextAppointment() == null) ? "" : patient.getNextAppointment())).append("</span></div>")
	            .append("</body></html>");

	        // Convert HTML to PDF using an InputStream
	        InputStream htmlStream = new ByteArrayInputStream(html.toString().getBytes(StandardCharsets.UTF_8));
	        HtmlConverter.convertToPdf(htmlStream, pdf);
	}
	
    // Util for table cells
//    private static PdfPCell getHeaderCell(String text) {
//        PdfPCell cell = new PdfPCell(new Phrase(text, new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD)));
//        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//        cell.setPadding(5);
//        return cell;
//    }

    private static PdfPCell getNormalCell(String text) {
        PdfPCell cell = new PdfPCell(new Phrase(text, new Font(Font.FontFamily.HELVETICA, 12)));
        cell.setPadding(5);
        return cell;
    }
    
    private static String prepareHtmlBodyForReport(Patient patient) {
//    	String html = 
//    			"\r\n"
//    			+ "<html lang=\"en\">\r\n"
//    			+ "<head>\r\n"
//    			+ "    <meta charset=\"UTF-8\">\r\n"
//    			+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
//    			+ "    <title>Chikitsa Patient Slip</title>\r\n"
//    			+ "    <style>\r\n"
//    			+ "        body {\r\n"
//    			+ "            font-family: Arial, sans-serif;\r\n"
//    			+ "            margin: 0px;\r\n"
//    			+ "            padding: 100px;\r\n"
//    			+ "            background-color: #f0f0f0;\r\n"
//    			+ "        }\r\n"
//    			+ "\r\n"
//    			+ "        .slip-container {\r\n"
//    			+ "            width: 800px; \r\n"
//    			+ "            margin: 0 auto;\r\n"
//    			+ "            background-color: #fff;\r\n"
//    			+ "            border: 1px solid #ccc;\r\n"
//    			+ "            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\r\n"
//    			+ "            min-height: 1000px;\r\n"
//    			+ "            display: flex;\r\n"
//    			+ "            flex-direction: column;\r\n"
//    			+ "            padding: 100px;\r\n"
//    			+ "        }\r\n"
//    			+ "\r\n"
//    			+ "        .header {\r\n"
//    			+ "            padding: 15px 20px 15px 20px;\r\n"
//    			+ "            border-bottom: 2px solid #000;\r\n"
//    			+ "            position: relative;\r\n"
//    			+ "            margin-bottom: 10px;\r\n"
//    			+ "        }\r\n"
//    			+ "\r\n"
//    			+ "        .header-top {\r\n"
//    			+ "            display: flex;\r\n"
//    			+ "            justify-content: space-between;\r\n"
//    			+ "            align-items: flex-start;\r\n"
//    			+ "        }\r\n"
//    			+ "\r\n"
//    			+ "        .doctor-info {\r\n"
//    			+ "            font-size: 15px;\r\n"
//    			+ "            line-height: 1.4;\r\n"
//    			+ "            color: #000000;\r\n"
//    			+ "            font-weight: bold;\r\n"
//    			+ "            width: 350px;\r\n"
//    			+ "        }\r\n"
//    			+ "\r\n"
//    			+ "        .clinic-info {\r\n"
//    			+ "            text-align: right;\r\n"
//    			+ "            font-size: 15px;\r\n"
//    			+ "            line-height: 1.4;\r\n"
//    			+ "            color: #000000;\r\n"
//    			+ "            font-weight: bold;\r\n"
//    			+ "            width: 300px;\r\n"
//    			+ "        }\r\n"
//    			+ "\r\n"
//    			+ "        .logo-section {\r\n"
//    			+ "            display: flex;\r\n"
//    			+ "            flex-direction: column;\r\n"
//    			+ "            align-items: center;\r\n"
//    			+ "            padding: 5px 0;\r\n"
//    			+ "            margin-bottom: 5px;\r\n"
//    			+ "        }\r\n"
//    			+ "\r\n"
//    			+ "        .logo-section img {\r\n"
//    			+ "            height: 30px;\r\n"
//    			+ "            margin-bottom: 3px;\r\n"
//    			+ "        }\r\n"
//    			+ "\r\n"
//    			+ "        .clinic-name {\r\n"
//    			+ "            font-size: 18px;\r\n"
//    			+ "            font-weight: bold;\r\n"
//    			+ "            color: #0066cc;\r\n"
//    			+ "        }\r\n"
//    			+ "\r\n"
//    			+ "        .clinic-tagline {\r\n"
//    			+ "            font-size: 10px;\r\n"
//    			+ "            color: #555;\r\n"
//    			+ "            margin-top: 2px;\r\n"
//    			+ "        }\r\n"
//    			+ "\r\n"
//    			+ "        .body-content {\r\n"
//    			+ "            flex-grow: 1;\r\n"
//    			+ "            padding: 20px;\r\n"
//    			+ "            background-image: url('./images/chikitsa.png'); \r\n"
//    			+ "            background-repeat: no-repeat;\r\n"
//    			+ "            background-position: center 20%;\r\n"
//    			+ "            background-size: 300px;\r\n"
//    			+ "            opacity: 0.1; \r\n"
//    			+ "        }\r\n"
//    			+ "\r\n"
//    			+ "        .patient-info-section {\r\n"
//    			+ "            padding: 10px 20px;\r\n"
//    			+ "            font-size: 14px;\r\n"
//    			+ "            border-bottom: 1px dashed #ccc;\r\n"
//    			+ "        }\r\n"
//    			+ "\r\n"
//    			+ "        .patient-info-line {\r\n"
//    			+ "            margin-bottom: 10px; \r\n"
//    			+ "            line-height: 1.4;\r\n"
//    			+ "            display: flex;\r\n"
//    			+ "            align-items: baseline; \r\n"
//    			+ "        }\r\n"
//    			+ "\r\n"
//    			+ "        .patient-label {\r\n"
//    			+ "            font-weight: bold;\r\n"
//    			+ "            min-width: 150px; \r\n"
//    			+ "            max-width: 150px;\r\n"
//    			+ "            width: 150px;\r\n"
//    			+ "            flex-shrink: 0\r\n"
//    			+ "        }\r\n"
//    			+ "\r\n"
//    			+ "        .patient-value {\r\n"
//    			+ "            font-style: italic;\r\n"
//    			+ "        }\r\n"
//    			+ "\r\n"
//    			+ "        .footer {\r\n"
//    			+ "            text-align: center;\r\n"
//    			+ "            background-color: #0066cc; \r\n"
//    			+ "            color: #fff;\r\n"
//    			+ "            padding: 5px 0;\r\n"
//    			+ "            font-size: 10px;\r\n"
//    			+ "            font-weight: 500;\r\n"
//    			+ "            text-transform: uppercase;\r\n"
//    			+ "        }\r\n"
//    			+ "\r\n"
//    			+ "    </style>\r\n"
//    			+ "</head>\r\n"
//    			+ "<body>\r\n"
//    			+ "\r\n"
//    			+ "    <div class=\"slip-container\">\r\n"
//    			+ "        <div class=\"header\">\r\n"
//    			+ "            <div class=\"header-top\">\r\n"
//    			+ "                <div class=\"doctor-info\">\r\n"
//    			+ "                    Dr.  Pradeep tiwari<br/>\r\n"
//    			+ "                    Consulting Homeopath & General Physician<br/>\r\n"
//    			+ "                    B.H.M.S, D.N.H.E., C.G.O.<br/>\r\n"
//    			+ "                    Mob: +91 8878547608\r\n"
//    			+ "                </div>\r\n"
//    			+ "\r\n"
//    			+ "                <div class=\"logo-section\">\r\n"
//    			+ "                    <img src=\"./images/chikitsa.png\" alt=\"Chikitsa Logo\"> \r\n"
//    			+ "                    <div class=\"clinic-tagline\">FAMILY WELLNESS CLINIC</div>\r\n"
//    			+ "                </div>\r\n"
//    			+ "            </div>\r\n"
//    			+ "        </div>\r\n"
//    			+ "\r\n"
//    			+ "        <div class=\"patient-info-section\">\r\n"
//    			+ "            <div class=\"patient-info-line\">\r\n"
//    			+ "                <span class=\"patient-label\">Reg. No. :</span> \r\n"
//    			+ "                <span class=\"patient-value\">" + patient.getRegistrationNumber() + "</span>\r\n"
//    			+ "            </div>\r\n"
//    			+ "            <div class=\"patient-info-line\">\r\n"
//    			+ "                <span class=\"patient-label\">Name :</span> \r\n"
//    			+ "                <span class=\"patient-value\">" + patient.getFullName() + "</span>\r\n"
//    			+ "                <span class=\"patient-label\" style=\"margin-left: 50px;\">S/O:</span> \r\n"
//    			+ "                <span class=\"patient-value\">**[GENDER]**</span>\r\n"
//    			+ "            </div>\r\n"
//    			+ "            <div class=\"patient-info-line\">\r\n"
//    			+ "                <span class=\"patient-label\">Age/Gender/Religion :</span> \r\n"
//    			+ "                <span class=\"patient-value\">"+ patient.getAge() + "/" + patient.getSex() + "/" + patient.getReligion() +"</span>\r\n"
//    			+ "            </div>\r\n"
//    			+ "            <div class=\"patient-info-line\">\r\n"
//    			+ "                <span class=\"patient-label\">Address :</span> \r\n"
//    			+ "                <span class=\"patient-value\">" + patient.getAddress() + "</span>\r\n"
//    			+ "            </div>\r\n"
//    			+ "            <div class=\"patient-info-line\">\r\n"
//    			+ "                <span class=\"patient-label\">Diagnosis :</span> \r\n"
//    			+ "                <span class=\"patient-value\">" + patient.getDiagnosis() + "</span>\r\n"
//    			+ "            </div>\r\n"
//    			+ "            <div class=\"patient-info-line\">\r\n"
//    			+ "                <span class=\"patient-label\">Case Taking :</span> \r\n"
//    			+ "                <span class=\"patient-value\">" + patient.getCasetaking() + "</span>\r\n"
//    			+ "            </div>\r\n"
//    			+ "            <div class=\"patient-info-line\">\r\n"
//    			+ "                <span class=\"patient-label\">Next Appointment :</span> \r\n"
//    			+ "                <span class=\"patient-value\">" + patient.getNextAppointment() + "</span>\r\n"
//    			+ "            </div>\r\n"
//    			+ "        </div>\r\n"
//    			+ "\r\n"
//    			+ "        <div class=\"body-content\">\r\n"
//    			+ "        \r\n"
//    			+ "        </div>\r\n"
//    			+ "        <div class=\"patient-info-line\">\r\n"
//    			+ "            <span class=\"patient-label\">Date :</span> \r\n"
//    			+ "            <span class=\"patient-value\">" + patient.getDate() + "</span>\r\n"
//    			+ "        </div>\r\n"
//    			+ "        <div class=\"footer\">\r\n"
//    			+ "            For A Better Mind, Body, & Life.\r\n"
//    			+ "        </div>\r\n"
//    			+ "    </div>\r\n"
//    			+ "\r\n"
//    			+ "</body>\r\n"
//    			+ "</html>";
//    	String html = "\r\n"
//    			+ "<html lang=\"en\">\r\n"
//    			+ "<head>\r\n"
//    			+ "    <meta charset=\"UTF-8\" />\r\n"
//    			+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\r\n"
//    			+ "    <title>Chikitsa Patient Slip</title>\r\n"
//    			+ "    <style>\r\n"
//    			+ "        /* --- PDF Optimization for Single Page --- */\r\n"
//    			+ "        body {\r\n"
//    			+ "            font-family: Arial, sans-serif;\r\n"
//    			+ "            margin: 0;\r\n"
//    			+ "            padding: 20px; /* Page margin */\r\n"
//    			+ "            background-color: #fff; \r\n"
//    			+ "            font-size: 10pt; /* Default print size */\r\n"
//    			+ "        }\r\n"
//                + "        table { border-collapse: collapse; width: 100%; }\r\n"
//                + "        td { padding: 0; vertical-align: top; }\r\n"
//    			+ "\r\n"
//    			+ "        .slip-container {\r\n"
//    			+ "            width: 100%; \r\n"
//    			+ "            margin: 0 auto;\r\n"
//    			+ "            min-height: auto; \r\n"
//    			+ "            padding: 0;\r\n"
//                + "        }\r\n"
//    			+ "\r\n"
//    			+ "        /* Header Styles */\r\n"
//    			+ "        .header {\r\n"
//    			+ "            padding: 5px 0 10px 0; \r\n"
//    			+ "            border-bottom: 2px solid #000;\r\n"
//    			+ "            margin-bottom: 10px;\r\n"
//    			+ "            font-size: 10pt;\r\n"
//    			+ "        }\r\n"
//    			+ "        .doctor-info {\r\n"
//    			+ "            line-height: 1.3;\r\n"
//    			+ "            font-weight: bold;\r\n"
//    			+ "            padding-right: 10px;\r\n"
//    			+ "        }\r\n"
//    			+ "        .clinic-info {\r\n"
//    			+ "            text-align: right;\r\n"
//    			+ "            line-height: 1.3;\r\n"
//    			+ "            font-weight: normal; \r\n"
//    			+ "            font-size: 9pt;\r\n"
//    			+ "        }\r\n"
//    			+ "        .logo-section {\r\n"
//    			+ "            text-align: center;\r\n"
//    			+ "            padding-top: 5px;\r\n"
//    			+ "            line-height: 1;\r\n"
//    			+ "        }\r\n"
//    			+ "        .logo-section img { height: 25px; margin-bottom: 2px;}\r\n"
//    			+ "        .clinic-tagline { font-size: 8pt; color: #555;}\r\n"
//    			+ "\r\n"
//    			+ "        /* Patient Info Section */\r\n"
//    			+ "        .patient-info-section {\r\n"
//    			+ "            padding: 5px 0 10px 0;\r\n"
//    			+ "            line-height: 1.5;\r\n"
//    			+ "        }\r\n"
//    			+ "        .patient-info-row td {\r\n"
//    			+ "            padding-bottom: 3px; /* Add small vertical space between rows */\r\n"
//    			+ "        }\r\n"
//    			+ "        .patient-label {\r\n"
//    			+ "            font-weight: bold;\r\n"
//    			+ "            white-space: nowrap;\r\n"
//    			+ "            width: 120px; /* Consistent label width */\r\n"
//    			+ "            padding-right: 5px;\r\n"
//    			+ "        }\r\n"
//    			+ "        .patient-value { font-style: italic; }\r\n"
//    			+ "\r\n"
//    			+ "        /* Main Content Area */\r\n"
//    			+ "        .main-content-area {\r\n"
//    			+ "            padding: 10px 0;\r\n"
//    			+ "        }\r\n"
//    			+ "        .casetaking-text {\r\n"
//    			+ "            padding-left: 10px;\r\n"
//    			+ "            font-style: italic;\r\n"
//    			+ "            display: block;\r\n"
//    			+ "            margin-bottom: 15px;\r\n"
//    			+ "        }\r\n"
//                + "        /* Footer Styles */\r\n"
//    			+ "        .footer {\r\n"
//    			+ "            text-align: center;\r\n"
//    			+ "            background-color: #0066cc; \r\n"
//    			+ "            color: #fff;\r\n"
//    			+ "            padding: 3px 0;\r\n"
//    			+ "            font-size: 8pt;\r\n"
//    			+ "            font-weight: 500;\r\n"
//    			+ "            text-transform: uppercase;\r\n"
//    			+ "        }\r\n"
//    			+ "        .footer-line {\r\n"
//    			+ "             margin-top: 10px;\r\n"
//    			+ "             margin-bottom: 10px;\r\n"
//    			+ "             border-top: 1px dashed #ccc;\r\n"
//    			+ "             height: 1px;\r\n"
//    			+ "             clear: both;\r\n"
//    			+ "        }\r\n"
//    			+ "\r\n"
//    			+ "    </style>\r\n"
//    			+ "</head>\r\n"
//    			+ "<body>\r\n"
//    			+ "\r\n"
//    			+ "    <div class=\"slip-container\">\r\n"
//    			+ "        <div class=\"header\">\r\n"
//    			+ "            <table width=\"100%\">\r\n"
//    			+ "                <tr>\r\n"
//    			+ "                    <td width=\"45%\" class=\"doctor-info\">\r\n"
//    			+ "                        Dr.  Pradeep tiwari<br />\r\n"
//    			+ "                        Consulting Homeopath & General Physician<br />\r\n"
//    			+ "                        B.H.M.S, D.N.H.E., C.G.O.<br />\r\n"
//    			+ "                        Mob: +91 8878547608\r\n"
//    			+ "                    </td>\r\n"
//    			+ "                    <td width=\"10%\" class=\"logo-section\">\r\n"
//    			+ "                        <img src=\"/static/images/chikitsa.png\" alt=\"Chikitsa Logo\" /> \r\n"
//    			+ "                        <div style=\"font-size: 6pt; color: #777;\">health care for sure</div>\r\n"
//    			+ "                        <div class=\"clinic-tagline\">FAMILY WELLNESS CLINIC</div>\r\n"
//    			+ "                    </td>\r\n"
//    			+ "                    <td width=\"45%\" class=\"clinic-info\">\r\n"
//    			+ "                        @Mob.No. - 6263111854<br />\r\n"
//    			+ "                        Timing- Mon. to Sat- 6 pm to 9 pm.<br />\r\n"
//    			+ "                        Sunday - 10 am to 1 pm.<br />\r\n"
//    			+ "                        Address- 1140, Bindeshwar Apartment<br />\r\n"
//    			+ "                        Sch. No. 114 Part-I Indore (M.P.) 452010\r\n"
//    			+ "                    </td>\r\n"
//    			+ "                </tr>\r\n"
//    			+ "            </table>\r\n"
//    			+ "        </div>\r\n"
//    			+ "\r\n"
//    			+ "        \r\n"
//    			+ "        <div class=\"patient-info-section\">\r\n"
//    			+ "            <table width=\"100%\">\r\n"
//    			+ "                \r\n"
//    			+ "                <tr class=\"patient-info-row\">\r\n"
//    			+ "                    <td width=\"100%\">\r\n"
//    			+ "                        <span class=\"patient-label\">Reg. No. :</span> \r\n"
//    			+ "                        <span class=\"patient-value\">" + patient.getRegistrationNumber() + "</span>\r\n"
//    			+ "                    </td>\r\n"
//    			+ "                </tr>\r\n"
//    			+ "                \r\n"
//    			+ "                <tr class=\"patient-info-row\">\r\n"
//    			+ "                    <td width=\"100%\">\r\n"
//    			+ "                        <span class=\"patient-label\">Name :</span> \r\n"
//    			+ "                        <span class=\"patient-value\">" + patient.getFullName() + "</span>\r\n"
//    			+ "                    </td>\r\n"
//    			+ "                </tr>\r\n"
//    			+ "                \r\n"
//    			+ "                <tr class=\"patient-info-row\">\r\n"
//    			+ "                    <td width=\"100%\">\r\n"
//    			+ "                        <span class=\"patient-label\">Father's Name :</span> \r\n"
//    			+ "                        <span class=\"patient-value\">" + patient.getFatherName() + "</span>\r\n"
//    			+ "                    </td>\r\n"
//    			+ "                </tr>\r\n"
//    			+ "                \r\n"
//    			+ "                <tr class=\"patient-info-row\">\r\n"
//    			+ "                    <td width=\"100%\">\r\n"
//    			+ "                        <span class=\"patient-label\">Age/Gender/Religion :</span> \r\n"
//    			+ "                        <span class=\"patient-value\">"+ patient.getAge() + "/" + patient.getSex() + "/" + patient.getReligion() +"</span>\r\n"
//    			+ "                    </td>\r\n"
//    			+ "                </tr>\r\n"
//    			+ "                \r\n"
//    			+ "                <tr class=\"patient-info-row\">\r\n"
//    			+ "                    <td width=\"100%\">\r\n"
//    			+ "                        <span class=\"patient-label\">Address :</span> \r\n"
//    			+ "                        <span class=\"patient-value\">" + patient.getAddress() + "</span>\r\n"
//    			+ "                    </td>\r\n"
//    			+ "                </tr>\r\n"
//    			+ "                \r\n"
//    			+ "                <tr class=\"patient-info-row\">\r\n"
//    			+ "                    <td width=\"100%\">\r\n"
//    			+ "                        <span class=\"patient-label\">Diagnosis :</span> \r\n"
//    			+ "                        <span class=\"patient-value\">" + patient.getDiagnosis() + "</span>\r\n"
//    			+ "                    </td>\r\n"
//    			+ "                </tr>\r\n"
//    			+ "                <tr class=\"patient-info-row\">\r\n"
//    			+ "                    <td width=\"100%\">\r\n"
//    			+ "                        <span class=\"patient-label\">Case Taking :</span> \r\n"
//    			+ "                        <span class=\"patient-value\">" + patient.getCasetaking() + "</span>\r\n"
//    			+ "                    </td>\r\n"
//    			+ "                </tr>\r\n"
//    			+ "                <tr class=\"patient-info-row\">\r\n"
//    			+ "                    <td width=\"100%\">\r\n"
//    			+ "                        <span class=\"patient-label\">Next Appointment :</span> \r\n"
//    			+ "                        <span class=\"patient-value\">" + patient.getNextAppointment() + "</span>\r\n"
//    			+ "                    </td>\r\n"
//    			+ "                </tr>\r\n"
//    			+ "                <tr>\r\n"
//    			+ "                   <td width=\"15%\"><span class=\"patient-label\">Date :</span></td> \r\n"
//    			+ "                   <td width=\"85%\"><span class=\"patient-value\">" + patient.getDate() + "</span></td>\r\n"
//    			+ "                </tr>\r\n"
//    			+ "            </table>\r\n"
//    			+ "        </div>\r\n"
//    			+ "\r\n"
//    			+ "        \r\n"
//    			+ "        <div class=\"footer-line\" style=\"margin-top: 0; margin-bottom: 10px;\"></div>\r\n"
//    			+ "        <div class=\"footer-line\" style=\"margin-top: 10px; margin-bottom: 5px;\"></div>\r\n"
//    			+ "        <p style=\"text-align: right; border-top: 1px solid #000; padding-top: 5px; margin-top: 5px; margin-bottom: 20px; font-weight: bold; font-size: 10pt;\">\r\n"
//    			+ "            Dr. Pradeep tiwari\r\n"
//    			+ "        </p>\r\n"
//    			+ "        \r\n"
//    			+ "        \r\n"
//    			+ "        <div class=\"footer\">\r\n"
//    			+ "            For A Better Mind, Body, & Life.\r\n"
//    			+ "        </div>\r\n"
//    			+ "    </div>\r\n"
//    			+ "\r\n"
//    			+ "</body>\r\n"
//    			+ "</html>";
//    	String html = "\r\n"
//    			+ "<html lang=\"en\">\r\n"
//    			+ "<head>\r\n"
//    			+ "    <meta charset=\"UTF-8\" />\r\n"
//    			+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\r\n"
//    			+ "    <title>Chikitsa Patient Slip</title>\r\n"
//    			+ "    <style>\r\n"
//    			+ "        /* --- PDF Optimization for Single Page --- */\r\n"
//    			+ "        body {\r\n"
//    			+ "            font-family: Arial, sans-serif;\r\n"
//    			+ "            margin: 0;\r\n"
//    			+ "            padding: 20px; /* Page margin */\r\n"
//    			+ "            background-color: #fff; \r\n"
//    			+ "            font-size: 10pt; /* Default print size */\r\n"
//    			+ "        }\r\n"
//                + "        table { border-collapse: collapse; width: 100%; }\r\n"
//                + "        td { padding: 0; vertical-align: top; }\r\n"
//    			+ "\r\n"
//    			+ "        .slip-container {\r\n"
//    			+ "            width: 100%; \r\n"
//    			+ "            margin: 0 auto;\r\n"
//    			+ "            min-height: auto; \r\n"
//    			+ "            padding: 0;\r\n"
//                + "        }\r\n"
//    			+ "\r\n"
//    			+ "        /* Header Styles */\r\n"
//    			+ "        .header {\r\n"
//    			+ "            padding: 5px 0 10px 0; \r\n"
//    			+ "            border-bottom: 2px solid #000;\r\n"
//    			+ "            margin-bottom: 10px;\r\n"
//    			+ "            font-size: 10pt;\r\n"
//    			+ "        }\r\n"
//    			+ "        .doctor-info {\r\n"
//    			+ "            line-height: 1.3;\r\n"
//    			+ "            font-weight: bold;\r\n"
//    			+ "            padding-right: 10px;\r\n"
//    			+ "        }\r\n"
//    			+ "        .clinic-info {\r\n"
//    			+ "            text-align: right;\r\n"
//    			+ "            line-height: 1.3;\r\n"
//    			+ "            font-weight: normal; \r\n"
//    			+ "            font-size: 9pt;\r\n"
//    			+ "        }\r\n"
//    			+ "        .logo-section {\r\n"
//    			+ "            text-align: center;\r\n"
//    			+ "            padding-top: 5px;\r\n"
//    			+ "            line-height: 1;\r\n"
//    			+ "        }\r\n"
//    			+ "        .logo-section img { height: 25px; margin-bottom: 2px;}\r\n"
//    			+ "        .clinic-tagline { font-size: 8pt; color: #555;}\r\n"
//    			+ "\r\n"
//    			+ "        /* Patient Info Section */\r\n"
//    			+ "        .patient-info-section {\r\n"
//    			+ "            padding: 5px 0 10px 0;\r\n"
//    			+ "            line-height: 1.5;\r\n"
//    			+ "        }\r\n"
//    			+ "        .patient-info-row td {\r\n"
//    			+ "            padding-bottom: 3px; /* Add small vertical space between rows */\r\n"
//    			+ "        }\r\n"
//    			+ "        .patient-label {\r\n"
//    			+ "            font-weight: bold;\r\n"
//    			+ "            white-space: nowrap;\r\n"
//    			+ "            width: 120px; /* Consistent label width */\r\n"
//    			+ "            padding-right: 5px;\r\n"
//    			+ "        }\r\n"
//    			+ "        .patient-value { font-style: italic; }\r\n"
//    			+ "\r\n"
//    			+ "        /* Main Content Area */\r\n"
//    			+ "        .main-content-area {\r\n"
//    			+ "            padding: 10px 0;\r\n"
//    			+ "            /* Fixed height for content area below dashed line */\r\n"
//    			+ "            height: 500px;\r\n" 
//    			+ "        }\r\n"
//    			+ "        .casetaking-text {\r\n"
//    			+ "            padding-left: 10px;\r\n"
//    			+ "            font-style: italic;\r\n"
//    			+ "            display: block;\r\n"
//    			+ "            margin-bottom: 15px;\r\n"
//    			+ "        }\r\n"
//                + "        /* Footer Styles */\r\n"
//    			+ "        .footer {\r\n"
//    			+ "            text-align: center;\r\n"
//    			+ "            background-color: #0066cc; \r\n"
//    			+ "            color: #fff;\r\n"
//    			+ "            padding: 3px 0;\r\n"
//    			+ "            font-size: 8pt;\r\n"
//    			+ "            font-weight: 500;\r\n"
//    			+ "            text-transform: uppercase;\r\n"
//    			+ "        }\r\n"
//    			+ "        .dashed-line {\r\n"
//    			+ "             margin-top: 10px;\r\n"
//    			+ "             margin-bottom: 10px;\r\n"
//    			+ "             border-top: 1px dashed #ccc;\r\n"
//    			+ "             height: 1px;\r\n"
//    			+ "             clear: both;\r\n"
//    			+ "        }\r\n"
//    			+ "        .signature-line {\r\n"
//                + "            border-top: 1px solid #000; \r\n"
//                + "            padding-top: 5px; \r\n"
//                + "            margin-top: 5px;\r\n"
//                + "        }\r\n"
//    			+ "\r\n"
//    			+ "    </style>\r\n"
//    			+ "</head>\r\n"
//    			+ "<body>\r\n"
//    			+ "\r\n"
//    			+ "    <div class=\"slip-container\">\r\n"
//    			+ "        <div class=\"header\">\r\n"
//    			+ "            <table width=\"100%\">\r\n"
//    			+ "                <tr>\r\n"
//    			+ "                    <td width=\"45%\" class=\"doctor-info\">\r\n"
//    			+ "                        Dr.  Pradeep tiwari<br />\r\n"
//    			+ "                        Consulting Homeopath & General Physician<br />\r\n"
//    			+ "                        B.H.M.S, D.N.H.E., C.G.O.<br />\r\n"
//    			+ "                        Mob: +91 8878547608\r\n"
//    			+ "                    </td>\r\n"
//    			+ "                    <td width=\"10%\" class=\"logo-section\">\r\n"
//    			+ "                        <img src=\"/static/images/chikitsa.png\" alt=\"Chikitsa Logo\" /> \r\n"
//    			+ "                        <div style=\"font-size: 6pt; color: #777;\">health care for sure</div>\r\n"
//    			+ "                        <div class=\"clinic-tagline\">FAMILY WELLNESS CLINIC</div>\r\n"
//    			+ "                    </td>\r\n"
//    			+ "                    <td width=\"45%\" class=\"clinic-info\">\r\n"
//    			+ "                        @Mob.No. - 6263111854<br />\r\n"
//    			+ "                        Timing- Mon. to Sat- 6 pm to 9 pm.<br />\r\n"
//    			+ "                        Sunday - 10 am to 1 pm.<br />\r\n"
//    			+ "                        Address- 1140, Bindeshwar Apartment<br />\r\n"
//    			+ "                        Sch. No. 114 Part-I Indore (M.P.) 452010\r\n"
//    			+ "                    </td>\r\n"
//    			+ "                </tr>\r\n"
//    			+ "            </table>\r\n"
//    			+ "        </div>\r\n"
//    			+ "\r\n"
//    			+ "        <div class=\"patient-info-section\">\r\n"
//    			+ "            <table width=\"100%\">\r\n"
//    			+ "                \r\n"
//    			+ "                <tr class=\"patient-info-row\">\r\n"
//    			+ "                    <td width=\"100%\">\r\n"
//    			+ "                        <span class=\"patient-label\">Reg. No. :</span> \r\n"
//    			+ "                        <span class=\"patient-value\">" + patient.getRegistrationNumber() + "</span>\r\n"
//    			+ "                    </td>\r\n"
//    			+ "                </tr>\r\n"
//    			+ "                \r\n"
//    			+ "                <tr class=\"patient-info-row\">\r\n"
//    			+ "                    <td width=\"100%\">\r\n"
//    			+ "                        <span class=\"patient-label\">Name :</span> \r\n"
//    			+ "                        <span class=\"patient-value\">" + patient.getFullName() + "</span>\r\n"
//    			+ "                    </td>\r\n"
//    			+ "                </tr>\r\n"
//    			+ "                \r\n"
//    			+ "                <tr class=\"patient-info-row\">\r\n"
//    			+ "                    <td width=\"100%\">\r\n"
//    			+ "                        <span class=\"patient-label\">Father's Name :</span> \r\n"
//    			+ "                        <span class=\"patient-value\">" + patient.getFatherName() + "</span>\r\n"
//    			+ "                    </td>\r\n"
//    			+ "                </tr>\r\n"
//    			+ "                \r\n"
//    			+ "                <tr class=\"patient-info-row\">\r\n"
//    			+ "                    <td width=\"100%\">\r\n"
//    			+ "                        <span class=\"patient-label\">Age/Gender/Religion :</span> \r\n"
//    			+ "                        <span class=\"patient-value\">"+ patient.getAge() + "/" + patient.getSex() + "/" + patient.getReligion() +"</span>\r\n"
//    			+ "                    </td>\r\n"
//    			+ "                </tr>\r\n"
//    			+ "                \r\n"
//    			+ "                <tr class=\"patient-info-row\">\r\n"
//    			+ "                    <td width=\"100%\">\r\n"
//    			+ "                        <span class=\"patient-label\">Address :</span> \r\n"
//    			+ "                        <span class=\"patient-value\">" + patient.getAddress() + "</span>\r\n"
//    			+ "                    </td>\r\n"
//    			+ "                </tr>\r\n"
//    			+ "                \r\n"
//    			+ "                <tr class=\"patient-info-row\">\r\n"
//    			+ "                    <td width=\"100%\">\r\n"
//    			+ "                        <span class=\"patient-label\">Diagnosis :</span> \r\n"
//    			+ "                        <span class=\"patient-value\">" + patient.getDiagnosis() + "</span>\r\n"
//    			+ "                    </td>\r\n"
//    			+ "                </tr>\r\n"
//    			+ "                <tr class=\"patient-info-row\">\r\n"
//    			+ "                    <td width=\"100%\">\r\n"
//    			+ "                        <span class=\"patient-label\">Case Taking :</span> \r\n"
//    			+ "                        <span class=\"patient-value\">" + patient.getCasetaking() + "</span>\r\n"
//    			+ "                    </td>\r\n"
//    			+ "                </tr>\r\n"
//    			+ "                <tr class=\"patient-info-row\">\r\n"
//    			+ "                    <td width=\"100%\">\r\n"
//    			+ "                        <span class=\"patient-label\">Next Appointment :</span> \r\n"
//    			+ "                        <span class=\"patient-value\">" + patient.getNextAppointment() + "</span>\r\n"
//    			+ "                    </td>\r\n"
//    			+ "                </tr>\r\n"
//    			+ "            </table>\r\n"
//    			+ "        </div>\r\n"
//    			+ "\r\n"
//    			+ "        \r\n"
//    			+ "        <div class=\"dashed-line\" style=\"margin-top: 0; margin-bottom: 20px;\"></div>\r\n"
//    			+ "\r\n"
//    			+ "        \r\n"
//    			+ "        <div class=\"main-content-area\" style=\"height: 500px;\">\r\n"
//    			+ "            \r\n"
//    			+ "        </div>\r\n"
//    			+ "\r\n"
//    			+ "        \r\n"
//    			+ "        \r\n"
//    			+ "        <div class=\"dashed-line\" style=\"margin-top: 10px; margin-bottom: 5px;\"></div>\r\n"
//    			+ "\r\n"
//    			+ "        \r\n"
//    			+ "        \r\n"
//    			+ "        \r\n"
//    			+ "        <table width=\"100%\" style=\"margin-bottom: 15px;\">\r\n" // Increased margin to lift it slightly before the footer
//    			+ "             <tr>\r\n"
//    			+ "                <td width=\"25%\" style=\"text-align: left;\">\r\n"
//    			+ "                    <span class=\"patient-label\">Date :</span> \r\n"
//    			+ "                    <span class=\"patient-value\">" + patient.getDate() + "</span>\r\n"
//    			+ "                </td>\r\n"
//    			+ "                <td width=\"75%\" style=\"text-align: right; font-weight: bold; font-size: 10pt;\">\r\n"
//    			+ "                    <p class=\"signature-line\" style=\"display: inline-block; margin: 0; padding-right: 0px;\">\r\n" // Apply the line style here
//    			+ "                       Dr. Pradeep tiwari\r\n"
//    			+ "                    </p>\r\n"
//    			+ "                </td>\r\n"
//    			+ "            </tr>\r\n"
//    			+ "        </table>\r\n"
//    			+ "        \r\n"
//    			+ "        \r\n"
//    			+ "        <div class=\"footer\">\r\n"
//    			+ "            For A Better Mind, Body, & Life.\r\n"
//    			+ "        </div>\r\n"
//    			+ "        \r\n"
//    			+ "        \r\n"
//    			+ "\r\n"
//    			+ "    </div>\r\n"
//    			+ "\r\n"
//    			+ "</body>\r\n"
//    			+ "</html>";
//    	String html = "\r\n"
//    			+ "<html lang=\"en\">\r\n"
//    			+ "<head>\r\n"
//    			+ "    <meta charset=\"UTF-8\" />\r\n"
//    			+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\r\n"
//    			+ "    <title>Chikitsa Patient Slip</title>\r\n"
//    			+ "    <style>\r\n"
//    			+ "        /* --- PDF Optimization for Single Page --- */\r\n"
//    			+ "        body {\r\n"
//    			+ "            font-family: Arial, sans-serif;\r\n"
//    			+ "            margin: 0;\r\n"
//    			+ "            padding: 20px; /* Page margin */\r\n"
//    			+ "            background-color: #fff; \r\n"
//    			+ "            font-size: 10pt; /* Default print size */\r\n"
//    			+ "        }\r\n"
//                + "        table { border-collapse: collapse; width: 100%; }\r\n"
//                + "        td { padding: 0; vertical-align: top; }\r\n"
//    			+ "\r\n"
//    			+ "        .slip-container {\r\n"
//    			+ "            width: 100%; \r\n"
//    			+ "            margin: 0 auto;\r\n"
//    			+ "            min-height: auto; \r\n"
//    			+ "            padding: 0;\r\n"
//                + "        }\r\n"
//    			+ "\r\n"
//    			+ "        /* Header Styles */\r\n"
//    			+ "        .header {\r\n"
//    			+ "            padding: 5px 0 10px 0; \r\n"
//    			+ "            border-bottom: 3px solid #000; /* Updated to 3px for a thicker line */\r\n"
//    			+ "            margin-bottom: 10px;\r\n"
//    			+ "            font-size: 10pt;\r\n"
//    			+ "        }\r\n"
//    			+ "        .doctor-info {\r\n"
//    			+ "            line-height: 1.3;\r\n"
//    			+ "            font-weight: bold;\r\n"
//    			+ "            padding-right: 10px;\r\n"
//    			+ "        }\r\n"
//    			+ "        .clinic-info {\r\n"
//    			+ "            text-align: right;\r\n"
//    			+ "            line-height: 1.3;\r\n"
//    			+ "            font-weight: normal; \r\n"
//    			+ "            font-size: 9pt;\r\n"
//    			+ "        }\r\n"
//    			+ "        .logo-section {\r\n"
//    			+ "            text-align: center;\r\n"
//    			+ "            padding-top: 5px;\r\n"
//    			+ "            line-height: 1;\r\n"
//    			+ "        }\r\n"
//    			+ "        .logo-section img { height: 25px; margin-bottom: 2px;}\r\n"
//    			+ "        .clinic-tagline { font-size: 8pt; color: #555;}\r\n"
//    			+ "\r\n"
//    			+ "        /* Patient Info Section */\r\n"
//    			+ "        .patient-info-section {\r\n"
//    			+ "            padding: 5px 0 10px 0;\r\n"
//    			+ "            line-height: 1.5;\r\n"
//    			+ "        }\r\n"
//    			+ "        .patient-info-row td {\r\n"
//    			+ "            padding-bottom: 3px; /* Add small vertical space between rows */\r\n"
//    			+ "        }\r\n"
//    			+ "        .patient-label {\r\n"
//    			+ "            font-weight: bold;\r\n"
//    			+ "            white-space: nowrap;\r\n"
//    			+ "            width: 120px; /* Consistent label width */\r\n"
//    			+ "            padding-right: 5px;\r\n"
//    			+ "        }\r\n"
//    			+ "        .patient-value { font-style: italic; }\r\n"
//    			+ "\r\n"
//    			+ "        /* Main Content Area */\r\n"
//    			+ "        .main-content-area {\r\n"
//    			+ "            padding: 10px 0;\r\n"
//    			+ "            /* Fixed height for content area below dashed line */\r\n"
//    			+ "            height: 500px;\r\n"
//    			+ "        }\r\n"
//    			+ "        .casetaking-text {\r\n"
//    			+ "            padding-left: 10px;\r\n"
//    			+ "            font-style: italic;\r\n"
//    			+ "            display: block;\r\n"
//    			+ "            margin-bottom: 15px;\r\n"
//    			+ "        }\r\n"
//                + "        /* Footer Styles */\r\n"
//    			+ "        .footer {\r\n"
//    			+ "            text-align: center;\r\n"
//    			+ "            background-color: #0066cc; \r\n"
//    			+ "            color: #fff;\r\n"
//    			+ "            padding: 3px 0;\r\n"
//    			+ "            font-size: 8pt;\r\n"
//    			+ "            font-weight: 500;\r\n"
//    			+ "            text-transform: uppercase;\r\n"
//    			+ "        }\r\n"
//    			+ "        .dashed-line {\r\n"
//    			+ "             margin-top: 10px;\r\n"
//    			+ "             margin-bottom: 10px;\r\n"
//    			+ "             border-top: 1px dashed #ccc;\r\n"
//    			+ "             height: 1px;\r\n"
//    			+ "             clear: both;\r\n"
//    			+ "        }\r\n"
//    			+ "        .signature-line {\r\n"
//                + "            border-top: 1px solid #000; \r\n"
//                + "            padding-top: 5px; \r\n"
//                + "            margin-top: 5px;\r\n"
//                + "        }\r\n"
//    			+ "\r\n"
//    			+ "    </style>\r\n"
//    			+ "</head>\r\n"
//    			+ "<body>\r\n"
//    			+ "\r\n"
//    			+ "    <div class=\"slip-container\">\r\n"
//    			+ "        <div class=\"header\">\r\n"
//    			+ "            <table width=\"100%\">\r\n"
//    			+ "                <tr>\r\n"
//    			+ "                    <td width=\"45%\" class=\"doctor-info\">\r\n"
//    			+ "                        Dr.  Pradeep tiwari<br />\r\n"
//    			+ "                        Consulting Homeopath & General Physician<br />\r\n"
//    			+ "                        B.H.M.S, D.N.H.E., C.G.O.<br />\r\n"
//    			+ "                        Mob: +91 8878547608\r\n"
//    			+ "                    </td>\r\n"
//    			+ "                    <td width=\"30%\" class=\"logo-section\">\r\n"
//    			+ "                        <img src=\"/images/chikitsa.png\" alt=\"Chikitsa Logo\" /> \r\n"
//    			+ "                        <div style=\"font-size: 6pt; color: #777;\">health care for sure</div>\r\n"
//    			+ "                        <div class=\"clinic-tagline\">FAMILY WELLNESS CLINIC</div>\r\n"
//    			+ "                    </td>\r\n"
//    			+ "                </tr>\r\n"
//    			+ "            </table>\r\n"
//    			+ "        </div>\r\n"
//    			+ "\r\n"
//    			+ "<div style=\"border-bottom: 3px solid black; margin: 10px 0;\"></div>"
//    			+ "\r\n"
//    			+ "        <div class=\"patient-info-section\">\r\n"
//    			+ "            <table width=\"100%\">\r\n"
//    			+ "                \r\n"
//    			+ "                <tr class=\"patient-info-row\">\r\n"
//    			+ "                    <td width=\"100%\">\r\n"
//    			+ "                        <span class=\"patient-label\">Reg. No. :</span> \r\n"
//    			+ "                        <span class=\"patient-value\">" + patient.getRegistrationNumber() + "</span>\r\n"
//    			+ "                    </td>\r\n"
//    			+ "                </tr>\r\n"
//    			+ "                \r\n"
//    			+ "                <tr class=\"patient-info-row\">\r\n"
//    			+ "                    <td width=\"100%\">\r\n"
//    			+ "                        <span class=\"patient-label\">Name :</span> \r\n"
//    			+ "                        <span class=\"patient-value\">" + patient.getFullName() + "</span>\r\n"
//    			+ "                    </td>\r\n"
//    			+ "                </tr>\r\n"
//    			+ "                \r\n"
//    			+ "                <tr class=\"patient-info-row\">\r\n"
//    			+ "                    <td width=\"100%\">\r\n"
//    			+ "                        <span class=\"patient-label\">Father's Name :</span> \r\n"
//    			+ "                        <span class=\"patient-value\">" + patient.getFatherName() + "</span>\r\n"
//    			+ "                    </td>\r\n"
//    			+ "                </tr>\r\n"
//    			+ "                \r\n"
//    			+ "                <tr class=\"patient-info-row\">\r\n"
//    			+ "                    <td width=\"100%\">\r\n"
//    			+ "                        <span class=\"patient-label\">Age/Gender/Religion :</span> \r\n"
//    			+ "                        <span class=\"patient-value\">"+ patient.getAge() + "/" + patient.getSex() + "/" + patient.getReligion() +"</span>\r\n"
//    			+ "                    </td>\r\n"
//    			+ "                </tr>\r\n"
//    			+ "                \r\n"
//    			+ "                <tr class=\"patient-info-row\">\r\n"
//    			+ "                    <td width=\"100%\">\r\n"
//    			+ "                        <span class=\"patient-label\">Address :</span> \r\n"
//    			+ "                        <span class=\"patient-value\">" + patient.getAddress() + "</span>\r\n"
//    			+ "                    </td>\r\n"
//    			+ "                </tr>\r\n"
//    			+ "                \r\n"
//    			+ "                <tr class=\"patient-info-row\">\r\n"
//    			+ "                    <td width=\"100%\">\r\n"
//    			+ "                        <span class=\"patient-label\">Diagnosis :</span> \r\n"
//    			+ "                        <span class=\"patient-value\">" + patient.getDiagnosis() + "</span>\r\n"
//    			+ "                    </td>\r\n"
//    			+ "                </tr>\r\n"
//    			+ "                <tr class=\"patient-info-row\">\r\n"
//    			+ "                    <td width=\"100%\">\r\n"
//    			+ "                        <span class=\"patient-label\">Case Taking :</span> \r\n"
//    			+ "                        <span class=\"patient-value\">" + patient.getCasetaking() + "</span>\r\n"
//    			+ "                    </td>\r\n"
//    			+ "                </tr>\r\n"
//    			+ "                <tr class=\"patient-info-row\">\r\n"
//    			+ "                    <td width=\"100%\">\r\n"
//    			+ "                        <span class=\"patient-label\">Next Appointment :</span> \r\n"
//    			+ "                        <span class=\"patient-value\">" + patient.getNextAppointment() + "</span>\r\n"
//    			+ "                    </td>\r\n"
//    			+ "                </tr>\r\n"
//    			+ "            </table>\r\n"
//    			+ "        </div>\r\n"
//    			+ "\r\n"
//    			+ "        \r\n"
//    			+ "        <div class=\"dashed-line\" style=\"margin-top: 0; margin-bottom: 20px;\"></div>\r\n"
//    			+ "\r\n"
//    			+ "        \r\n"
//    			+ "        <div class=\"main-content-area\" style=\"height: 500px;\">\r\n"
//    			+ "            \r\n"
//    			+ "        </div>\r\n"
//    			+ "\r\n"
//    			+ "        \r\n"
//    			+ "        \r\n"
//    			+ "        <div class=\"dashed-line\" style=\"margin-top: 10px; margin-bottom: 5px;\"></div>\r\n"
//    			+ "\r\n"
//    			+ "        \r\n"
//    			+ "        \r\n"
//    			+ "        \r\n"
//    			+ "        <table width=\"100%\" style=\"margin-bottom: 15px;\">\r\n" 
//    			+ "             <tr>\r\n"
//    			+ "                <td width=\"25%\" style=\"text-align: left;\">\r\n"
//    			+ "                    <span class=\"patient-label\">Date :</span> \r\n"
//    			+ "                    <span class=\"patient-value\">" + patient.getDate() + "</span>\r\n"
//    			+ "                </td>\r\n"
//    			+ "                <td width=\"75%\" style=\"text-align: right; font-weight: bold; font-size: 10pt;\">\r\n"
//    			+ "                    <p class=\"signature-line\" style=\"display: inline-block; margin: 0; padding-right: 0px;\">\r\n" 
//    			+ "                       Dr. Pradeep tiwari\r\n"
//    			+ "                    </p>\r\n"
//    			+ "                </td>\r\n"
//    			+ "            </tr>\r\n"
//    			+ "        </table>\r\n"
//    			+ "        \r\n"
//    			+ "        \r\n"
//    			+ "        <div class=\"footer\">\r\n"
//    			+ "            For A Better Mind, Body, & Life.\r\n"
//    			+ "        </div>\r\n"
//    			+ "        \r\n"
//    			+ "        \r\n"
//    			+ "\r\n"
//    			+ "    </div>\r\n"
//    			+ "\r\n"
//    			+ "</body>\r\n"
//    			+ "</html>";
    	
    	// ... inside a service or controller method
    	try {
    	    // This finds the file relative to the 'resources' folder
    	    ClassPathResource resource = new ClassPathResource("static/images/chikitsa.png");
    	    
    	    // Use the InputStream to read the image data
    	    InputStream inputStream = resource.getInputStream();
    	    
    	    // Now you can process the image data (e.g., read bytes, Base64 encode, etc.)
    	} catch (IOException e) {
    	    // Handle error if file not found
    	    e.printStackTrace();
    	}
    	
    	String html = "\r\n"
    			+ "<html lang=\"en\">\r\n"
    			+ "<head>\r\n"
    			+ "    <meta charset=\"UTF-8\" />\r\n"
    			+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\r\n"
    			+ "    <title>Chikitsa Patient Slip</title>\r\n"
    			+ "    <style>\r\n"
    			+ "        /* --- PDF Optimization for Single Page --- */\r\n"
    			+ "        body {\r\n"
    			+ "            font-family: Arial, sans-serif;\r\n"
    			+ "            margin: 0;\r\n"
    			+ "            padding: 20px; /* Page margin */\r\n"
    			+ "            background-color: #fff; \r\n"
    			+ "            font-size: 10pt; /* Default print size */\r\n"
    			+ "        }\r\n"
                + "        table { border-collapse: collapse; width: 100%; }\r\n"
                + "        td { padding: 0; vertical-align: top; }\r\n"
    			+ "\r\n"
    			+ "        .slip-container {\r\n"
    			+ "            width: 100%; \r\n"
    			+ "            margin: 0 auto;\r\n"
    			+ "            min-height: auto; \r\n"
    			+ "            padding: 0;\r\n"
                + "        }\r\n"
    			+ "\r\n"
    			+ "        /* Header Styles */\r\n"
    			+ "        .header {\r\n"
    			+ "            padding: 5px 0 10px 0; \r\n"
    			+ "            border-bottom: 3px solid #000; /* THICK LINE IS HERE */\r\n"
    			+ "            margin-bottom: 10px;\r\n"
    			+ "            font-size: 10pt;\r\n"
    			+ "        }\r\n"
    			+ "        .doctor-info {\r\n"
    			+ "            line-height: 1.3;\r\n"
    			+ "            font-weight: bold;\r\n"
    			+ "            padding-right: 10px;\r\n"
    			+ "        }\r\n"
    			+ "        .clinic-info {\r\n"
    			+ "            text-align: right;\r\n"
    			+ "            line-height: 1.3;\r\n"
    			+ "            font-weight: normal; \r\n"
    			+ "            font-size: 9pt;\r\n"
    			+ "        }\r\n"
    			+ "        .logo-section {\r\n"
    			+ "            text-align: center;\r\n"
    			+ "            padding-top: 5px;\r\n"
    			+ "            line-height: 1;\r\n"
    			+ "        }\r\n"
    			+ "        .logo-section img { height: 25px; margin-bottom: 2px;}\r\n"
    			+ "        .clinic-tagline { font-size: 8pt; color: #555;}\r\n"
    			+ "\r\n"
    			+ "        /* Patient Info Section */\r\n"
    			+ "        .patient-info-section {\r\n"
    			+ "            padding: 5px 0 10px 0;\r\n"
    			+ "            line-height: 1.5;\r\n"
    			+ "        }\r\n"
    			+ "        .patient-info-row td {\r\n"
    			+ "            padding-bottom: 3px; /* Add small vertical space between rows */\r\n"
    			+ "        }\r\n"
    			+ "        .patient-label {\r\n"
    			+ "            font-weight: bold;\r\n"
    			+ "            white-space: nowrap;\r\n"
    			+ "            width: 120px; /* Consistent label width */\r\n"
    			+ "            padding-right: 5px;\r\n"
    			+ "        }\r\n"
    			+ "        .patient-value { font-style: italic; }\r\n"
    			+ "\r\n"
    			+ "        /* Main Content Area */\r\n"
    			+ "        .main-content-area {\r\n"
    			+ "            padding: 10px 0;\r\n"
    			+ "            /* Fixed height for content area below dashed line */\r\n"
    			+ "            height: 500px;\r\n"
    			+ "        }\r\n"
    			+ "        .casetaking-text {\r\n"
    			+ "            padding-left: 10px;\r\n"
    			+ "            font-style: italic;\r\n"
    			+ "            display: block;\r\n"
    			+ "            margin-bottom: 15px;\r\n"
    			+ "        }\r\n"
                + "        /* Footer Styles */\r\n"
    			+ "        .footer {\r\n"
    			+ "            text-align: center;\r\n"
    			+ "            background-color: #0066cc; \r\n"
    			+ "            color: #fff;\r\n"
    			+ "            padding: 3px 0;\r\n"
    			+ "            font-size: 8pt;\r\n"
    			+ "            font-weight: 500;\r\n"
    			+ "            text-transform: uppercase;\r\n"
    			+ "        }\r\n"
    			+ "        .dashed-line {\r\n"
    			+ "             margin-top: 10px;\r\n"
    			+ "             margin-bottom: 10px;\r\n"
    			+ "             border-top: 1px dashed #ccc;\r\n"
    			+ "             height: 1px;\r\n"
    			+ "             clear: both;\r\n"
    			+ "        }\r\n"
    			+ "        .signature-line {\r\n"
                + "            border-top: 1px solid #000; \r\n"
                + "            padding-top: 5px; \r\n"
                + "            margin-top: 5px;\r\n"
                + "        }\r\n"
    			+ "\r\n"
    			+ "    </style>\r\n"
    			+ "</head>\r\n"
    			+ "<body>\r\n"
    			+ "\r\n"
    			+ "    <div class=\"slip-container\">\r\n"
    			+ "        <div class=\"header\">\r\n"
    			+ "            <table width=\"100%\">\r\n"
    			+ "                <tr>\r\n"
    			+ "                    <td width=\"45%\" class=\"doctor-info\">\r\n"
    			+ "                        Dr. Pradeep tiwari <br/>\r\n"
    			+ "                        Consulting Homeopath & General Physician <br/>\r\n"
    			+ "                        B.H.M.S, D.N.H.E., C.G.O.<br/>\r\n"
    			+ "                        Mob: +91 8878547608<br/>\r\n"
    			+ "                        Date: " +  patient.getDate() + "\r\n"
    			+ "                    </td>\r\n"
    			+ "                    <td width=\"10%\" class=\"logo-section\">\r\n"
    			+ "                        <img src=\"/static/images/chikitsa.png\" alt=\"Chikitsa Logo\" /> \r\n"
    			+ "                        <div style=\"font-size: 6pt; color: #777;\">health care for sure</div>\r\n"
    			+ "                        <div class=\"clinic-tagline\">FAMILY WELLNESS CLINIC</div>\r\n"
    			+ "                    </td>\r\n"
    			+ "                </tr>\r\n"
    			+ "            </table>\r\n"
    			+ "        </div>\r\n"
    			+ "\r\n"
    			+ "        <div class=\"patient-info-section\">\r\n"
    			+ "            <table width=\"100%\">\r\n"
    			+ "                \r\n"
    			+ "                <tr class=\"patient-info-row\">\r\n"
    			+ "                    <td width=\"100%\">\r\n"
    			+ "                        <span class=\"patient-label\">Reg. No. :</span> \r\n"
    			+ "                        <span class=\"patient-value\">" + patient.getRegistrationNumber() + "</span>\r\n"
    			+ "                    </td>\r\n"
    			+ "                </tr>\r\n"
    			+ "                \r\n"
    			+ "                <tr class=\"patient-info-row\">\r\n"
    			+ "                    <td width=\"100%\">\r\n"
    			+ "                        <span class=\"patient-label\">Name :</span> \r\n"
    			+ "                        <span class=\"patient-value\">" + patient.getFullName() + "</span>\r\n"
    			+ "                    </td>\r\n"
    			+ "                </tr>\r\n"
    			+ "                \r\n"
    			+ "                <tr class=\"patient-info-row\">\r\n"
    			+ "                    <td width=\"100%\">\r\n"
    			+ "                        <span class=\"patient-label\">Father's Name :</span> \r\n"
    			+ "                        <span class=\"patient-value\">" + patient.getFatherName() + "</span>\r\n"
    			+ "                    </td>\r\n"
    			+ "                </tr>\r\n"
    			+ "                \r\n"
    			+ "                <tr class=\"patient-info-row\">\r\n"
    			+ "                    <td width=\"100%\">\r\n"
    			+ "                        <span class=\"patient-label\">Age/Gender/Religion :</span> \r\n"
    			+ "                        <span class=\"patient-value\">"+ patient.getAge() + "/" + patient.getSex() + "/" + patient.getReligion() +"</span>\r\n"
    			+ "                    </td>\r\n"
    			+ "                </tr>\r\n"
    			+ "                \r\n"
    			+ "                <tr class=\"patient-info-row\">\r\n"
    			+ "                    <td width=\"100%\">\r\n"
    			+ "                        <span class=\"patient-label\">Address :</span> \r\n"
    			+ "                        <span class=\"patient-value\">" + patient.getAddress() + "</span>\r\n"
    			+ "                    </td>\r\n"
    			+ "                </tr>\r\n"
    			+ "                \r\n"
    			+ "                <tr class=\"patient-info-row\">\r\n"
    			+ "                    <td width=\"100%\">\r\n"
    			+ "                        <span class=\"patient-label\">Diagnosis :</span> \r\n"
    			+ "                        <span class=\"patient-value\">" + patient.getDiagnosis() + "</span>\r\n"
    			+ "                    </td>\r\n"
    			+ "                </tr>\r\n"
    			+ "                <tr class=\"patient-info-row\">\r\n"
    			+ "                    <td width=\"100%\">\r\n"
    			+ "                        <span class=\"patient-label\">Case Taking :</span> \r\n"
    			+ "                        <span class=\"patient-value\">" + patient.getCasetaking() + "</span>\r\n"
    			+ "                    </td>\r\n"
    			+ "                </tr>\r\n"
    			+ "                <tr class=\"patient-info-row\">\r\n"
    			+ "                    <td width=\"100%\">\r\n"
    			+ "                        <span class=\"patient-label\">Next Appointment :</span> \r\n"
    			+ "                        <span class=\"patient-value\">" + patient.getNextAppointment() + "</span>\r\n"
    			+ "                    </td>\r\n"
    			+ "                </tr>\r\n"
    			+ "            </table>\r\n"
    			+ "        </div>\r\n"
//    			+ "\r\n"
//    			+ "        \r\n"
//    			+ "        <div class=\"dashed-line\" style=\"margin-top: 0; margin-bottom: 20px;\"></div>\r\n"
//    			+ "\r\n"
//    			+ "        \r\n"
//    			+ "        <div class=\"main-content-area\" style=\"height: 500px;\">\r\n"
//    			+ "            \r\n"
//    			+ "        </div>\r\n"
//    			+ "\r\n"
//    			+ "        \r\n"
//    			+ "        \r\n"
//    			+ "        <div class=\"dashed-line\" style=\"margin-top: 10px; margin-bottom: 5px;\"></div>\r\n"
//    			+ "\r\n"
//    			+ "        \r\n"
//    			+ "        \r\n"
//    			+ "        \r\n"
//    			+ "        <table width=\"100%\" style=\"margin-bottom: 15px;\">\r\n" 
//    			+ "             <tr>\r\n"
//    			+ "                <td width=\"25%\" style=\"text-align: left;\">\r\n"
//    			+ "                    <span class=\"patient-label\">Date :</span> \r\n"
//    			+ "                    <span class=\"patient-value\">" + patient.getDate() + "</span>\r\n"
//    			+ "                </td>\r\n"
//    			+ "                <td width=\"75%\" style=\"text-align: right; font-weight: bold; font-size: 10pt;\">\r\n"
//    			+ "                    <p class=\"signature-line\" style=\"display: inline-block; margin: 0; padding-right: 0px;\">\r\n" 
//    			+ "                       Dr. Pradeep tiwari\r\n"
//    			+ "                    </p>\r\n"
//    			+ "                </td>\r\n"
//    			+ "            </tr>\r\n"
//    			+ "        </table>\r\n"
//    			+ "        \r\n"
//    			+ "        \r\n"
//    			+ "        <div class=\"footer\">\r\n"
//    			+ "            For A Better Mind, Body, & Life.\r\n"
//    			+ "        </div>\r\n"
//    			+ "        \r\n"
//    			+ "        \r\n"
//    			+ "\r\n"
    			+ "    </div>\r\n"
    			+ "\r\n"
    			+ "</body>\r\n"
    			+ "</html>";
    	return html;
    	
    }

}