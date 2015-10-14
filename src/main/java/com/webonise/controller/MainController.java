package com.webonise.controller;

import com.webonise.view.MainScreen;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MainController extends Pane {

    private static final String APP_TITLE = "CACHE CAPTURE";

    @Autowired
    private MainScreen mainScreen;

    public void launch(Stage primaryStage) throws IOException {
        Stage stage;
        Scene scene;
        stage = new Stage(StageStyle.DECORATED);
        scene = new Scene(mainScreen);
        stage.setTitle(APP_TITLE);
        stage.initOwner(primaryStage);
        stage.setScene(scene);
        stage.show();
    }
}
