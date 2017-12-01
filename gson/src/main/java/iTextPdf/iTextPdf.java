package iTextPdf;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Arrays;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class iTextPdf {

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 * @throws DocumentException
	 */
	public static void main(String[] args) throws FileNotFoundException, DocumentException {

		// Instantiation of document object
		Document document = new Document(PageSize.A4, 50, 50, 50, 50);

		// Creation of PdfWriter object
		@SuppressWarnings("unused")
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("sampleFile.pdf"));
		document.open();

		// Creation of chapter object and main heading
		Paragraph mainTitle = new Paragraph("Document Reprint - Scanned Jobs", 
				 FontFactory.getFont(FontFactory.COURIER, 18, Font.BOLD));
		document.add(mainTitle);
		
		// Creation of section object with timestamp and userID
		Paragraph subTitle = new Paragraph("Scanned on 03/11/2017 @ 14:43:39 by celodwig",
				FontFactory.getFont(FontFactory.COURIER, 16, Font.BOLD));
		subTitle.setSpacingBefore(20);
		subTitle.setSpacingAfter(20);
		document.add(subTitle);

		// Creation of reprint docs list
		List<String> list = Arrays.asList("1398734010 01552 (Single)", 
        		"1398734010 01553 (Single)",
        		"1398734010       (Batch)");
        for (String item : list) {
        	document.add(new Paragraph(item));
        }

		// Close the main document
		document.close();
	}
}
