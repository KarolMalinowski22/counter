package sample;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import sample.util.Counting;
import sample.util.Password;


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

    private String passwordExpected;
    private boolean isPaswordExpectedSet = false;
    private boolean isPaswordHiddenSet = false;
    private Password password = new Password("", "");


    public void initialize() {

        Initializing.createKeyboard(keyboard);
        for (int i = 0; i < keyboard.getChildren().size(); i++) {
            Node button = keyboard.getChildren().get(i);
            button.setOnMouseClicked(e -> {
                Counting.setPasswordGiven(button.getAccessibleText());
                display.setText(Counting.getPasswordGiven());
            });
        }
        checkButton.setOnAction(e -> {
            if (!isPaswordHiddenSet) {
                if (!isPaswordExpectedSet){
                    passwordExpected = Counting.getPasswordGiven();
                    isPaswordExpectedSet = true;
                    Counting.reset();
                    display.setText(Counting.getPasswordGiven());
                    label.setText("Set password secured:");
                }else{
                    password = new Password(Counting.getPasswordGiven(), passwordExpected);
                    isPaswordHiddenSet = true;
                    checkButton.setText("Check");
                    Counting.reset();
                    display.setText(Counting.getPasswordGiven());
                    label.setText("Password:");
                }
            }else if (password.checkPassword(Counting.getPasswordGiven())) {
                display.setText(password.getPasswordToAccess());
            }
        });
        resetButton.setOnAction(e -> {
            Counting.reset();
            display.setText(Counting.getPasswordGiven());
        });
        backButton.setOnAction(e -> {
            Counting.back();
            display.setText(Counting.getPasswordGiven());
        });
    }
}
