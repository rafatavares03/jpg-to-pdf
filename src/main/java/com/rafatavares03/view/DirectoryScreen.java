package com.rafatavares03.view;

import com.rafatavares03.controller.ScreenController;
import com.rafatavares03.service.PdfGeneratorService;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;

import java.io.File;

public class DirectoryScreen implements Screen{
    private final ScreenController controller;
    private Scene scene;
    private final PdfGeneratorService pdfGeneratorService;
    private ObjectProperty<File> inputDirectory = new SimpleObjectProperty<>();
    private ObjectProperty<File> outputDirectory = new SimpleObjectProperty<>();

    public DirectoryScreen(ScreenController controller, PdfGeneratorService pdfGeneratorService) {
        this.controller = controller;
        this.scene = build();
        this.pdfGeneratorService = pdfGeneratorService;
    }

    private Scene build() {
        VBox container = new VBox();
        Text title = new Text("Selecione a pasta para gerar o PDF.");
        DirectoryChooser directoryChooser = new DirectoryChooser();

        Button inputDirectorySelector = new Button("Selecione a pasta de origem");
        inputDirectorySelector.setOnAction(e -> {
            inputDirectory.set(directoryChooser.showDialog(controller.getWindow()));
            if(inputDirectory.get() != null) System.out.println("Origem:" + inputDirectory.get().getAbsolutePath());
        });

        Button outputDirectorySelector = new Button("Selecione a pasta de destino");
        outputDirectorySelector.setOnAction(e -> {
            outputDirectory.set(directoryChooser.showDialog(controller.getWindow()));
            if(outputDirectory.get() != null) System.out.println("Destino:" + outputDirectory.get().getAbsolutePath());
        });

        Button generateFileButton = new Button("Gerar PDF");
        generateFileButton.disableProperty()
                        .bind(
                                inputDirectory.isNull()
                                        .or(outputDirectory.isNull())
                        );
        generateFileButton.setOnAction(e -> {
            pdfGeneratorService.generateSinglePdfFromDirectory(inputDirectory.get(), outputDirectory.get());
        });

        container.getChildren().addAll(title, inputDirectorySelector, outputDirectorySelector, generateFileButton);

        return new Scene(container);
    }

    public Scene show() {
        return this.scene;
    }
}
