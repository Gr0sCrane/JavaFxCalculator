package org.example.calculator;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.List;

public class HelloController {
    @FXML
    private Label displayLabel;
    @FXML
    private Label operatorLabel;
    private boolean operatorchoiced = false;
    private String[] operator = new String[1];
    private List<String> numbers = new ArrayList<>();
    private final String[] chars = {
            "7", "8", "9", "/",
            "4", "5", "6", "x",
            "1", "2", "3", "-",
            "+/-", "0", "=", "+","C"
    };
    @FXML
    GridPane pane = new GridPane();



    @FXML
    private void init() {

        displayLabel.setDisable(false);
        operatorLabel.setDisable(false);

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
        /*
        3 => /  | 12 => +/-
        7 => x  | 14 => =
        11 => - | 15 => +
        16 => C |
         */
        if (i == 16){
            displayLabel.setText("");
        }
        else if (i != 3 && i != 7 && i != 11 && i != 12 && i != 14 && i != 15){
            displayLabel.setText(displayLabel.getText() + button.getText());
        } else {
        String op = button.getText();
        operation(op);
        }
        if (operatorchoiced) {
            numbers.add(displayLabel.getText());
            displayLabel.setText("");
            operatorchoiced = false;
            operatorLabel.setDisable(true);
        }
        System.out.println(numbers.toString());
    }

    void operation(String op) {
        switch (op){
            case "+", "/", "x", "-":
                operator[0] = op;
                operatorLabel.setText(op);
                operatorchoiced = true;
                break;
            default:
                break;
        }
    }

    public void initialize() {

        Font.loadFont(getClass().getResource("/font/digital-7.ttf").toExternalForm(), 12);

        init();
    }
}