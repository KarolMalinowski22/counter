package sample;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import sample.passwordutil.CheckAndSetPassword;
import sample.passwordutil.Password;
import sample.passwordutil.SaveState;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class Initializing {
    public static void createKeyboard(GridPane keyboard) {
        int temp;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Node node = new Button();
                temp = (9 - (i * 3) - (2 - j));
                node.setAccessibleText("" + temp);
                ((Button) node).setText("" + temp);
                ((Button) node).setMinSize(20, 12);
                ((Button) node).setPrefSize(100, 60);
                keyboard.add(node, j, i);
            }
        }
        //zero...
        Node node = new Button();
        temp = 0;
        node.setAccessibleText("" + temp);
        ((Button) node).setText("" + temp);
        ((Button) node).setMinSize(20, 12);
        ((Button) node).setPrefSize(100, 60);
        keyboard.add(node, 1, 3);
    }
    public static boolean checkForSave(CheckAndSetPassword checkAndSetPassword){
        if(SaveState.checkForSave()){
            Object o;
            try {
                FileInputStream fis = new FileInputStream("casp.ser");
                ObjectInputStream ois = new ObjectInputStream(fis);
                o = ois.readObject();
                checkAndSetPassword.setPassword((Password)o);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return true;
        }
        return false;
    }
}
