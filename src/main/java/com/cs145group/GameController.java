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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;

/* Game Controller Class
 * This class is the core of the GUI aspect of our program.
 * It essentially ties UI elements and events to calls of the encapsulated GoFishManager class, which holds our game logic.
 */
public class GameController {

    // Our properties
    private GoFishManager gameManager;
    private Card selectedCard; // The card selected in the List View.

    @FXML
    private ListView<Card> cardList; // The List View of our cards, specified via the ID in the FXML with fx:id.

    @FXML
    private Label cpuCardText; // The Label saying how many cards the CPU has in their hand.

    @FXML
    private Label playScoreText;

    @FXML
    private Label cpuScoreText;

    @FXML
    private Label deckRemainder;
    /*
     * Void Method,
     * Updates all the labels with the proper values coming from the game manager.
     */
    public void updateText(){
        this.playScoreText.setText(String.format("Player Score: %d", gameManager.getUserScore()));
        this.cpuScoreText.setText(String.format("CPU Score: %d", gameManager.getCpuScore()));
        this.deckRemainder.setText(String.format("Deck Remainder: %d", gameManager.getDeckLocation()));
        this.cpuCardText.setText(String.format("CPU Remaining Cards: %d", gameManager.getCPUHandSize()));
    }

    /* Void Method
     * Handles the response to both result dialogs, reloading the scene, hence restarting the game
     * if the player opts to play again. Otherwise quits the program gracefully.
     * Parameters:
     *  response (ButtonType) - The button which was selected in the dialog.
     */
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

    /* Void Method
     * Builds and shows the Alert to be shown if the player hasn't selected a cart when clicking the request button.
     */
    public static void showSelectAlert(){
        Alert invalidAlert = new Alert(AlertType.INFORMATION);
        invalidAlert.setTitle("Invalid Selection");
        invalidAlert.setHeaderText("");
        invalidAlert.setContentText("Please select a card to play.");
        invalidAlert.showAndWait();
    }

    /* Void Method
     * Builds and shows the Alert to be shown when the player Wins a game.
     */
    public static void showWinAlert(){
        ButtonType yes = new ButtonType("Yes", ButtonData.YES);
        ButtonType no = new ButtonType("No", ButtonData.NO);
        Alert invalidAlert = new Alert(AlertType.CONFIRMATION, null, yes, no);
        invalidAlert.setTitle("You've won!");
        invalidAlert.setHeaderText("");
        invalidAlert.setContentText("Congrats! Play again?");
        invalidAlert.showAndWait().ifPresent(GameController::handleWinResponse);;
    }

    /* Void Method
     * Builds and shows the Alert to be shown when the player loses a game.
     */
    public static void showLoseAlert(){
        ButtonType yes = new ButtonType("Yes", ButtonData.YES);
        ButtonType no = new ButtonType("No", ButtonData.NO);
        Alert invalidAlert = new Alert(AlertType.ERROR, null, yes, no);
        invalidAlert.setTitle("You've lost.");
        invalidAlert.setHeaderText("");
        invalidAlert.setContentText("Sorry, you've lost. Play again?");
        invalidAlert.showAndWait().ifPresent(GameController::handleWinResponse);;
    }

    /*
     * Void Initializer method.
     * This method is essentially our constructor, in JavaFX.
     * Called automatically as part of the construction of the scene.
     * This method initializes our gameManager property, sets our listView to the list of Cards
     * In the User's hand property in the Game Manager, by converting the Arraylist to an ObservableList.
     * We also create the event handler for changing the Selection in the list view, updating the selected card property.
     * Finally, we call updateCPUText to show the initial number of cards in the CPU's hand.
     */
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

        updateText();
    }

    /*
     * Void Method (Button Click Event Handler)
     * Specified in our game.fxml, this method is called when the request button is clicked.
     * This method, which admittedly is probably doing too much heavy lifting
     * calls the gameManager, set up during initialization playing the currently selected card.
     * After playing our hand, we tell the gameManager to play the CPU's hand.
     * We then update the Listview, and cpu hand text, to reflect the changes made during the class.
     * Finally, we check in case the game ended during those two calls, if it does, we show the proper alert.
     */
    @FXML
    public void requestCard(){
        if(selectedCard == null) {
            showSelectAlert();
            return;
        }
        this.gameManager.userPlayHand(selectedCard);
        this.gameManager.cpuTurn();
        this.cardList.setItems(FXCollections.observableList(this.gameManager.getUserHand()));
        updateText();
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
