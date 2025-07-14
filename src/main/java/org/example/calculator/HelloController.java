package org.example.calculator;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import java.util.Stack;

public class HelloController {
    @FXML
    private Label displayLabel;
    @FXML
    private Label operatorLabel;
    @FXML
    private Label resultLabel;
    private boolean operatorchoiced = false;
    private String[] operator = new String[1];
    private Stack<String> numbers = new Stack<>();
    private Stack<Double> resultStack = new Stack<>();

    private final String[] chars = {
            "7", "8", "9", "/",
            "4", "5", "6", "x",
            "1", "2", "3", "-",
            "+/-", "0", "=", "+","C","R"
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
        from chars[]
        ---------------------
        3 => /  | 12 => +/-
        7 => x  | 14 => =
        11 => - | 15 => +
        16 => C | 17 => R
        ---------------------
         */
        String op = button.getText();

        if (i == 16){
            displayLabel.setText("");
            operatorLabel.setText("");
            numbers.clear();
            resultStack.clear();
            resultLabel.setText("");
        }
        else if (i == 14) {
            if (!displayLabel.getText().isEmpty()) {
                numbers.push(displayLabel.getText());
            }

            if (numbers.size() < 2 || operator[0] == null) {
                return;
            }

            selectCalc(operator[0]);

            if (!resultStack.isEmpty()) {
                resultLabel.setText(String.valueOf(resultStack.peek()));
            }

            operatorLabel.setText("");
            operator[0] = null;
            numbers.clear();
        } else if (i == 17){
            if (!resultStack.isEmpty()) {
                String retenue = resultStack.pop().toString();
                displayLabel.setText(retenue);
            }
        }
        else if (i != 3 && i != 7 && i != 11 && i != 12 && i != 15){
            displayLabel.setText(displayLabel.getText() + button.getText());
        } else {
        operation(op);
        }
    }

    void operation(String op) {
        switch (op){
            case "+", "/", "x", "-":
                numbers.push(displayLabel.getText());
                operator[0] = op;
                operatorLabel.setText(op);
                operatorchoiced = true;
                displayLabel.setText("");
                break;
            default:
                break;
        }
    }

    public void initialize() {

        Font.loadFont(getClass().getResource("/font/digital-7.ttf").toExternalForm(), 12);

        init();
    }

    public void selectCalc(String operator){
        if (numbers.size() <= 1) {
            throw new IllegalArgumentException();
        }
        String nbr1 = numbers.pop();
        String nbr2 = numbers.pop();
        double nbr1_conv = convertStringToNumber(nbr1);
        double nbr2_conv = convertStringToNumber(nbr2);

        switch (operator){
            case "+":
                double add_result = Operations.addition(nbr2_conv,nbr1_conv);
                resultStack.addFirst(add_result);
                break;
            case "-":
                double sub_result = Operations.subtract(nbr2_conv,nbr1_conv);
                resultStack.addFirst(sub_result);
                break;
            case "x":
                double mov_result = Operations.multiply(nbr2_conv,nbr1_conv);
                resultStack.addFirst(mov_result);
                break;
            case "/":
                double div_result = Operations.divide(nbr2_conv,nbr1_conv);
                resultStack.addFirst(div_result);
                break;
                default:
                    break;
        }
        System.out.println("Result stack: " + resultStack.toString());
    }

    public double convertStringToNumber(String number) {
        return Double.parseDouble(number);
    }
}