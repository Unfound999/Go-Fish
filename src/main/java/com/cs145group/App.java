// Authors: Christopher Waschke, Brody Weinkauf, Jackson Jenks
// Description: JavaFX Initialization of GUI Program.
// Citation: This document is heavily taken form the template generated with a new JavaFX Application.

package com.cs145group;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene; // private global scene variable, used for updating the view in Controller classes.

    /*
     * JavaFX start method.
     * Loads our stage (Window) with the Scene (view/gui) specified from the FXML loader call.
     * Sets the title to Go Fish!
     * Shows the window.
     */
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(FXMLLoader.load(getClass().getResource("intro.fxml")), 640, 480);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Go Fish!");
        stage.show();
    }

    /*
     * Void method.
     * Sets the Scene variable with the new view to be loaded.
     */
    public static void setScene(String fxml) throws IOException {
        scene.setRoot(FXMLLoader.load(App.class.getResource(fxml)));
    }

    /* Main Method
     * Calls JavaFX required Launch method, which then differs to the launch() method overrided above, after creating the stage. (Window)
     */
    public static void main(String[] args) {
        launch();
    }

}