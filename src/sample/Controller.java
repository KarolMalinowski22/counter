package sample;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import sample.passwordutil.CheckAndSetPassword;
import sample.passwordutil.Input;
import sample.passwordutil.Password;
import sample.passwordutil.SaveState;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.io.Serializable;


public class Controller {
    @FXML
    GridPane keyboard;
    @FXML
    TextField display;
    @FXML
    Button checkButton;
    @FXML
    Button resetButton;
    @FXML
    Button backButton;
    @FXML
    Label label;
    @FXML
    ProgressBar pb;
    CheckAndSetPassword checkAndSetPassword = new CheckAndSetPassword();



    public void initialize() {
        if(Initializing.checkForSave(checkAndSetPassword)){
            label.setText("Password:");
            checkButton.setText("Check");
        }
        Initializing.createKeyboard(keyboard);
        for (int i = 0; i < keyboard.getChildren().size(); i++) {
            Node button = keyboard.getChildren().get(i);
            button.setOnMouseClicked(e -> {
                Input.writePasswordToDisplay(button.getAccessibleText());
                display.setText(Input.getPasswordGiven());
            });
        }
        checkAndSetPassword.setController(this);
        checkButton.setOnAction(e -> {
            checkAndSetPassword.checkOrSetPassword();
        });
        resetButton.setOnAction(e -> {
            if(keyboard.isDisabled()){
                disableButtons(false);
            }
            //if super password has been given
            if(Input.reset()){
                label.setText("Set password expected");
                checkButton.setText("Set");
            }
            display.setText(Input.getPasswordGiven());
        });
        backButton.setOnAction(e -> {
            Input.back();
            display.setText(Input.getPasswordGiven());
        });
    }
    public void setTextOnCheckButton(String text){
        checkButton.setText(text);
    }
    public void setTextOnDisplay(String text){
        display.setText(text);
    }
    public void setTextOnLabel(String text){
        label.setText(text);
    }

    public TextField getDisplay() {
        return display;
    }
    public void disableButtons(boolean disable){
        keyboard.setDisable(disable);
        checkButton.setDisable(disable);
        resetButton.setDisable(disable);
        backButton.setDisable(disable);
    }
    public void disableReset(boolean disable){
        resetButton.setDisable(disable);
    }

    public ProgressBar getPb() {
        return pb;
    }
}
