package scicalcsrc.scicalculator.model;

public class Calculator {
    private static String expression;

    public Calculator() {
        this.expression="";
    }
    public void setExpression(String expression) {
        this.expression = expression;
    }
    public String getExpression() {
        return expression;
    }
}
