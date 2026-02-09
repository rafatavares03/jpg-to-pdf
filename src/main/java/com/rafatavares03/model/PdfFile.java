package com.rafatavares03.model;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

import java.io.File;
import java.io.IOException;
import java.util.Queue;

public class PdfFile {
    private final File directory;
    private final String fileName;
    private final PDDocument file;

    public PdfFile(File directory, String fileName) {
        this.directory = directory;
        this.fileName = (fileName.endsWith(".pdf")) ? fileName.replace(".pdf", "") : fileName;
        this.file = new PDDocument();
    }

    private void save() {
        File pdfFile = new File(directory, fileName + ".pdf");
        try{
            file.save(pdfFile);
            file.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void addPages(Queue<PDPage> pages) {
        while(!pages.isEmpty()) {
            file.addPage(pages.remove());
        }
    }

    public void generate() {
        save();
    }

    public PDDocument getFile() {
        return this.file;
    }
}
