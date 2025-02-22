package com.cs145group;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(FXMLLoader.load(getClass().getResource("intro.fxml")), 640, 480);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Go Fish!");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}