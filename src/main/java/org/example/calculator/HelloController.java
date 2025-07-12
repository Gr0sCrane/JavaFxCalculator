package org.example.calculator;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class HelloController {
    @FXML
    private Label welcomeText;
    private String[] chars = {"+/-","0",",","=","1","2","3","+","4","5","6","-","7","8","9","x","/"};
    String var = "5";
    @FXML
    GridPane pane = new GridPane();


    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText(var);
    }

    @FXML
    protected void createButton() {
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                Button button = new Button();
                button.setText(chars[i]);
                pane.add(button, i, j);
            }
        }
        System.out.println("Button created");
    }
}