package org.example.calculator;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class HelloController {
    @FXML
    private Label displayLabel;
    private final String[] chars = {
            "7", "8", "9", "/",
            "4", "5", "6", "x",
            "1", "2", "3", "-",
            "+/-", "0", "=", "+","C"
    };
    @FXML
    GridPane pane = new GridPane();
    @FXML
    private void createButton() {

        displayLabel.setDisable(false);

        int columns = 4;

        for (int i = 0; i < chars.length; i++) {
            Button button = new Button(chars[i]);

            int col = i % columns;
            int row = i / columns;

            pane.add(button, col, row);
            button.setMinSize(50, 50);
            int elem = i;
            button.setOnAction(event -> {update(button,elem);});
        }
    }

    void update(Button button,int i) {
        if (i != 3 && i != 7 && i != 11 && i != 12 && i != 14 && i != 15){
            displayLabel.setText(displayLabel.getText() + button.getText());
        }
        if (i == 16){
            displayLabel.setText("");
        }
    }
}