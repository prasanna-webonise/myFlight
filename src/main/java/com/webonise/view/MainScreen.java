package com.webonise.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

//@Component
public class MainScreen  extends Pane{
    private static final Logger LOG = LoggerFactory.getLogger(MainScreen.class);
    private static final String MAIN_SCREEN_FXML ="/fxml/MainScreen.fxml" ;

    @FXML
    private TableView listOfCacheSites;

    @FXML
    private WebView browser;

    @FXML
    private TextField cachedSiteName;

    @FXML
    private TableView siteImage;

    @FXML
    private TableView siteName;

    private WebEngine engine;

    public MainScreen(){
    }

    public Parent loadFxml() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(MAIN_SCREEN_FXML));
        return root;
    }

    @FXML
    public void loadPage(){
        engine = browser.getEngine();
        engine.load(getClass().getResource("/html/LatLangMarking.html").toExternalForm());
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

    public TableView getSiteImage() {
        return siteImage;
    }

    public void setSiteImage(TableView siteImage) {
        this.siteImage = siteImage;
    }

    public TableView getSiteName() {
        return siteName;
    }

    public void setSiteName(TableView siteName) {
        this.siteName = siteName;
    }

    public WebEngine getEngine() {
        return engine;
    }

    public void setEngine(WebEngine engine) {
        this.engine = engine;
    }

}
