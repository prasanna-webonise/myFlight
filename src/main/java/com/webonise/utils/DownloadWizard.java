package com.webonise.utils;

import com.webonise.model.CacheRegion;
import com.webonise.model.Location;
import com.webonise.model.Sites;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadWizard {

    private static final String DOWNLOAD_FOLDER =
            "D:\\dev\\WorkSpace\\inFlight\\SampleCache\\src\\main\\resources\\images\\";
    private static final String DOWNLOAD_PATH_PREFIX = "https://api.mapbox.com/v4/mapbox.streets/";
    private static final String DOWNLOAD_PATH_SUFFIX = ",7/500x300.png?access_token=pk" +
            ".eyJ1IjoicHJhc2FuMjg5MyIsImEiOiJjaWZmN3AxbmE3emt0c2trbnZqcGN4bTl6In0.LJ220L_6CnwOfS5sAStp-g";
    private static final String IMAGE_FILE_EXTENSION = ".png";

    public void downloadCacheImage(String fileName) throws IOException {
        String path = getDownloadSourcePath();
        URL url = new URL(path);
        File file = null;
        HttpURLConnection httpconn = getHttpURLConnection(url);
        file = new File(DOWNLOAD_FOLDER + fileName + IMAGE_FILE_EXTENSION);
        InputStream inputStream = httpconn.getInputStream();
        FileOutputStream outputStream = new FileOutputStream(file);
        IOUtils.copy(inputStream, outputStream);
        addCacheRegionObjectToSites(fileName, file);
        closeHttpURLConnection(httpconn);
    }

    private void addCacheRegionObjectToSites(String fileName, File file) {
        CacheRegion cacheRegion = new CacheRegion();
        cacheRegion.setImage(file);
        cacheRegion.setRegionName(fileName);
        Sites.getInstance().getCacheSites().add(cacheRegion);
    }

    private void closeHttpURLConnection(HttpURLConnection httpconn) {
        httpconn.disconnect();
    }

    private String getDownloadSourcePath() {
        return DOWNLOAD_PATH_PREFIX + Location.getInstance().getLongitude() + "," + Location.getInstance()
                .getLatitude() + DOWNLOAD_PATH_SUFFIX;
    }

    private HttpURLConnection getHttpURLConnection(URL url) throws IOException {
        return (HttpURLConnection) url.openConnection();
    }
}
