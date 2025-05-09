package scicalcsrc.scicalculator.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import scicalcsrc.scicalculator.model.Calculator;
import scicalcsrc.scicalculator.model.ExpressionsEntry;
import scicalcsrc.scicalculator.model.valueButtons;

import java.net.URL;
import java.util.ResourceBundle;

public class scPanelController implements Initializable {
    private Calculator c = new Calculator();
    private valueButtons vb = new valueButtons();
    private int index = 0;
    @FXML private TextField insertTextField;
    @FXML private Label shiftLabel;
    @FXML private Label resultLabel;
    @FXML private Label formatLabel;

    @Override // Initializes what happens at the start of the application
    public void initialize(URL location, ResourceBundle resources) {
        c.setFormat("Degree");

        if (insertTextField == null) {
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
            System.out.println("Caret position: " + newPosition.intValue() +
                    ", Focused: " + insertTextField.isFocused());
        });

        insertTextField.addEventHandler(KeyEvent.KEY_TYPED, this::handleKeyTyped);
        insertTextField.addEventHandler(KeyEvent.KEY_PRESSED, this::handleKeyPressed);
    }

    private void handleKeyTyped(KeyEvent event) {//handles which keys are allowed to be pressed
        String character = event.getCharacter();
        if (character == null || character.isEmpty()) {
            return;
        }

        if (character.matches("[0-9\\.,\\*/\\-+\\%\\(\\)xX]")) {
            appendCharacter(character);
            event.consume();
        }
    }

    private void handleKeyPressed(KeyEvent event) {
    /* Executes the expression when Pressing ENTER button
    / This is to allow evaluating the entered expression without having to
     manually press the EXE button in the application */
        if (event.getCode() == KeyCode.ENTER) {
            executeButtonAction(new ActionEvent());
            event.consume();
        }
    }

    private void appendCharacter(String digit) { //Appends the character/String into the textField
        insertTextField.requestFocus();
        insertTextField.deselect();
        insertTextField.insertText(c.getCaretPosition(), digit);
        c.setExpression(insertTextField.getText());
        String currentText = c.getExpression();
        System.out.println("current expression: " + currentText);
    }

    @FXML
    public void oneButtonAction(ActionEvent event) {//Appends 1 - Shift: D
        appendCharacter(vb.getoneButton());
        if (c.getShift()) shiftButtonAction(event);
    }

    @FXML
    public void twoButtonAction(ActionEvent event) {//Appends 2 - Shift: E
        appendCharacter(vb.gettwoButton());
        if (c.getShift()) shiftButtonAction(event);
    }

    @FXML
    public void threeButtonAction(ActionEvent event) {//Appends 3 - Shift: F
        appendCharacter(vb.getthreeButton());
        if (c.getShift()) shiftButtonAction(event);
    }

    @FXML
    public void fourButtonAction(ActionEvent event) {//Appends 4 - Shift: A
        appendCharacter(vb.getfourButton());
        if (c.getShift()) shiftButtonAction(event);
    }

    @FXML
    public void fiveButtonAction(ActionEvent event) {//Appends 5 - Shift: B
        appendCharacter(vb.getfiveButton());
        if (c.getShift()) shiftButtonAction(event);
    }

    @FXML
    public void sixButtonAction(ActionEvent event) {//Appends 6 - Shift: C
        appendCharacter(vb.getsixButton());
        if (c.getShift()) shiftButtonAction(event);
    }

    @FXML
    public void sevenButtonAction(ActionEvent event) {//Appends 7 - Shift: π
        appendCharacter(vb.getsevenButton());
        if (c.getShift()) shiftButtonAction(event);
    }

    @FXML
    public void eightButtonAction(ActionEvent event) {//Appends 8 - Shift: e
        appendCharacter(vb.geteightButton());
        if (c.getShift()) shiftButtonAction(event);
    }

    @FXML
    public void nineButtonAction(ActionEvent event) {//Appends 9 - Shift: i
        appendCharacter(vb.getnineButton());
        if (c.getShift()) shiftButtonAction(event);
    }

    @FXML
    public void zeroButtonAction(ActionEvent event) {//Appends 0 - Shift: x
        appendCharacter(vb.getzeroButton());
        if (c.getShift()) shiftButtonAction(event);
    }

    @FXML
    public void decimalPointButtonAction(ActionEvent event) {//Appends "." - Shift: y
        appendCharacter(vb.getdotButton());
        if (c.getShift()) shiftButtonAction(event);
    }

    @FXML
    public void commaButtonAction(ActionEvent event) {//Appends "," - Shift: z
        appendCharacter(vb.getcommaButton());
        if (c.getShift()) shiftButtonAction(event);
    }

    @FXML
    public void moduloButtonAction(ActionEvent event) {//Appends "%"
        appendCharacter(vb.getmoduloButton());
        if (c.getShift()) shiftButtonAction(event);
    }

    @FXML
    public void additionButtonAction(ActionEvent event) {//Appends "+"
        appendCharacter(vb.getadditionButton());
        if (c.getShift()) shiftButtonAction(event);
    }

    @FXML
    public void subtractionButtonAction(ActionEvent event) {//Appends "-"
        appendCharacter(vb.getsubtractionButton());
        if (c.getShift()) shiftButtonAction(event);
    }

    @FXML
    public void multiplicationButtonAction(ActionEvent event) {//Appends "*"
        appendCharacter(vb.getmultiplyButton());
        if (c.getShift()) shiftButtonAction(event);
    }

    @FXML
    public void divisionButtonAction(ActionEvent event) {//Appends "/"
        appendCharacter(vb.getdivideButton());
        if (c.getShift()) shiftButtonAction(event);
    }

    @FXML
    public void leftParenthesisButtonAction(ActionEvent event) {//Appends "("
        appendCharacter(vb.getleftParButton());
        if (c.getShift()) shiftButtonAction(event);
    }

    @FXML
    public void rightParenthesisButtonAction(ActionEvent event) {//Appends ")"
        appendCharacter(vb.getrightParButton());
        if (c.getShift()) shiftButtonAction(event);
    }

    @FXML
    public void sinButtonAction(ActionEvent event) {//Appends "sin()" - Shift: "csc()"
        appendCharacter(vb.getsinButton());
        if (c.getShift()) shiftButtonAction(event);
    }

    @FXML
    public void cosButtonAction(ActionEvent event) {//Appends "cos()" - Shift: "sec()"
        appendCharacter(vb.getcosButton());
        if (c.getShift()) shiftButtonAction(event);
    }

    @FXML
    public void tanButtonAction(ActionEvent event) {//Appends "tan()" - Shift: "cot()"
        appendCharacter(vb.gettanButton());
        if (c.getShift()) shiftButtonAction(event);
    }

    @FXML
    public void ansButtonAction(ActionEvent event) {//Appends "Ans()"
        appendCharacter(vb.getAnsButton());
        if (c.getShift()) shiftButtonAction(event);
    }

    @FXML
    public void absoluteValueButtonAction(ActionEvent event) {//Appends "||"
        appendCharacter(vb.getAbsButton());
        if (c.getShift()) shiftButtonAction(event);
    }

    @FXML
    public void fractionButtonAction(ActionEvent event) {//Appends "(/)" - Shift: "arcsin()"
        appendCharacter(vb.getfrctButton());
        if (c.getShift()) shiftButtonAction(event);
    }

    @FXML
    public void sqrtButtonAction(ActionEvent event) {//Appends "√" Shift: "arccos()"
        appendCharacter(vb.getsqrtButton());
        if (c.getShift()) shiftButtonAction(event);
    }

    @FXML
    public void exponentButtonAction(ActionEvent event) {//Appends "^()" Shift: "arctan()"
        appendCharacter(vb.getexpButton());
        if (c.getShift()) shiftButtonAction(event);
    }

    @FXML
    public void squaredButtonAction(ActionEvent event) {//Appends "^(2)"
        appendCharacter(vb.getsqrdButton());
        if (c.getShift()) shiftButtonAction(event);
    }

    @FXML
    public void logNButtonAction(ActionEvent event) {//Appends "log(,)"
        appendCharacter(vb.getlogNButton());
        if (c.getShift()) shiftButtonAction(event);
    }

    @FXML
    public void log10ButtonAction(ActionEvent event) {//Appends "log(10,)"
        appendCharacter(vb.getlog10Button());
        if (c.getShift()) shiftButtonAction(event);
    }

    @FXML
    public void naturalLogButtonAction(ActionEvent event) {//Appends "ln()"
        appendCharacter(vb.getlnButton());
        if (c.getShift()) shiftButtonAction(event);
    }

    @FXML
    public void inverseButtonAction(ActionEvent event) {//Appends "^(-1)"
        appendCharacter(vb.getinverseButton());
        if (c.getShift()) shiftButtonAction(event);
    }

    @FXML
    public void customRootButtonAction(ActionEvent event) {//Appends "√(,)"
        appendCharacter(vb.getcustomRootButton());
        if (c.getShift()) shiftButtonAction(event);
    }

    @FXML
    public void backspaceButtonAction(ActionEvent event) {//Removes 1 character to left of cursor/pointer
        if (insertTextField == null) {
            System.err.println("Error: insertTextField is null in backspaceButtonAction");
            return;
        }

        int caretPosition = c.getCaretPosition();
        System.out.println("Caret before backspace: " + caretPosition);

        String currentText = insertTextField.getText();
        if (currentText == null) {
            currentText = "";
        }

        if (caretPosition <= 0 || currentText.isEmpty()) {
            System.out.println("Nothing to delete: Caret at start or text empty");
            return;
        }
        try {
            String newText = currentText.substring(0, caretPosition - 1) + currentText.substring(caretPosition);
            insertTextField.setText(newText);
            int newCaretPosition = caretPosition - 1;
            c.setCaretPosition(newCaretPosition);
            insertTextField.positionCaret(newCaretPosition);
            insertTextField.requestFocus();
            insertTextField.deselect();
            c.setExpression(insertTextField.getText());
            System.out.println("Backspace applied, New Text: " + newText + ", Caret: " + c.getCaretPosition());
        } catch (Exception e) {
            elementBoundChecker();
        }
    }

    @FXML
    public void clearButtonAction(ActionEvent event) {//clears the TextField
        insertTextField.setText("");
        resultLabel.setText("");
        c.setCaretPosition(0);
        insertTextField.positionCaret(c.getCaretPosition());
        insertTextField.requestFocus();
        insertTextField.deselect();
    }

    @FXML
    public void leftButtonAction(ActionEvent event) {//moves the cursor to the left of the text
        Platform.runLater(() -> {
            insertTextField.positionCaret(c.getCaretPosition() - 1);
            insertTextField.requestFocus();
            insertTextField.deselect();
            elementBoundChecker();
        });
    }

    @FXML
    public void rightButtonAction(ActionEvent event) {//moves the cursor to the right of the text
        Platform.runLater(() -> {
            insertTextField.positionCaret(c.getCaretPosition() + 1);
            insertTextField.requestFocus();
            insertTextField.deselect();
            elementBoundChecker();
        });
    }

    @FXML
    public void upButtonAction(ActionEvent event) {//traverses the history of the equations upwards
        Platform.runLater(() -> {
            expressionsEntryChecker();
            index--;
            ExpressionsEntry entry = c.getHistoryEntry(index);
            if (entry != null) {
                insertTextField.setText(entry.getExpression());
                resultLabel.setText(entry.getAnswer());
            }
            c.setCaretPosition(c.getCaretPosition());
            insertTextField.positionCaret(c.getCaretPosition());
            insertTextField.requestFocus();
            insertTextField.deselect();
        });
    }

    @FXML
    public void downButtonAction(ActionEvent event) {//traverses the history of the equations downwards
        Platform.runLater(() -> {
            expressionsEntryChecker();
            index++;
            ExpressionsEntry entry = c.getHistoryEntry(index);
            if (entry != null) {
                insertTextField.setText(entry.getExpression());
                resultLabel.setText(entry.getAnswer());
            }
            c.setCaretPosition(c.getCaretPosition());
            insertTextField.positionCaret(c.getCaretPosition());
            insertTextField.requestFocus();
            insertTextField.deselect();
        });
    }
    public void expressionsEntryChecker(){//checks and see if the entrySize is beyond the size of the entries
        if(index<0){
            index=0;
        }
        else if(index>c.entrySize()-1){
            index=c.entrySize()-1;
        }
    }

    public void elementBoundChecker() {//checks to see if the cursor is outofbounds, resets it back to 0 or the current length of the text
        if (c.getCaretPosition() < 0) {
            c.setCaretPosition(0);
            insertTextField.positionCaret(c.getCaretPosition());
            insertTextField.requestFocus();
            insertTextField.deselect();
        } else if (c.getCaretPosition() > insertTextField.getText().length()) {
            c.setCaretPosition(insertTextField.getText().length());
            insertTextField.positionCaret(c.getCaretPosition());
            insertTextField.requestFocus();
            insertTextField.deselect();
        }
    }

    @FXML
    public void shiftButtonAction(ActionEvent event) {//toggle the shiftButton, if shifted it changes the appended value of some buttons
        if (!c.getShift()) {
            c.setShift(true);
            c.setCaretPosition(c.getCaretPosition());
            shiftLabel.setVisible(true);
            shiftLabel.setText("Shift On");
        } else {
            c.setCaretPosition(c.getCaretPosition());
            c.setShift(false);
            shiftLabel.setVisible(false);
        }
    }

    @FXML
    public void executeButtonAction(ActionEvent event) {//executes and evaluates the expression, shows the answer.
        index++;
        c.evaluate(c.getExpression());
        resultLabel.setText(c.getAnswer());
        c.setCaretPosition(c.getCaretPosition());
        insertTextField.positionCaret(c.getCaretPosition());
        insertTextField.requestFocus();
        insertTextField.deselect();
    }

    @FXML
    public void exitButtonAction(ActionEvent event) {//exits the program
        Platform.exit();
    }

    @FXML
    public void formatButtonAction(ActionEvent event) {//changes the format from degree to radian
        String currentExpression = insertTextField.getText();
        if (c.getFormat().equals("Degree")) {
            c.setFormat("Radian");
            formatLabel.setText("Radian");
            System.out.println("Format: " + c.getFormat());
        } else {
            c.setFormat("Degree");
            formatLabel.setText("Degree");
            System.out.println("Format: " + c.getFormat());
        }
        // Re-evaluate the current expression in the new format
        if (currentExpression != null && !currentExpression.trim().isEmpty()) {
            c.evaluate(currentExpression);
            resultLabel.setText(c.getAnswer());
        }
        c.setCaretPosition(c.getCaretPosition());
        insertTextField.positionCaret(c.getCaretPosition());
        insertTextField.requestFocus();
        insertTextField.deselect();
    }
}//SOLANO, ALFONSO