/**
 * This example was written by Bruno Lowagie in answer to the following question:
 * http://stackoverflow.com/questions/26752663/adding-maps-at-itext-java
 */
package com.itextpdf.samples.sandbox.objects;

import com.itextpdf.basics.geom.PageSize;
import com.itextpdf.basics.image.ImageFactory;
import com.itextpdf.canvas.PdfCanvas;
import com.itextpdf.core.color.Color;
import com.itextpdf.core.pdf.PdfDocument;
import com.itextpdf.core.pdf.PdfWriter;
import com.itextpdf.core.testutils.annotations.type.SampleTest;
import com.itextpdf.model.Document;
import com.itextpdf.model.element.Image;
import com.itextpdf.samples.GenericTest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.junit.experimental.categories.Category;

@Category(SampleTest.class)
public class AddPointer extends GenericTest {
    public static final String DEST = "./target/test/resources/sandbox/objects/add_pointer.pdf";
    public static final String IMG = "./src/test/resources/sandbox/objects/map_cic.png";

    public static void main(String[] args) throws Exception {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new AddPointer().manipulatePdf(DEST);
    }

    public void manipulatePdf(String dest) throws IOException {
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(new FileOutputStream(dest)));
        Image img = new Image(ImageFactory.getImage(IMG));
        Document document = new Document(pdfDoc, new PageSize(img.getImageWidth(), img.getImageHeight()));

        img.setFixedPosition(0, 0);
        document.add(img);
        PdfCanvas canvas = new PdfCanvas(pdfDoc.getLastPage());
        canvas.setStrokeColor(Color.RED);
        canvas.setLineWidth(3);
        canvas.moveTo(220, 330);
        canvas.lineTo(240, 370);
        canvas.arc(200, 350, 240, 390, 0, (float) 180);
        canvas.lineTo(220, 330);
        canvas.closePathStroke();
        canvas.setFillColor(Color.RED);
        canvas.circle(220, 370, 10);
        canvas.fill();

        document.close();
    }
}