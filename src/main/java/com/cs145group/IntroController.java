package com.cs145group;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class IntroController {

    @FXML
    private void handlePlay() throws IOException {
        App.setScene("game.fxml");
    }
}
