package GC_11.view.Lobby;

import GC_11.distributed.Client;
import GC_11.exceptions.IllegalMoveException;
import GC_11.network.choices.Choice;
import GC_11.network.choices.ChoiceFactory;
import GC_11.network.message.LobbyViewMessage;
import GC_11.view.GUI.GUIApplication;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class LobbyController {
    public Stage primaryStage;

    public Client client;

    @FXML
    private ChoiceBox<String> chooseNumberPlayers;

    @FXML
    private TextField clientNickname;

    @FXML
    private Button confirmName;

    @FXML
    private TextArea errorArea;

    @FXML
    private TextArea listPlayers;

    @FXML
    private Label text;


    public LobbyController(){
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void initialize() {
        confirmName.setDisable(true);
        clientNickname.textProperty().addListener((observable, oldValue, newValue) -> {
            confirmName.setDisable(newValue.trim().isEmpty());
        });
        chooseNumberPlayers.getItems().addAll("2", "3", "4");
    }

    public void setError(String error) {
        errorArea.setText(error);
    }

    public void showPlayers(List<String> players) {
        chooseNumberPlayers.setVisible(false);
        text.setVisible(false);
        listPlayers.setVisible(true);
        confirmName.setVisible(false);
        clientNickname.setVisible(false);

        for(String player : players)
            listPlayers.appendText(player + "\n");

        setError("Waiting for other players...");
    }

    public void waitingRoom(){

        //OTTIENI LISTA PLAYER DA GAMEVIEWMESSAGE

        // TEMPORANEO
        List<String> players = new ArrayList<>();
        players.add("player1");
        players.add("player2");
        players.add("player3");
        showPlayers(players);
    }

    @FXML
    public String sendNumberOfPlayer(){
        int numberOfPlayers = Integer.parseInt((String) chooseNumberPlayers.getValue());
        System.out.println(numberOfPlayers);
        waitingRoom();
        return chooseNumberPlayers.getValue().toString();
    }

    @FXML
    public void createChoice(String s) {
        Choice choice;
        try {
            choice = ChoiceFactory.createChoice(null, s);
        } catch (IllegalMoveException e) {
            throw new RuntimeException(e);
        }
        try {
            client.notifyServer(choice);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void confirmNickname() {
        confirmName.setDisable(true);
        chooseNumberPlayers.setVisible(true);
        clientNickname.setVisible(false);
        text.setText("Scegli il numero di giocatori");

        chooseNumberPlayers.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            // Mostra il bottone di conferma solo se è selezionata un'opzione
            confirmName.setDisable(newValue == null);
        });

        confirmName.setOnAction(event -> sendNumberOfPlayer());
        createChoice("ADD_PLAYER " +clientNickname.getText());
        notifyAll();
    }

    //USA platform.runLater
    public void updatePlayerList(LobbyViewMessage message) {
        List<String> players = message.getPlayersNames();
        listPlayers.setText("");
        for(String player : players)
            listPlayers.appendText(player + "\n");
    }

    public GUIApplication changeScene() throws RemoteException {
        GUIApplication guiApplication = new GUIApplication();
        //guiApplication.setClient(this.client);

        try {
            guiApplication.start(this.primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return guiApplication;
    }

}
