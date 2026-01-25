package com.rafatavares03.controller;

import com.rafatavares03.view.HomeScreen;
import com.rafatavares03.view.Screen;
import javafx.stage.Stage;


public class ScreenController {
    private Stage window;
    private ScreenType currentScreen;

    public ScreenController(Stage stage) {
        this.window = stage;
        currentScreen = ScreenType.HOME;
        Screen homeScreen = new HomeScreen();
        window.setTitle("Image as PDF");
        window.setMinHeight(600);
        window.setMinWidth(550);
        window.setScene(homeScreen.show());
        window.show();
    }

    public void setCurrentScreen(ScreenType type) {
        currentScreen = type;
        Screen screen = null;
        switch(type) {
            case ScreenType.HOME:
                screen = new HomeScreen();
                break;
        }

        if(screen == null) {
            throw new RuntimeException("Invalid screen.");
        }

        window.setScene(screen.show());
        window.show();
    }

}
