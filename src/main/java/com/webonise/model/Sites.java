package com.webonise.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.stereotype.Component;

@Component
public class Sites {

    private final ObservableList<CacheRegion> cacheSites;

    public Sites() {
        cacheSites = FXCollections.observableArrayList();
    }

    public ObservableList<CacheRegion> getCacheSites() {
        return cacheSites;
    }

}
