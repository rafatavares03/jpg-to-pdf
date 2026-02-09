package com.rafatavares03;
import com.rafatavares03.controller.ScreenController;
import java.io.IOException;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) throws IOException {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        ScreenController screenController = new ScreenController(primaryStage);
    }
}