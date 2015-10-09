package com.webonise;

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadWizard {

    public void download() throws IOException {
        String path = "https://api.mapbox.com/v4/mapbox.streets/73.8567,18.5203,13/500x300.png?access_token=pk" +
                ".eyJ1IjoicHJhc2FuMjg5MyIsImEiOiJjaWZmN3AxbmE3emt0c2trbnZqcGN4bTl6In0.LJ220L_6CnwOfS5sAStp-g";
        URL url = new URL(path);
        File file = null;
        HttpURLConnection httpconn = (HttpURLConnection) url.openConnection();
        file = new File("D:\\sample.png");
        InputStream inputStream = httpconn.getInputStream();
        FileOutputStream outputStream = new FileOutputStream(file);
        IOUtils.copy(inputStream, outputStream);
        httpconn.disconnect();
    }
}