package com.webonise.view;

import com.webonise.controller.MainController;
import com.webonise.model.Location;
import com.webonise.model.Sites;
import com.webonise.model.WebEngineStatus;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.concurrent.Worker.State;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Component
public class MainScreen extends HBox {

    private static final Logger LOG = LoggerFactory.getLogger(MainScreen.class);
    private static final String MAIN_SCREEN_FXML = "/fxml/MainScreen.fxml";
    private static final String MAIN_SCREEN_HTML = "/html/mapRegion.html";
    private static final String REMOVE_LAYER = "removeLayer()";
    private static final String BLANK_STRING = "";

    @FXML
    private ListView listOfCacheSites;

    @FXML
    private WebView browser;

    @FXML
    private TextField cachedSiteName;

    @FXML
    private Button saveButton;

    @Autowired
    private Location location;

    @Autowired
    private Sites sites;

    @Autowired
    private MainController mainController;

    private WebEngine engine;
    private WebEngineStatus webEngineStatus;

    public MainScreen() {
        super();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(MAIN_SCREEN_FXML));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            LOG.error("Exception occured while loading UI : {}", e);
        }
    }

    @PostConstruct
    public void init() {
        loadMapPage();
        setButtonActions();
        listOfCacheSites.setItems(sites.getCacheSites());
    }
    private void loadMapPage() {
        JSObject script;
        engine = browser.getEngine();
        engine.getLoadWorker().stateProperty().addListener(new ChangeListener<Worker.State>() {
            public void changed(ObservableValue<? extends State> observable, State oldValue, State newValue) {
                if (newValue == State.SUCCEEDED) {
                    webEngineStatus = WebEngineStatus.ON;
                } else {
                    webEngineStatus = WebEngineStatus.OFF;
                }
            }
        });
        script = (JSObject) engine.executeScript("window");
        script.setMember("centerCoordinates", location);
        engine.load(getClass().getResource(MAIN_SCREEN_HTML).toExternalForm());
    }

    private void setButtonActions() {
        saveButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                try {
                    mainController.downloadSiteImage(cachedSiteName.getText());
                } catch (IOException e) {
                    LOG.error("Exception occurred while downloading the file : {}",e);
                }
                if (webEngineStatus == WebEngineStatus.ON) {
                    engine.executeScript(REMOVE_LAYER);
                    cachedSiteName.setText(BLANK_STRING);
                } else {
                    LOG.error("Network offline");
                }
            }
        });
    }
}
