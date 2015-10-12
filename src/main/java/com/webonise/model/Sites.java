package com.webonise.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.stereotype.Component;

@Component
public class Sites {

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
