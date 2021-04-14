package utilities;

import controllers.CreateGameViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.VideoGame;

import java.io.IOException;

public class SceneChanger {

    public static void changeScenes(ActionEvent event,String pathToFXML, String title, Object editedObject) throws IOException {
        FXMLLoader loader = new FXMLLoader(SceneChanger.class.getResource(pathToFXML));
        Parent root = loader.load();

        // FXMLLoader loader = new FXMLLoader();
        // Parent root = loader.load(SceneChanger.class.getResource(pathToFXML));
        Scene scene = new Scene(root);

        //get the stage from the event that was passed in
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.setTitle(title);

        if (editedObject != null && editedObject instanceof VideoGame) {
            CreateGameViewController ctrl = loader.getController();
            ctrl.setGame((VideoGame)editedObject);
        }

        stage.show();
    }
}