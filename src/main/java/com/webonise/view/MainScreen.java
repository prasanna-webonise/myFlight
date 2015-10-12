package com.webonise.view;

import com.webonise.controller.MainController;
import com.webonise.model.CacheRegion;
import com.webonise.model.Location;
import com.webonise.model.Sites;
import com.webonise.util.DownloadWizard;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

@Component
public class MainScreen extends Pane {

    private static final Logger LOG = LoggerFactory.getLogger(MainScreen.class);
    private static final String MAIN_SCREEN_FXML = "/fxml/MainScreen.fxml";
    private static final String MAIN_SCREEN_HTML = "/html/LatLangMarking.html";

    @FXML
    private TableView listOfCacheSites;

    @FXML
    private WebView browser;

    @FXML
    private TextField cachedSiteName;

    @FXML
    private TableColumn image;

    @FXML
    private TableColumn cacheName;

    @FXML
    private ImageView siteImage;

    @FXML
    private Button start;

    @FXML
    private Button saveButton;

    @Autowired
    private MainController mainController;

    @Autowired
    private DownloadWizard downloader;

    @Autowired
    private Location location;

    @Autowired
    private Sites sites;

    private WebEngine engine;
    private JSObject script;

    public MainScreen(){
        super();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(MAIN_SCREEN_FXML));
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            LOG.error("Exception occured while loading UI : {}",e.getMessage());
        }
    }

    @PostConstruct
    public void init(){
        start.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                engine = browser.getEngine();
                script = (JSObject) engine.executeScript("window");
                script.setMember("centerCoordinates", location);
                engine.load(getClass().getResource(MAIN_SCREEN_HTML).toExternalForm());
                image.setCellValueFactory(new PropertyValueFactory<CacheRegion, File>("image"));
                cacheName.setCellValueFactory(new PropertyValueFactory<CacheRegion, String>("regionName"));
                listOfCacheSites.setItems(sites.getCacheSites());
            }
        });

        saveButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                try {
                    downloader.downloadCacheImage(cachedSiteName.getText());
                } catch (IOException e) {
                    LOG.error("Exception occured while downloading : {}", e.getMessage());
                }
                engine.executeScript("removeLayer()");
                cachedSiteName.setText("");
            }
        });
    }

    public Parent loadFxml() throws IOException {
        return FXMLLoader.load(getClass().getResource(MAIN_SCREEN_FXML));

    }
}
