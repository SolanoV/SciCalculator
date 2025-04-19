package scicalcsrc.scicalculator;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import scicalcsrc.scicalculator.model.Calculator;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class scPanelController implements Initializable {
    private Calculator c=new Calculator();
    private int caretPosition;
    @FXML private TextField insertTextField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> insertTextField.requestFocus());

        insertTextField.caretPositionProperty().addListener((obs, oldPosition, newPosition) -> {
            caretPosition=newPosition.intValue();
            System.out.println("Caret position: " + newPosition.intValue() +
                    ", Focused: " + insertTextField.isFocused());
        });

    }

    private void appendDigit(String digit) {
        insertTextField.requestFocus();
        insertTextField.deselect();
        insertTextField.insertText(caretPosition,digit);
    }

    @FXML
    public void oneButtonAction(ActionEvent event) {
        appendDigit("1");
    }

    @FXML
    public void twoButtonAction(ActionEvent event) {
        appendDigit("2");
    }

    @FXML
    public void threeButtonAction(ActionEvent event) {
        appendDigit("3");
    }

    @FXML
    public void fourButtonAction(ActionEvent event) {
        appendDigit("4");
    }

    @FXML
    public void fiveButtonAction(ActionEvent event) {
        appendDigit("5");
    }

    @FXML
    public void sixButtonAction(ActionEvent event) {
        appendDigit("6");
    }

    @FXML
    public void sevenButtonAction(ActionEvent event) {
        appendDigit("7");
    }

    @FXML
    public void eightButtonAction(ActionEvent event) {
        appendDigit("8");
    }

    @FXML
    public void nineButtonAction(ActionEvent event) {
        appendDigit("9");
    }

    @FXML
    public void zeroButtonAction(ActionEvent event) {
        appendDigit("0");
    }
}