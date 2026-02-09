package com.rafatavares03.controller;

import com.rafatavares03.service.PdfGeneratorService;
import com.rafatavares03.view.DirectoryScreen;
import com.rafatavares03.view.HomeScreen;
import com.rafatavares03.view.Screen;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;


public class ScreenController {
    private final Stage window;
    private Map<ScreenType, Screen> screens = new HashMap<>();
    private ScreenType currentScreen = ScreenType.HOME;
    private final PdfGeneratorService pdfGeneratorService;

    public ScreenController(Stage stage) {
        pdfGeneratorService = new PdfGeneratorService();
        this.window = stage;
        loadScreens();

        window.setTitle("Image as PDF");
        window.setMinHeight(600);
        window.setMinWidth(550);

        setCurrentScreen(currentScreen);
    }

    private void loadScreens() {
        screens.put(ScreenType.HOME, new HomeScreen(this));
        screens.put(ScreenType.DIRECTORY, new DirectoryScreen(this, pdfGeneratorService));
    }

    public void setCurrentScreen(ScreenType type) {
        Screen screen = screens.get(type);
        currentScreen = type;

        window.setScene(screen.show());
        window.show();
    }

    public Stage getWindow() {
        return this.window;
    }
}
