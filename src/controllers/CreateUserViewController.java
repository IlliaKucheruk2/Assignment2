package controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import models.User;
import utilities.DBUtilities;
import utilities.SceneChanger;
import java.io.IOException;
import java.sql.SQLException;


public class CreateUserViewController {

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField creditCardTextField;

    @FXML
    private DatePicker birthdayDate;

    @FXML
    private Label msgLabel;

    //create user and then if the button submit successfully go to home page and add to DB
    @FXML
    private void createUser(ActionEvent event){
        if (fieldsPopulated())
            try {
                User newUser = new User(nameTextField.getText(),
                        birthdayDate.getValue(),
                        Integer.parseInt(creditCardTextField.getText()),
                        0);
                DBUtilities.insertUserIntoDB(newUser);

                try {
                    SceneChanger.changeScenes(event, "../views/dashboardView.fxml", "Home",
                            null);
                } catch (IOException e) {
                    e.printStackTrace();
                    msgLabel.setText(e.getMessage());
                }
            }catch (IllegalArgumentException | SQLException e){
            msgLabel.setText(e.getMessage());
        }
    }

    private boolean fieldsPopulated(){
        String errMsg = "The following fields are empty: ";

        if(nameTextField.getText().isEmpty())
            errMsg += "name, ";
        if(birthdayDate.getValue()==null)
            errMsg += "birthday, ";
        if (creditCardTextField.getText().isEmpty())
            errMsg += "credit card, ";
        if(errMsg.equals("The following fields are empty: "))
            return true;

        msgLabel.setText(errMsg.substring(0, errMsg.length()-5));
        return false;
    }

    @FXML
    private void returnToHome(ActionEvent event) throws IOException {
        SceneChanger.changeScenes(event, "../views/dashboardView.fxml", "Home", "");
    }

}
