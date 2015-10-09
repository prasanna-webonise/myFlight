package com.webonise.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Sites {

    private static Sites instance;

    public static Sites getInstance() {
        if (instance == null) {
            instance = new Sites();
        }
        return instance;
    }

    private ObservableList<CacheRegion> cacheSites;

    private Sites() {
        cacheSites = FXCollections.observableArrayList();
    }

    public ObservableList<CacheRegion> getCacheSites() {
        return cacheSites;
    }

    public void setCacheSites(ObservableList<CacheRegion> cacheSites) {
        this.cacheSites = cacheSites;
    }
}
