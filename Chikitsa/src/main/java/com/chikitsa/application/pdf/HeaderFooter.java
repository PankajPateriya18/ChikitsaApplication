package com.chikitsa.application.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;


public class HeaderFooter extends PdfPageEventHelper {

    Image logo;

    @Override
    public void onStartPage(PdfWriter writer, Document document) {
        try {
            logo = Image.getInstance("src/main/resources/static/images/chikitsa_logo.png");

            // Header Logo
            logo.scaleToFit(120, 60);
            logo.setAbsolutePosition(document.left(), document.top() + 10);
            writer.getDirectContent().addImage(logo);

            // Top right clinic details
            ColumnText.showTextAligned(writer.getDirectContent(),
                    Element.ALIGN_RIGHT,
                    new Phrase("Mob: 9876543210\nPune, India", new Font(Font.FontFamily.HELVETICA, 10)),
                    document.right(), document.top() + 40, 0);

            // ✅ Watermark (center big faint logo)
            Image watermark = Image.getInstance("src/main/resources/static/images/chikitsa_logo.png");
            watermark.scaleToFit(300, 300);
            watermark.setAbsolutePosition(
                    (document.right() - document.left()) / 2,
                    (document.top() - document.bottom()) / 2 - 100
            );
            watermark.setTransparency(new int[]{80, 80});
            writer.getDirectContentUnder().addImage(watermark);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void onEndPage(PdfWriter writer, Document document) {

        // Footer center text
        ColumnText.showTextAligned(writer.getDirectContent(),
                Element.ALIGN_CENTER,
                new Phrase("For A Better Mind, Body & Life", 
                   new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD)),
                (document.right() - document.left()) / 2 + document.leftMargin(),
                document.bottom() - 10, 0);
    }
}