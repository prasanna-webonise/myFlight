package com.webonise;

import com.webonise.controller.MainController;
import javafx.application.Application;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App extends Application {

    private static final Logger LOG = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        LOG.debug("Launching the Application");
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        LOG.debug("Starting the application");
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        MainController controller = context.getBean(MainController.class);
        controller.launch(primaryStage);
    }
}
