package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import models.User;
import models.VideoGame;
import utilities.DBUtilities;
import utilities.SceneChanger;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DashboardViewController implements Initializable {

    @FXML
    private ListView<VideoGame> gameListView;

    @FXML
    private ListView<User> userListView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            userListView.getItems().addAll(DBUtilities.getUserFromDB());
            gameListView.getItems().addAll(DBUtilities.getVideoGameFromDB());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @FXML
    private void createNewUserButton(ActionEvent event) throws IOException {
        SceneChanger.changeScenes(event, "../views/createUserView.fxml", "Create New User", null);
    }

    @FXML
    private void createNewGameButton(ActionEvent event) throws IOException {
        SceneChanger.changeScenes(event, "../views/createGameView.fxml", "Create New Game", null);
    }

    @FXML
    void editGameButton(ActionEvent event) throws IOException{
        VideoGame selectedGame = gameListView.getSelectionModel().getSelectedItem();
        if(selectedGame != null){
            SceneChanger.changeScenes(event, "../views/createGameView.fxml", "Create New Game", selectedGame);
        }
    }
}


