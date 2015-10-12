package com.webonise.util;

import com.webonise.model.CacheRegion;
import com.webonise.model.Location;
import com.webonise.model.Sites;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class DownloadWizard {

    @Autowired
    private Sites sites;
    @Autowired
    private Location location;

    private static final String DOWNLOAD_FOLDER =
            "D:\\dev\\WorkSpace\\inFlight\\SampleCache\\src\\main\\resources\\images\\";
    private static final String DOWNLOAD_PATH_PREFIX = "https://api.mapbox.com/v4/mapbox.streets/";
    private static final String DOWNLOAD_PATH_SUFFIX = ",7/500x300.png?access_token=pk" +
            ".eyJ1IjoicHJhc2FuMjg5MyIsImEiOiJjaWZmN3AxbmE3emt0c2trbnZqcGN4bTl6In0.LJ220L_6CnwOfS5sAStp-g";
    private static final String IMAGE_FILE_EXTENSION = ".png";

    public void downloadCacheImage(String fileName) throws IOException {
        String path = getDownloadSourcePath();
        URL url = new URL(path);
        HttpURLConnection httpconn = getHttpURLConnection(url);
        InputStream inputStream = httpconn.getInputStream();
        copyFileToDestination(fileName, inputStream);
        closeHttpURLConnection(httpconn);
    }

    private void copyFileToDestination(String fileName, InputStream inputStream) throws IOException {
        File file;
        file = new File(DOWNLOAD_FOLDER + fileName + IMAGE_FILE_EXTENSION);
        FileOutputStream outputStream = new FileOutputStream(file);
        IOUtils.copy(inputStream, outputStream);
        addCacheRegionObjectToSites(fileName, file);
    }

    private void addCacheRegionObjectToSites(String fileName, File file) {
        CacheRegion cacheRegion = new CacheRegion();
        cacheRegion.setImage(file);
        cacheRegion.setRegionName(fileName);
        sites.getCacheSites().add(cacheRegion);
    }

    private void closeHttpURLConnection(HttpURLConnection httpconn) {
        httpconn.disconnect();
    }

    private String getDownloadSourcePath() {
        return DOWNLOAD_PATH_PREFIX + location.getLongitude() + "," + location
                .getLatitude() + DOWNLOAD_PATH_SUFFIX;
    }

    private HttpURLConnection getHttpURLConnection(URL url) throws IOException {
        return (HttpURLConnection) url.openConnection();
    }
}
