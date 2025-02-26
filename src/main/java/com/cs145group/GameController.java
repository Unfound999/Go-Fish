// Authors: Christopher Waschke, Brody Weinkauf, Jackson Jenks
// Description: A simple Go Fish Card game using Stacks
// Citation: https://stackoverflow.com/questions/36629522/convert-arraylist-to-observable-list-for-javafx-program
// Citation: https://stackoverflow.com/questions/12459086/how-to-perform-an-action-by-selecting-an-item-from-listview-in-javafx-2


package com.cs145group;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;

public class GameController {

    private GoFishManager gameManager;
    private Card selectedCard;

    @FXML
    private ListView<Card> cardList;

    @FXML
    public void initialize(){
        gameManager = new GoFishManager();
        var handList = FXCollections.observableList(gameManager.getUserHand());
        cardList.setItems(handList);

        cardList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Card>() {

            @Override
            public void changed(ObservableValue<? extends Card> observable, Card oldValue, Card newValue) {
                selectedCard = newValue;
            }
        });
    }

    @FXML
    public void requestCard(){
        if(selectedCard == null) {
            Alert invalidAlert = new Alert(AlertType.INFORMATION);
            invalidAlert.setTitle("Invalid Selection");
            invalidAlert.setHeaderText("");
            invalidAlert.setContentText("Please select a card to play.");
            invalidAlert.showAndWait();
            return;
        }
        this.gameManager.userPlayHand(selectedCard);
        this.cardList.setItems(FXCollections.observableList(this.gameManager.getUserHand()));
        this.gameManager.cpuTurn();
    }
}
