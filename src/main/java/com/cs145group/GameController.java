package com.cs145group;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;

public class GameController {

    public GoFishManager gameManager;

    @FXML
    private ListView<Card> cardList;

    @FXML
    public void initialize(){
        gameManager = new GoFishManager();
        // Hopefully, the constructor will set up a new instance of the game
        // Meaning that this call will be enough. (I.E. Constructor will shuffle and then deal the cards to internally managed players.)
        // What I'll need here is set my cardList to a converted (which the UI will take care of) version of the player's hand
        // And update the CPU card count text.
    }

    @FXML
    public void requestCard(){
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setContentText("Card played.");
        alert.setHeaderText("");
        alert.showAndWait();

        // This method will hopefully just call our gameManager with the information of which card in hand the player played.
        // From this point, it will expect back an update to player's hand, and an update of the remaining CPU hand cards.
    }
}
