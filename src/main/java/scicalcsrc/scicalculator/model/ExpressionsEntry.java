package scicalcsrc.scicalculator.model;

public class ExpressionsEntry {//For history entry of expressions
    String expression;
    String answer;

    ExpressionsEntry(String expression, String answer) {
        this.expression = expression;
        this.answer = answer;
    }

    @Override
    public String toString() {//for debugging purposes
        return "Expression: " + expression + ", Answer: " + answer;
    }
    public String getExpression() {//returns the String expression
        return expression;
    }
    public String getAnswer() {//returns the String Answer
        return answer;
    }

}//SOLANO
