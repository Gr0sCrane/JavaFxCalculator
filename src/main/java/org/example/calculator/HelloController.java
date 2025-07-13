package org.example.calculator;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class HelloController {
    @FXML
    private Label welcomeText;
    private String[] chars = {
            "7", "8", "9", "/",
            "4", "5", "6", "x",
            "1", "2", "3", "-",
            "+/-", "0", "=", "+"
    };
    @FXML
    GridPane pane = new GridPane();
    @FXML
    private void createButton() {
        int columns = 4;

        for (int i = 0; i < chars.length; i++) {
            Button button = new Button(chars[i]);

            int col = i % columns;
            int row = i / columns;

            pane.add(button, col, row);
            button.setMinSize(50, 50);
        }
    }
}