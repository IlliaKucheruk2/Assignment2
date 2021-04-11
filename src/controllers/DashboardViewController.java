package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import models.User;
import models.VideoGame;
import utilities.DBUtilities;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DashboardViewController implements Initializable {

    @FXML
    private Label gamesLabel;

    @FXML
    private ListView<VideoGame> gameListView;

    @FXML
    private Label usersLabel;

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
        Parent root = FXMLLoader.load(getClass().getResource("../views/createUserView.fxml"));
        Scene scene = new Scene(root);

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.setTitle("Create New User");
        stage.show();
    }
}


