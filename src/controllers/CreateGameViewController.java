package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import models.User;
import models.VideoGame;
import utilities.DBUtilities;
import utilities.SceneChanger;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class CreateGameViewController implements Initializable {

    private models.VideoGame game;

    @FXML
    private TextField gameNameTextView;

    @FXML
    private ListView<User> gameUserListView;

    @FXML
    private ListView<User> allUserListView;

    @FXML
    private TextField gameRatingTextView;

    @FXML
    private Label msgLabel;

    //Add a user from allUser to the certain game and then remove from the list to prevent duplication
    @FXML
    void AddUsToGame(ActionEvent event) {
        User selectedUser = allUserListView.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            gameUserListView.getItems().add(selectedUser);
            allUserListView.getItems().remove(selectedUser);
        }
    }

    //Remove a user from a game and add to the allUser and then remove from the list of Game users to prevent duplication
    @FXML
    void RemoveUsToGame(ActionEvent event) {
        User selectedUser = gameUserListView.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            allUserListView.getItems().add(selectedUser);
            gameUserListView.getItems().remove(selectedUser);
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            allUserListView.getItems().addAll(DBUtilities.getUserFromDB());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //if click the button return to home page
    @FXML
    private void returnToHome(ActionEvent event) throws IOException {
        SceneChanger.changeScenes(event, "../views/dashboardView.fxml", "Home", null);
    }

    //check if the object is null then create a new one if the object is filled update it
    @FXML
    private void submit(ActionEvent event) throws SQLException {
        if (fieldsPopulated())
        try {
            // add new game when the <game> is null
            if(game == null){
                game = new VideoGame(
                    gameNameTextView.getText(),
                    Integer.parseInt(gameRatingTextView.getText()),
                0
                );

                int netIdValue = DBUtilities.insertGameIntoDB(game);

                game.setId(netIdValue);
            }
            else{
                // set game's properties
                game.setName(gameNameTextView.getText());
                game.setAgeRating(Integer.parseInt(gameRatingTextView.getText()));

                DBUtilities.UpdateGame(game);                   // update the game info
                DBUtilities.ClearGameUser(game.getId());        // clean up currently existing users
            }

            // save assigned users to the game
            for (int i = 0; i < gameUserListView.getItems().size(); i++) {
                User currentUser = gameUserListView.getItems().get(i);
                DBUtilities.AddUserToGame(game.getId(), currentUser.getId());
            }
            try {
                SceneChanger.changeScenes(event, "../views/dashboardView.fxml", "Home",
                        null);
            } catch (IOException e) {
                e.printStackTrace();
                msgLabel.setText(e.getMessage());
            }
        } catch (IllegalArgumentException | SQLException e ) {
            msgLabel.setText(e.getMessage());
        }

    }

    private boolean fieldsPopulated(){
        String errMsg = "The following fields are empty: ";

        if(gameNameTextView.getText().isEmpty())
            errMsg += "name, ";
        if (gameRatingTextView.getText().isEmpty())
            errMsg += "GameRating";
        if(errMsg.equals("The following fields are empty: "))
            return true;

        msgLabel.setText(errMsg.substring(0, errMsg.length()-2)+"...");
        return false;
    }

    //set a Game name, Game rating and set Users that will use this game
    public void setGame(VideoGame param) {
        game = param;

        gameNameTextView.setText(game.getName());

        gameRatingTextView.setText(String.valueOf(game.getAgeRating()));

        ArrayList<User> gameUsers = param.getUsers();

        gameUserListView.getItems().addAll(gameUsers);

        for(User us: gameUsers) {
            User found = null;

            for (int i = 0; i < allUserListView.getItems().size(); i++) {
                User currentUser = allUserListView.getItems().get(i);
                if (currentUser.getId() == us.getId()) {
                    found = currentUser;
                    break;
                }
            }

            if (found != null) {
                allUserListView.getItems().remove(found);
            }
        }
    }




}



