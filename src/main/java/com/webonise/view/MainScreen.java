package com.webonise.view;

import com.webonise.controller.MainController;
import com.webonise.model.CacheRegion;
import com.webonise.utils.DownloadWizard;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
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

import java.io.File;
import java.io.IOException;

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

    private WebEngine engine;
    private JSObject script;
    @Autowired
    private MainController mainController;

    public MainScreen() {
    }

    public Parent loadFxml() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(MAIN_SCREEN_FXML));
        return root;
    }

    @FXML
    public void loadPage() {
        engine = browser.getEngine();
        script = (JSObject) engine.executeScript("window");
        script.setMember("centerCoordinates", new MainController().getLocation());
        engine.load(getClass().getResource(MAIN_SCREEN_HTML).toExternalForm());
        image.setCellValueFactory(new PropertyValueFactory<CacheRegion, File>("image"));
        cacheName.setCellValueFactory(new PropertyValueFactory<CacheRegion, String>("regionName"));
        listOfCacheSites.setItems(new MainController().getSites());
    }

    @FXML
    public void saveCache() throws IOException {
        new DownloadWizard().download(cachedSiteName.getText());
        engine.executeScript("removeLayer()");
        cachedSiteName.setText("");
    }

    public TableView getListOfCacheSites() {
        return listOfCacheSites;
    }

    public void setListOfCacheSites(TableView listOfCacheSites) {
        this.listOfCacheSites = listOfCacheSites;
    }

    public WebView getBrowser() {
        return browser;
    }

    public void setBrowser(WebView browser) {
        this.browser = browser;
    }

    public TextField getCachedSiteName() {
        return cachedSiteName;
    }

    public void setCachedSiteName(TextField cachedSiteName) {
        this.cachedSiteName = cachedSiteName;
    }

    public WebEngine getEngine() {
        return engine;
    }

    public void setEngine(WebEngine engine) {
        this.engine = engine;
    }


    public TableColumn getImage() {
        return image;
    }

    public void setImage(TableColumn image) {
        this.image = image;
    }

    public TableColumn getCacheName() {
        return cacheName;
    }

    public void setCacheName(TableColumn cacheName) {
        this.cacheName = cacheName;
    }

}
