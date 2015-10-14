package com.webonise.controller;

import com.webonise.util.DownloadWizard;
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

    @Autowired
    private DownloadWizard downloader;

    public void launch(Stage primaryStage) throws IOException {
        Stage stage =new Stage(StageStyle.DECORATED);;
        Scene scene= new Scene(mainScreen);;
        stage.setTitle(APP_TITLE);
        stage.initOwner(primaryStage);
        stage.setScene(scene);
        stage.show();
    }

    public void downloadSiteImage(String cachedSiteName) throws IOException {
        downloader.downloadCacheImage(cachedSiteName);
    }
}
