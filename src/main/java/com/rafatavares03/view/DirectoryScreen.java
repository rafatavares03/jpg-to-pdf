package com.rafatavares03.view;

import com.rafatavares03.controller.ScreenController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;

import java.io.File;

public class DirectoryScreen implements Screen{
    ScreenController controller;
    Scene scene;

    public DirectoryScreen(ScreenController controller) {
        this.controller = controller;
        this.scene = build();
    }

    private Scene build() {
        VBox container = new VBox();
        Text title = new Text("Selecione a pasta para gerar o PDF.");
        DirectoryChooser directoryChooser = new DirectoryChooser();

        Button button = new Button("Select Directory");
        button.setOnAction(e -> {
            File selectedDirectory = directoryChooser.showDialog(controller.getWindow());
            System.out.println(selectedDirectory.getAbsolutePath());
        });

        container.getChildren().addAll(title, button);

        return new Scene(container);
    }

    public Scene show() {
        return this.scene;
    }
}
