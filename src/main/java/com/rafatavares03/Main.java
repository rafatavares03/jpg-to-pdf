package com.rafatavares03;
import com.rafatavares03.PDF.PDF;
import com.rafatavares03.controller.ScreenController;
import com.rafatavares03.input.InputImages;

import java.io.File;
import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) throws IOException {
        launch(args);
        /*
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
        */

    }

    @Override
    public void start(Stage primaryStage) {
        ScreenController screenController = new ScreenController(primaryStage);
    }
}