package com.rafatavares03.view;

import com.rafatavares03.controller.ScreenController;
import com.rafatavares03.controller.ScreenType;
import com.rafatavares03.service.PdfGeneratorService;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;

import java.awt.*;
import java.io.File;

public class DirectoryScreen implements Screen{
    private final ScreenController controller;
    private final Scene scene;
    private final PdfGeneratorService pdfGeneratorService;
    private final ObjectProperty<File> inputDirectory = new SimpleObjectProperty<>();
    private final ObjectProperty<File> outputDirectory = new SimpleObjectProperty<>();
    private final DirectoryChooser directoryChooser = new DirectoryChooser();

    public DirectoryScreen(ScreenController controller, PdfGeneratorService pdfGeneratorService) {
        this.controller = controller;
        this.scene = build();
        this.pdfGeneratorService = pdfGeneratorService;
    }

    private Scene build() {
        VBox container = new VBox();
        Text pageText = new Text("Gere um único PDF a partir de fotos de uma pasta.");

        VBox inputDirectorySelector = buttonSelectorContainer("Selecione a pasta que contém as imagens do PDF:", "Selecionar", inputDirectory);
        VBox outputDirectorySelector = buttonSelectorContainer("Selecione a pasta para salvar o PDF:", "Selecionar", outputDirectory);

        Button generateFileButton = new Button("Gerar PDF");
        generateFileButton.disableProperty()
                        .bind(
                                inputDirectory.isNull()
                                        .or(outputDirectory.isNull())
                        );
        generateFileButton.setOnAction(e -> {
            pdfGeneratorService.generateSinglePdfFromDirectory(inputDirectory.get(), outputDirectory.get());
            controller.setCurrentScreen(ScreenType.HOME);
        });

        container.getChildren().addAll(pageText, inputDirectorySelector, outputDirectorySelector, generateFileButton);

        return new Scene(container);
    }

    private VBox buttonSelectorContainer(String containerText, String buttonText, ObjectProperty<File> directory) {
        VBox container = new VBox();
        Text text = new Text(containerText);
        Text path = new Text();
        Button button = new Button(buttonText);
        button.setOnAction(e -> {
            directory.set(directoryChooser.showDialog(controller.getWindow()));
            path.setText(directory.get().getAbsolutePath());
            if(directory.get() != null) System.out.println("Origem:" + directory.get().getAbsolutePath());
        });

        container.getChildren().addAll(text,path,button);
        return container;
    }

    public Scene show() {
        return this.scene;
    }
}
