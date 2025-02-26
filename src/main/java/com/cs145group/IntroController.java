package com.cs145group;

import java.io.IOException;

import javafx.fxml.FXML;

/*
 * IntroController class
 * Displays the instructions to go fish,
 * and handles the play button switch to the game scene.
 */
public class IntroController {

    /*
     * void method (Button Click Handler, defined in FXML)
     * Switches the view to the game view.
     */
    @FXML
    private void handlePlay() throws IOException {
        App.setScene("game.fxml");
    }
}
