package scicalcsrc.scicalculator;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import scicalcsrc.scicalculator.model.Calculator;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class scPanelController implements Initializable {
    private Calculator c=new Calculator();
    @FXML private TextField insertTextField;
    @FXML private Label shiftLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (insertTextField == null) {
            //Debugging Line
            System.err.println("Error: insertTextField is null in initialize");
            return;
        }

        Platform.runLater(() -> {
            insertTextField.requestFocus();
            String initialText = insertTextField.getText();
            c.setCaretPosition(initialText != null ? initialText.length() : 0);
            insertTextField.positionCaret(c.getCaretPosition());
        });

        insertTextField.caretPositionProperty().addListener((obs, oldPosition, newPosition) -> {
            c.setCaretPosition(newPosition.intValue());
            //Debugging Line
            System.out.println("Caret position: " + newPosition.intValue() +
                    ", Focused: " + insertTextField.isFocused());
        });

    }

    private void appendCharacter(String digit) {
        insertTextField.requestFocus();
        insertTextField.deselect();
        insertTextField.insertText(c.getCaretPosition(),digit);
    }

    @FXML
    public void oneButtonAction(ActionEvent event) {
        appendCharacter("1");
    }

    @FXML
    public void twoButtonAction(ActionEvent event) {
        appendCharacter("2");
    }

    @FXML
    public void threeButtonAction(ActionEvent event) {
        appendCharacter("3");
    }

    @FXML
    public void fourButtonAction(ActionEvent event) {
        appendCharacter("4");
    }

    @FXML
    public void fiveButtonAction(ActionEvent event) {
        appendCharacter("5");
    }

    @FXML
    public void sixButtonAction(ActionEvent event) {
        appendCharacter("6");
    }

    @FXML
    public void sevenButtonAction(ActionEvent event) {
        appendCharacter("7");
    }

    @FXML
    public void eightButtonAction(ActionEvent event) {
        appendCharacter("8");
    }

    @FXML
    public void nineButtonAction(ActionEvent event) {
        appendCharacter("9");
    }

    @FXML
    public void zeroButtonAction(ActionEvent event) {
        appendCharacter("0");
    }
    @FXML
    public void decimalPointButtonAction(ActionEvent event) {
        appendCharacter(".");
    }
    @FXML
    public void commaButtonAction(ActionEvent event) {
        appendCharacter(",");
    }
    @FXML
    public void moduloButtonAction(ActionEvent event) {
        appendCharacter("%");
    }
    @FXML
    public void additionButtonAction(ActionEvent event) {
        appendCharacter("+");
    }
    @FXML
    public void subtractionButtonAction(ActionEvent event) {
        appendCharacter("-");
    }
    @FXML
    public void multiplicationButtonAction(ActionEvent event) {
        appendCharacter("*");
    }
    @FXML
    public void divisionButtonAction(ActionEvent event) {
        appendCharacter("/");
    }
    @FXML
    public void leftParenthesisButtonAction(ActionEvent event) {
        appendCharacter("(");
    }
    @FXML
    public void rightParenthesisButtonAction(ActionEvent event) {
        appendCharacter(")");
    }
    @FXML
    public void sinButtonAction(ActionEvent event) {
        appendCharacter("sin()");
    }
    @FXML
    public void cosButtonAction(ActionEvent event) {
        appendCharacter("cos()");
    }
    @FXML
    public void tanButtonAction(ActionEvent event) {
        appendCharacter("tan()");
    }
    @FXML
    public void ansButtonAction(ActionEvent event) {
        appendCharacter("Ans");
    }
    @FXML
    public void xButtonAction(ActionEvent event) {
        appendCharacter("x");
    }
    @FXML
    public void fractionButtonAction(ActionEvent event) {
        appendCharacter("frct()");
    }
    @FXML
    public void sqrtButtonAction(ActionEvent event) {
        appendCharacter("sqrt()");
    }
    @FXML
    public void exponentButtonAction(ActionEvent event) {
        appendCharacter("exp()");
    }
    @FXML
    public void squaredButtonAction(ActionEvent event) {
        appendCharacter("sqrd()");
    }
    @FXML
    public void logNButtonAction(ActionEvent event) {
        appendCharacter("log()");
    }
    @FXML
    public void backspaceButtonAction(ActionEvent event) {
        if (insertTextField == null) {
            //Debugging Line
            System.err.println("Error: insertTextField is null in backspaceButtonAction");
            return;
        }

        int caretPosition = c.getCaretPosition();
        //Debugging Line
        System.out.println("Caret before backspace: " + caretPosition);

        String currentText = insertTextField.getText();
        if (currentText == null) {
            currentText = "";
        }

        // If caret is at the start, there's nothing to delete
        if (caretPosition <= 0 || currentText.isEmpty()) {
            //Debugging Line
            System.out.println("Nothing to delete: Caret at start or text empty");
            return;
        }

        // Remove the character before the caret
        String newText = currentText.substring(0, caretPosition - 1) + currentText.substring(caretPosition);
        insertTextField.setText(newText);

        // Update the caret position directly
        int newCaretPosition = caretPosition - 1;
        c.setCaretPosition(newCaretPosition);
        insertTextField.positionCaret(newCaretPosition);
        elementBoundChecker();

        insertTextField.requestFocus();
        insertTextField.deselect();

        //Debugging Line
        System.out.println("Backspace applied, New Text: " + newText + ", Caret: " + c.getCaretPosition());
    }

    @FXML
    public void clearButtonAction(ActionEvent event) {
        insertTextField.setText("");
        c.setCaretPosition(0);
        insertTextField.positionCaret(c.getCaretPosition());
        insertTextField.requestFocus();
        insertTextField.deselect();

    }

    @FXML
    public void leftButtonAction(ActionEvent event) {
        Platform.runLater(() -> {
            insertTextField.positionCaret(c.getCaretPosition()-1);
            insertTextField.requestFocus();
            insertTextField.deselect();
            elementBoundChecker();
        });

    }
    @FXML
    public void rightButtonAction(ActionEvent event) {
        Platform.runLater(() -> {
            insertTextField.positionCaret(c.getCaretPosition()+1);
            insertTextField.requestFocus();
            insertTextField.deselect();
            elementBoundChecker();
        });

    }
    public void elementBoundChecker(){
        if(c.getCaretPosition()<0){
            c.setCaretPosition(0);
            insertTextField.positionCaret(c.getCaretPosition());
            insertTextField.requestFocus();
            insertTextField.deselect();
        }
        else if(c.getCaretPosition()>insertTextField.getText().length()){
            c.setCaretPosition(insertTextField.getText().length());
            insertTextField.positionCaret(c.getCaretPosition());
            insertTextField.requestFocus();
            insertTextField.deselect();
        }
    }
    public void shiftButtonAction(ActionEvent event) {
        if(c.getShift()==false){
            c.setShift(true);
            shiftLabel.setVisible(true);
            shiftLabel.setText("Shift On");
        }
        else{
            c.setShift(false);
            shiftLabel.setVisible(false);
        }
    }
}