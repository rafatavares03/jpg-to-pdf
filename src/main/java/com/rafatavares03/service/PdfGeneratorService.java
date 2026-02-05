package com.rafatavares03.service;

import com.rafatavares03.model.ImageLoader;
import com.rafatavares03.model.PdfFile;
import com.rafatavares03.model.PdfPagesFactory;
import org.apache.pdfbox.pdmodel.PDPage;

import java.io.File;
import java.util.Queue;

public class PdfGeneratorService {
    private ImageLoader imageLoader;

    public PdfGeneratorService() {
        this.imageLoader = new ImageLoader();
    }

    public boolean generateSinglePdfFromDirectory(File inputDirectory, File outputDirectory)  {
        if(!inputDirectory.isDirectory() || !outputDirectory.isDirectory()) {
            String invalidDirectory = "Select a valid " + ((!inputDirectory.isDirectory()) ? "input" : "output") + "directory.";
            throw new RuntimeException("Invalid directory." + invalidDirectory);
        }

        PdfFile pdf = new PdfFile(outputDirectory, "teste");
        try {
            Queue<File> images = imageLoader.loadImages(inputDirectory);
            Queue<PDPage> pages = PdfPagesFactory.createPages(pdf.getFile(), images);
            pdf.addPages(pages);
            pdf.generate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return true;
    }
}
