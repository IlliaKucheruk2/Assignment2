package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import models.User;
import java.time.LocalDate;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class CreateUserViewController {

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField creditCardTextField;

    @FXML
    private DatePicker birthdayDate;

    @FXML
    private Label msgLabel;

    @FXML
    private void createUser(){
        if (fieldsPopulated())
        try {
            User newUser = new User(nameTextField.getText(),
                    birthdayDate.getValue(),
                    Integer.parseInt(creditCardTextField.getText()));
            msgLabel.setText(newUser.toString());
        }catch (IllegalArgumentException e){
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
        msgLabel.setText(errMsg.substring(0, errMsg.length()-2));
        return false;
    }
}
