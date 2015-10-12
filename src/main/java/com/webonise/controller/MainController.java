package com.webonise.controller;

import com.webonise.model.Location;
import com.webonise.model.Sites;
import com.webonise.view.MainScreen;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MainController {

    private static final String APP_TITLE = "CACHE CAPTURE";

    private MainScreen screen = new MainScreen();
    private Stage stage;
    private Scene scene;

    public void launch(Stage primaryStage) throws IOException {
        stage = new Stage(StageStyle.DECORATED);
        scene = new Scene(screen.loadFxml());
        stage.setTitle(APP_TITLE);
        stage.initOwner(primaryStage);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    public Location getLocation() {
        return Location.getInstance();
    }

    public ObservableList getSites() {
        return Sites.getInstance().getCacheSites();
    }

}
