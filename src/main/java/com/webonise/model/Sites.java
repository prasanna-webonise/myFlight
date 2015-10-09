package com.webonise.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Sites {

    private ObservableList<CacheRegion> cacheSites;

    public Sites() {
        cacheSites= FXCollections.observableArrayList();
    }

    public ObservableList<CacheRegion> getCacheSites() {
        return cacheSites;
    }

    public void setCacheSites(ObservableList<CacheRegion> cacheSites) {
        this.cacheSites = cacheSites;
    }
}
