package com.rafatavares03.view;

import com.rafatavares03.controller.ScreenController;
import com.rafatavares03.controller.ScreenType;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class HomeScreen implements Screen{
    public Scene screen;
    private final ScreenController controller;

    public HomeScreen(ScreenController controller) {
        this.screen = build();
        this.controller = controller;
    };

    private Scene build() {
        VBox container = new VBox();
        Text title = new Text("Image as PDF");
        Text subtitle = new Text("Crie PDFs com imagens do seu computador.");
        Button btn = new Button();
        btn.setText("Imagens de uma pasta\nSelecione uma pasta para criar um PDF com todas as imagens que ela possui.");
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
                controller.setCurrentScreen(ScreenType.DIRECTORY);
            }
        });
        container.getChildren().add(title);
        container.getChildren().add(subtitle);
        container.getChildren().add(btn);

        return new Scene(container);
    }

    public Scene show() {
        return this.screen;
    }
}
