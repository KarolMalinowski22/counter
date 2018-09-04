package sample;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.GridPane;

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
    }
}
