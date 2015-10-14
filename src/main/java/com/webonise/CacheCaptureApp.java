package com.webonise;

import com.webonise.controller.MainController;
import javafx.application.Application;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CacheCaptureApp extends Application {

    private static final Logger LOG = LoggerFactory.getLogger(CacheCaptureApp.class);

    public static void main(String[] args) {
        LOG.info("Launching the Application");
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        LOG.info("Starting the application");
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        MainController mainController = context.getBean(MainController.class);
        mainController.launch(primaryStage);
    }
}
