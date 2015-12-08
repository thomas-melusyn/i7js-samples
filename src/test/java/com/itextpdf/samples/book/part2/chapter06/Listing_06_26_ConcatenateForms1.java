package com.itextpdf.samples.book.part2.chapter06;

import com.itextpdf.core.pdf.PdfDocument;
import com.itextpdf.core.pdf.PdfReader;
import com.itextpdf.core.pdf.PdfWriter;
import com.itextpdf.core.testutils.annotations.type.SampleTest;
import com.itextpdf.forms.PdfPageFormCopier;
import com.itextpdf.samples.GenericTest;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;

import org.junit.experimental.categories.Category;

@Category(SampleTest.class)
public class Listing_06_26_ConcatenateForms1 extends GenericTest {
    public static final String DATASHEET =
            "./src/test/resources/pdfs/datasheet.pdf";
    public static final String DEST =
            "./target/test/resources/book/part2/chapter06/Listing_06_26_ConcatenateForms1.pdf";

    public static void main(String[] args)
            throws SQLException, IOException {
        new Listing_06_26_ConcatenateForms1().manipulatePdf(DEST);
    }

    @Override
    public void manipulatePdf(String dest) throws SQLException, IOException {
        // Create the result document
        FileOutputStream fos = new FileOutputStream(DEST);
        PdfWriter writer = new PdfWriter(fos);
        PdfDocument pdfDoc = new PdfDocument(writer);

        // Create the first source document
        FileInputStream fis = new FileInputStream(DATASHEET);
        PdfReader reader = new PdfReader(fis);
        PdfDocument srcDoc = new PdfDocument(reader);

        srcDoc.copyPages(1, 1, pdfDoc, new PdfPageFormCopier());
        srcDoc.close();

        // Create the second source document
        fis = new FileInputStream(DATASHEET);
        reader = new PdfReader(fis);
        srcDoc = new PdfDocument(reader);

        srcDoc.copyPages(1, 1, pdfDoc, new PdfPageFormCopier());
        srcDoc.close();

        pdfDoc.close();
    }
}
