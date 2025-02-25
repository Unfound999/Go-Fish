package com.cs145group;

import java.io.IOException;

import javafx.fxml.FXML;

public class IntroController {

    @FXML
    private void handlePlay() throws IOException {
        App.setScene("game.fxml");
    }
}
