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
    private static final String DOWNLOAD_PATH_PART1 = "https://api.mapbox.com/v4/mapbox.streets/";
    private static final String DOWNLOAD_PATH_PART2 = ",7/500x300.png?access_token=pk" +
            ".eyJ1IjoicHJhc2FuMjg5MyIsImEiOiJjaWZmN3AxbmE3emt0c2trbnZqcGN4bTl6In0.LJ220L_6CnwOfS5sAStp-g";
    private static final String PNG = ".png";

    public void download(String fileName) throws IOException {
        String path = DOWNLOAD_PATH_PART1 + Location.getInstance().getLongitude() + "," + Location.getInstance()
                .getLattitude() + DOWNLOAD_PATH_PART2;
        URL url = new URL(path);
        File file = null;
        HttpURLConnection httpconn = (HttpURLConnection) url.openConnection();
        file = new File(DOWNLOAD_FOLDER + fileName + PNG);
        InputStream inputStream = httpconn.getInputStream();
        FileOutputStream outputStream = new FileOutputStream(file);
        IOUtils.copy(inputStream, outputStream);
        CacheRegion item = new CacheRegion();
        item.setImage(file);
        item.setRegionName(fileName);
        Sites.getInstance().getCacheSites().add(item);
        httpconn.disconnect();
    }
}