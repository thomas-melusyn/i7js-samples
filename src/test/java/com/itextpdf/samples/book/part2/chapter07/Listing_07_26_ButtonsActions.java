/*

    This file is part of the iText (R) project.
    Copyright (c) 1998-2016 iText Group NV

*/

package com.itextpdf.samples.book.part2.chapter07;

import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.action.PdfAction;
import com.itextpdf.kernel.pdf.annot.PdfAnnotation;
import com.itextpdf.test.annotations.type.SampleTest;
import com.itextpdf.forms.fields.PdfButtonFormField;
import com.itextpdf.forms.fields.PdfFormField;
import com.itextpdf.samples.GenericTest;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.experimental.categories.Category;

@Category(SampleTest.class)
public class Listing_07_26_ButtonsActions extends GenericTest {
    public static final String DEST = "./target/test/resources/book/part2/chapter07/Listing_07_26_ButtonsActions.pdf";

    public static final String MOVIE_TEMPLATES = "./src/test/resources/book/part1/chapter03/cmp_Listing_03_29_MovieTemplates.pdf";

    protected String[] arguments;

    public static void main(String args[]) throws IOException, SQLException {
        Listing_07_26_ButtonsActions application = new Listing_07_26_ButtonsActions();
        application.arguments = args;
        application.manipulatePdf(DEST);
    }

    public void manipulatePdf(String dest) throws IOException, SQLException {
        // Listing_03_29_MovieTemplates.main(arguments);
        // Create a reader
        PdfDocument pdfDoc = new PdfDocument(new PdfReader(MOVIE_TEMPLATES), new PdfWriter(DEST));
        PdfButtonFormField saveAs =
                PdfFormField.createPushButton(pdfDoc, new Rectangle(636, 10, 716 - 636, 30 - 10), "Save", "Save");
        saveAs.setColor(Color.RED);
        PdfAnnotation saveAsButton = saveAs.getWidgets().get(0);
        saveAs.setAction(PdfAction.createJavaScript("app.execMenuItem('SaveAs')"));

        PdfButtonFormField mail =
                PdfFormField.createPushButton(pdfDoc, new Rectangle(736, 10, 816 - 736, 30 - 10), "Mail", "Mail");
        mail.setColor(Color.RED);
        PdfAnnotation mailButton = mail.getWidgets().get(0);
        mailButton.setAction(PdfAction.createJavaScript("app.execMenuItem('AcroSendMail:SendMail')"));
        // Add the annotations to every page of the document
        for (int page = 1; page <= pdfDoc.getNumberOfPages(); page++) {
            pdfDoc.getPage(page).addAnnotation(saveAsButton);
            pdfDoc.getPage(page).addAnnotation(mailButton);
        }
        // Close the pdfDocument
        pdfDoc.close();
    }
}
