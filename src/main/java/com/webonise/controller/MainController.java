package com.webonise.controller;

import com.webonise.view.MainScreen;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MainController {

    private static final String APP_TITLE = "CACHE RECORDING";

    //@Autowired
    MainScreen screen = new MainScreen();
    Stage stage;
    Scene scene;

    public void launch(Stage primaryStage) throws IOException {
        stage = new Stage(StageStyle.DECORATED);
        //Parent root = FXMLLoader.load(getClass().getResource("/fxml/MainScreen.fxml"));
        scene = new Scene(screen.loadFxml());
        stage.setTitle(APP_TITLE);
        stage.initOwner(primaryStage);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }




}
