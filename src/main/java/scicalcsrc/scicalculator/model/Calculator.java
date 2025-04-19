package scicalcsrc.scicalculator.model;

public class Calculator {
    private static String expression;
    private static int caretPosition;
    private static boolean shift;

    public Calculator() {
        this.expression="";
        this.caretPosition=0;
        this.shift=false;
    }
    public void setExpression(String expression) {
        this.expression = expression;
    }
    public String getExpression() {
        return expression;
    }
    public void setCaretPosition(int caretPosition) {
        this.caretPosition = caretPosition;
    }
    public int getCaretPosition() {
        return caretPosition;
    }
    public void setShift(boolean shift) {
        this.shift = shift;
    }
    public boolean getShift() {
        return shift;
    }
}
