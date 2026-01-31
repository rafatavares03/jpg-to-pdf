package com.rafatavares03.service;

import com.rafatavares03.model.ImageLoader;
import com.rafatavares03.model.PdfGenerator;

import java.io.File;
import java.util.Queue;

public class PdfGeneratorService {
    private PdfGenerator pdfGenerator;
    private ImageLoader imageLoader;

    public PdfGeneratorService() {
        this.pdfGenerator = new PdfGenerator();
        this.imageLoader = new ImageLoader();
    }

    public boolean generateSinglePdfFromDirectory(File inputDirectory, File outputDirectory) {
        if(!inputDirectory.isDirectory() || !outputDirectory.isDirectory()) {
            String invalidDirectory = "Select a valid " + ((!inputDirectory.isDirectory()) ? "input" : "output") + "directory.";
            throw new RuntimeException("Invalid directory." + invalidDirectory);
        }

        Queue<File> images = imageLoader.loadImages(inputDirectory);

        return true;
    }
}
