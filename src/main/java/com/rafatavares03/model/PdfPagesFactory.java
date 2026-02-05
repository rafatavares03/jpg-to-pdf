package com.rafatavares03.model;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class PdfPagesFactory {
    public static Queue<PDPage> createPages(PDDocument pdfFile, Queue<File> images) throws IOException {
        Queue<PDPage> pages = new LinkedList<>();
        while(!images.isEmpty()) {
            File image = images.remove();
            System.out.println(image.getAbsolutePath());
            PDImageXObject imageXObject = PDImageXObject.createFromFileByExtension(image, pdfFile);

            PDPage newPage = new PDPage();
            drawImage(new PDPageContentStream(pdfFile, newPage), imageXObject);
            pages.add(newPage);
        }
        return pages;
    }

    private static void drawImage(PDPageContentStream contentStream, PDImageXObject imageXObject) throws IOException {
        contentStream.drawImage(imageXObject, 0, 0, 300, 300);
        contentStream.close();
    }
}
