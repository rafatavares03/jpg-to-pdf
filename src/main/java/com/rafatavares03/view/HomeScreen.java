package com.rafatavares03.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

public class HomeScreen implements Screen{
    public Scene screen;

    public HomeScreen() {
        this.screen = new Scene(build());
    };

    private StackPane build() {
        StackPane root = new StackPane();
        Button btn = new Button();
        btn.setText("Folder images");
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        root.getChildren().add(btn);
        return root;
    }

    public Scene show() {
        return this.screen;
    }
}
