package org.example;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

public class Main {
    public static void main(String[] args) throws IOException {
        try{
            File directory = new File(args[0]);
            FilenameFilter filter = new FilenameFilter() {
                @Override
                public boolean accept(File directory, String fileName) {
                    return fileName.endsWith(".jpg") || fileName.endsWith(".jpge");
                }
            };
            File files[] = directory.listFiles(filter);
            PDF pdf = new PDF(directory.getAbsolutePath(), args[1]);
            for(int i = 0; i < files.length; i++) {
                pdf.addImagePage(files[i].getAbsolutePath());
            }

            System.out.println("PDF saved");
            pdf.close();

        } catch(Exception e){
            System.out.println("Archive path most be wrong or invalid. Make sure theres no spaces in the directory path.");
        }

    }
}