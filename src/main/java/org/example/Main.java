package org.example;
import org.example.PDF.PDF;
import org.example.input.InputImages;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        try{
            File directory = new File(args[0]);
            InputImages images = new InputImages();
            images.addImages(new File(args[0]));
            PDF pdf = new PDF(directory.getAbsolutePath(), args[1]);
            int i = 0;
            while(!images.getQueue().isEmpty()){
                pdf.addImagePage(images.getQueue().remove().getAbsolutePath());
                System.out.println(i);
                i++;
            }
            pdf.generate();
            System.out.println("PDF saved");
            pdf.close();

        } catch(Exception e){
            System.out.println("Archive path most be wrong or invalid. Make sure theres no spaces in the directory path.");
            System.out.println(e);
        }

    }
}