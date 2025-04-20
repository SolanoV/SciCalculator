package scicalcsrc.scicalculator;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import scicalcsrc.scicalculator.model.Calculator;
import scicalcsrc.scicalculator.model.characterButtons;
import scicalcsrc.scicalculator.model.valueButtons;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class scPanelController implements Initializable {
    private Calculator c=new Calculator();
    private valueButtons vb=new valueButtons();
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
        appendCharacter(vb.getoneButton());

    }

    @FXML
    public void twoButtonAction(ActionEvent event) {
        appendCharacter(vb.gettwoButton());
    }

    @FXML
    public void threeButtonAction(ActionEvent event) {
        appendCharacter(vb.getthreeButton());
    }

    @FXML
    public void fourButtonAction(ActionEvent event) {
        appendCharacter(vb.getfourButton());
    }

    @FXML
    public void fiveButtonAction(ActionEvent event) {
        appendCharacter(vb.getfiveButton());
    }

    @FXML
    public void sixButtonAction(ActionEvent event) {
        appendCharacter(vb.getsixButton());
    }

    @FXML
    public void sevenButtonAction(ActionEvent event) {
        appendCharacter(vb.getsevenButton());
    }

    @FXML
    public void eightButtonAction(ActionEvent event) {
        appendCharacter(vb.geteightButton());
    }

    @FXML
    public void nineButtonAction(ActionEvent event) {
        appendCharacter(vb.getnineButton());
    }

    @FXML
    public void zeroButtonAction(ActionEvent event) {
        appendCharacter(vb.getzeroButton());
    }
    @FXML
    public void decimalPointButtonAction(ActionEvent event) {
        appendCharacter(vb.getdotButton());
    }
    @FXML
    public void commaButtonAction(ActionEvent event) {
        appendCharacter(vb.getcommaButton());
    }
    @FXML
    public void moduloButtonAction(ActionEvent event) {
        appendCharacter(vb.getmoduloButton());
    }
    @FXML
    public void additionButtonAction(ActionEvent event) {
        appendCharacter(vb.getadditionButton());
    }
    @FXML
    public void subtractionButtonAction(ActionEvent event) {
        appendCharacter(vb.getsubtractionButton());
    }
    @FXML
    public void multiplicationButtonAction(ActionEvent event) {
        appendCharacter(vb.getmultiplyButton());
    }
    @FXML
    public void divisionButtonAction(ActionEvent event) {
        appendCharacter(vb.getdivideButton());
    }
    @FXML
    public void leftParenthesisButtonAction(ActionEvent event) {
        appendCharacter(vb.getleftParButton());
    }
    @FXML
    public void rightParenthesisButtonAction(ActionEvent event) {
        appendCharacter(vb.getrightParButton());
    }
    @FXML
    public void sinButtonAction(ActionEvent event) {
        appendCharacter(vb.getsinButton());
    }
    @FXML
    public void cosButtonAction(ActionEvent event) {
        appendCharacter(vb.getcosButton());
    }
    @FXML
    public void tanButtonAction(ActionEvent event) {
        appendCharacter(vb.gettanButton());
    }
    @FXML
    public void ansButtonAction(ActionEvent event) {
        appendCharacter(vb.getAnsButton());
    }
    @FXML
    public void xButtonAction(ActionEvent event) {
        appendCharacter(vb.getxButton());
    }
    @FXML
    public void fractionButtonAction(ActionEvent event) {
        appendCharacter(vb.getfrctButton());
    }
    @FXML
    public void sqrtButtonAction(ActionEvent event) {
        appendCharacter(vb.getsqrtButton());
    }
    @FXML
    public void exponentButtonAction(ActionEvent event) {
        appendCharacter(vb.getexpButton());
    }
    @FXML
    public void squaredButtonAction(ActionEvent event) {
        appendCharacter(vb.getsqrdButton());
    }
    @FXML
    public void logNButtonAction(ActionEvent event) {
        appendCharacter(vb.getlogNButton());
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
        try {
            // Remove the character before the caret
            String newText = currentText.substring(0, caretPosition - 1) + currentText.substring(caretPosition);
            insertTextField.setText(newText);
            // Update the caret position directly
            int newCaretPosition = caretPosition - 1;
            c.setCaretPosition(newCaretPosition);
            insertTextField.positionCaret(newCaretPosition);
            insertTextField.requestFocus();
            insertTextField.deselect();

            //Debugging Line
            System.out.println("Backspace applied, New Text: " + newText + ", Caret: " + c.getCaretPosition());
        } catch (Exception e) {
            elementBoundChecker();
        }


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
            c.setCaretPosition(c.getCaretPosition());
            shiftLabel.setVisible(true);
            shiftLabel.setText("Shift On");
        }
        else{
            c.setCaretPosition(c.getCaretPosition());
            c.setShift(false);
            shiftLabel.setVisible(false);
        }
    }
}