// Authors: Christopher Waschke, Brody Weinkauf, Jackson Jenks
// Description: A simple Go Fish Card game using Stacks
// Citation: https://stackoverflow.com/questions/36629522/convert-arraylist-to-observable-list-for-javafx-program
// Citation: https://stackoverflow.com/questions/12459086/how-to-perform-an-action-by-selecting-an-item-from-listview-in-javafx-2


package com.cs145group;

import java.io.IOException;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;

public class GameController {

    private GoFishManager gameManager;
    private Card selectedCard;

    @FXML
    private ListView<Card> cardList;

    @FXML
    private Label cpuCardText;

    public void updateCPUText(){
        this.cpuCardText.setText(String.format("CPU Remaining Cards: %d", gameManager.getCPUHandSize()));
    }

    public static void handleWinResponse(ButtonType response){
        if(response.getButtonData() == ButtonData.YES ){
            try{
                App.setScene("game.fxml");
            } catch (IOException e){
                System.err.println("Unable to load game.fxml");
                System.exit(1);
            }
        } else {
            Platform.exit();
        }
    }

    public void showSelectAlert(){
        Alert invalidAlert = new Alert(AlertType.INFORMATION);
        invalidAlert.setTitle("Invalid Selection");
        invalidAlert.setHeaderText("");
        invalidAlert.setContentText("Please select a card to play.");
        invalidAlert.showAndWait();
    }

    public void showWinAlert(){
        ButtonType yes = new ButtonType("Yes", ButtonData.YES);
        ButtonType no = new ButtonType("No", ButtonData.NO);
        Alert invalidAlert = new Alert(AlertType.CONFIRMATION, null, yes, no);
        invalidAlert.setTitle("You've won!");
        invalidAlert.setHeaderText("");
        invalidAlert.setContentText("Congrats! Play again?");
        invalidAlert.showAndWait().ifPresent(GameController::handleWinResponse);;
    }

    public void showLoseAlert(){
        ButtonType yes = new ButtonType("Yes", ButtonData.YES);
        ButtonType no = new ButtonType("No", ButtonData.NO);
        Alert invalidAlert = new Alert(AlertType.ERROR, null, yes, no);
        invalidAlert.setTitle("You've lost.");
        invalidAlert.setHeaderText("");
        invalidAlert.setContentText("Sorry, you've lost. Play again?");
        invalidAlert.showAndWait().ifPresent(GameController::handleWinResponse);;
    }

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

        updateCPUText();
    }

    @FXML
    public void requestCard(){
        if(selectedCard == null) {
            showSelectAlert();
            return;
        }
        this.gameManager.userPlayHand(selectedCard);
        this.gameManager.cpuTurn();
        this.cardList.setItems(FXCollections.observableList(this.gameManager.getUserHand()));
        updateCPUText();
        switch (this.gameManager.checkWinState()) {
            case "win":
            showWinAlert();
            break;
            case "lost":
            showLoseAlert();
            break;
            default:
            break;
        }
    }
}
