package scicalcsrc.scicalculator.model;

import java.util.*;

public class Calculator {
    private static String expression;
    private static int caretPosition;
    private static boolean shift;
    private static String Answer;
    private static String lastAnswer;
    private static String format;
    private static ArrayList<ExpressionsEntry> history = new ArrayList<>();

    public Calculator() {//default constructor
        this.expression = "";
        this.caretPosition = 0;
        this.shift = false;
        this.Answer = "";
        this.format = "Degree";
    }

    public void setExpression(String expression) {//sets the expression variable
        this.expression = expression;
    }

    public String getExpression() {//gets the current expression variable
        return expression;
    }

    public void setCaretPosition(int caretPosition) {//sets the CaretPosition(Cursor PositioN)
        this.caretPosition = caretPosition;
    }

    public int getCaretPosition() {//gets the CaretPosition(Cursor Position)
        return caretPosition;
    }

    public void setShift(boolean shift) {//sets shift value
        this.shift = shift;
    }

    public boolean getShift() {//gets the shift value
        return shift;
    }

    public void setAnswer(String Answer) {//sets the Answer
        this.Answer = Answer;
    }

    public String getAnswer() {//gets the Answer
        return Answer;
    }

    public void setFormat(String format) {//sets the Format
        this.format = format;
    }

    public String getFormat() {//gets the Format
        return format;
    }

    private static double toRadians(double degrees) {//changes the format to Radians
        return Math.toRadians(degrees);
    }

    private static double toDegrees(double radians) {//changes the format to Degrees
        return Math.toDegrees(radians);
    }
    public static int entrySize(){
        return history.size();
    }

    public static ExpressionsEntry getHistoryEntry(int index) {//gets the entry based on the index size
        if (history.isEmpty()) {
            System.out.println("History is empty");
            return null;
        }
        if (index < 0 || index >= history.size()) {
            System.out.println("Invalid index: " + index + ", History size: " + history.size());
            return null;
        }
        return history.get(index);
    }

    public static void evaluate(String expression) {//evaluates the expression
        if (expression == null || expression.trim().isEmpty()) {
            Answer = "Syntax Error";
            lastAnswer = Answer;
            addToHistory(expression, Answer);
            return;
        }

        expression = expression.replaceAll("\\s+", "");

        Deque<Double> numbers = new ArrayDeque<>();
        Deque<Character> operators = new ArrayDeque<>();

        try {
            int i = 0;
            while (i < expression.length()) {
                char c = expression.charAt(i);

                // Handle negative sign outside parentheses
                if (c == '-' && i + 1 < expression.length() && expression.charAt(i + 1) == '(') {
                    int negationCount = 0;
                    while (i < expression.length() && expression.charAt(i) == '-') {
                        negationCount++;
                        i++;
                    }
                    if (i >= expression.length() || expression.charAt(i) != '(') {
                        throw new IllegalArgumentException();
                    }
                    int parenCount = 1;
                    i++;
                    StringBuilder subExpr = new StringBuilder();
                    while (i < expression.length() && parenCount > 0) {
                        char ch = expression.charAt(i);
                        if (ch == '(') parenCount++;
                        if (ch == ')') parenCount--;
                        if (parenCount == 0) break;
                        subExpr.append(ch);
                        i++;
                    }
                    if (parenCount != 0 || i >= expression.length() || expression.charAt(i) != ')') {
                        throw new IllegalArgumentException();
                    }
                    evaluate(subExpr.toString());
                    if (Answer.equals("Syntax Error")) {
                        throw new IllegalArgumentException();
                    }
                    double result = Double.parseDouble(Answer);
                    if (negationCount % 2 == 1) {
                        result = -result;
                    }
                    numbers.push(result);
                    i++;
                    if (i < expression.length()) {
                        char next = expression.charAt(i);
                        if (next == '(' || next == '√' || next == '|' ||
                                expression.startsWith("log", i) || expression.startsWith("ln", i) ||
                                expression.startsWith("sin", i) || expression.startsWith("cos", i) ||
                                expression.startsWith("tan", i) || expression.startsWith("csc", i) ||
                                expression.startsWith("sec", i) || expression.startsWith("cot", i) ||
                                expression.startsWith("arcsin", i) || expression.startsWith("arccos", i) ||
                                expression.startsWith("arctan", i)) {
                            while (!operators.isEmpty() &&
                                    hasHigherPrecedence(operators.peek(), '*') &&
                                    operators.peek() != '(' && operators.peek() != '|') {
                                performOperation(numbers, operators);
                            }
                            operators.push('*');
                        }
                    }
                }
                // Handle absolute value
                else if (c == '|') {
                    i++;
                    int absCount = 1;
                    int parenCount = 0;
                    StringBuilder subExpr = new StringBuilder();
                    while (i < expression.length() && absCount > 0) {
                        char ch = expression.charAt(i);
                        if (ch == '|') {
                            if (parenCount == 0) absCount--;
                            else subExpr.append(ch);
                        }
                        if (ch == '(') parenCount++;
                        if (ch == ')') {
                            if (parenCount == 0) throw new IllegalArgumentException();
                            parenCount--;
                            subExpr.append(ch);
                        }
                        if (absCount == 0) break;
                        if (ch != '|') subExpr.append(ch);
                        i++;
                    }
                    if (absCount != 0 || i >= expression.length() || expression.charAt(i) != '|') {
                        throw new IllegalArgumentException();
                    }
                    if (parenCount != 0) {
                        throw new IllegalArgumentException();
                    }
                    evaluate(subExpr.toString());
                    if (Answer.equals("Syntax Error")) {
                        throw new IllegalArgumentException();
                    }
                    double result = Math.abs(Double.parseDouble(Answer));
                    numbers.push(result);
                    i++;
                    if (i < expression.length()) {
                        char next = expression.charAt(i);
                        if (next == '(' || next == '√' || next == '|' ||
                                expression.startsWith("log", i) || expression.startsWith("ln", i) ||
                                expression.startsWith("sin", i) || expression.startsWith("cos", i) ||
                                expression.startsWith("tan", i) || expression.startsWith("csc", i) ||
                                expression.startsWith("sec", i) || expression.startsWith("cot", i) ||
                                expression.startsWith("arcsin", i) || expression.startsWith("arccos", i) ||
                                expression.startsWith("arctan", i)) {
                            while (!operators.isEmpty() &&
                                    hasHigherPrecedence(operators.peek(), '*') &&
                                    operators.peek() != '(' && operators.peek() != '|') {
                                performOperation(numbers, operators);
                            }
                            operators.push('*');
                        }
                    }
                }
                // Handle numbers
                else if (Character.isDigit(c) || c == '.' || (c == '-' && (i == 0 || expression.charAt(i - 1) == '(' || expression.charAt(i - 1) == '|' || "+-*/^%".indexOf(expression.charAt(i - 1)) >= 0))) {
                    StringBuilder num = new StringBuilder();
                    boolean hasDecimal = false;
                    if (c == '-') {
                        num.append(c);
                        i++;
                        if (i >= expression.length() || (!Character.isDigit(expression.charAt(i)) && expression.charAt(i) != '.')) {
                            throw new IllegalArgumentException();
                        }
                    }
                    if (i < expression.length() && expression.charAt(i) == '.') {
                        hasDecimal = true;
                        num.append(expression.charAt(i));
                        i++;
                    }
                    while (i < expression.length() && Character.isDigit(expression.charAt(i))) {
                        num.append(expression.charAt(i));
                        i++;
                    }
                    if (i < expression.length() && expression.charAt(i) == '.' && !hasDecimal) {
                        hasDecimal = true;
                        num.append(expression.charAt(i));
                        i++;
                        while (i < expression.length() && Character.isDigit(expression.charAt(i))) {
                            num.append(expression.charAt(i));
                            i++;
                        }
                    }
                    if (num.length() == 0 || num.toString().equals("-") || num.toString().equals("-.") || num.toString().equals(".")) {
                        throw new IllegalArgumentException();
                    }
                    numbers.push(Double.parseDouble(num.toString()));
                    if (i < expression.length()) {
                        char next = expression.charAt(i);
                        if (next == '(' || next == '√' || next == '|' ||
                                expression.startsWith("log", i) || expression.startsWith("ln", i) ||
                                expression.startsWith("sin", i) || expression.startsWith("cos", i) ||
                                expression.startsWith("tan", i) || expression.startsWith("csc", i) ||
                                expression.startsWith("sec", i) || expression.startsWith("cot", i) ||
                                expression.startsWith("arcsin", i) || expression.startsWith("arccos", i) ||
                                expression.startsWith("arctan", i)) {
                            while (!operators.isEmpty() &&
                                    hasHigherPrecedence(operators.peek(), '*') &&
                                    operators.peek() != '(' && operators.peek() != '|') {
                                performOperation(numbers, operators);
                            }
                            operators.push('*');
                        }
                    }
                }
                // Handle special constants
                else if (c == 'π' || c == 'e' || c == 'i' || expression.startsWith("Ans", i)) {
                    if (c == 'i') {
                        throw new IllegalArgumentException();
                    }
                    if (expression.startsWith("Ans", i)) {
                        if (lastAnswer == null || lastAnswer.equals("Syntax Error")) {
                            throw new IllegalArgumentException();
                        }
                        numbers.push(Double.parseDouble(lastAnswer));
                        i += 3;
                    } else {
                        numbers.push(c == 'π' ? Math.PI : Math.E);
                        i++;
                    }
                    if (i < expression.length()) {
                        char next = expression.charAt(i);
                        if (next == '(' || next == '√' || next == '|' ||
                                expression.startsWith("log", i) || expression.startsWith("ln", i) ||
                                expression.startsWith("sin", i) || expression.startsWith("cos", i) ||
                                expression.startsWith("tan", i) || expression.startsWith("csc", i) ||
                                expression.startsWith("sec", i) || expression.startsWith("cot", i) ||
                                expression.startsWith("arcsin", i) || expression.startsWith("arccos", i) ||
                                expression.startsWith("arctan", i)) {
                            while (!operators.isEmpty() &&
                                    hasHigherPrecedence(operators.peek(), '*') &&
                                    operators.peek() != '(' && operators.peek() != '|') {
                                performOperation(numbers, operators);
                            }
                            operators.push('*');
                        }
                    }
                }
                // Handle operators and parentheses
                else if (c == '(') {
                    operators.push(c);
                    i++;
                }
                else if (c == ')') {
                    while (!operators.isEmpty() && operators.peek() != '(' && operators.peek() != '|') {
                        performOperation(numbers, operators);
                    }
                    if (!operators.isEmpty()) {
                        operators.pop();
                    } else {
                        throw new IllegalArgumentException();
                    }
                    if (i + 1 < expression.length()) {
                        char next = expression.charAt(i + 1);
                        if (next == '(' || next == '√' || next == '|' ||
                                expression.startsWith("log", i + 1) || expression.startsWith("ln", i + 1) ||
                                expression.startsWith("sin", i + 1) || expression.startsWith("cos", i + 1) ||
                                expression.startsWith("tan", i + 1) || expression.startsWith("csc", i + 1) ||
                                expression.startsWith("sec", i + 1) || expression.startsWith("cot", i + 1) ||
                                expression.startsWith("arcsin", i + 1) || expression.startsWith("arccos", i + 1) ||
                                expression.startsWith("arctan", i + 1)) {
                            while (!operators.isEmpty() &&
                                    hasHigherPrecedence(operators.peek(), '*') &&
                                    operators.peek() != '(' && operators.peek() != '|') {
                                performOperation(numbers, operators);
                            }
                            operators.push('*');
                        } else if (Character.isDigit(next) || next == '.' || next == 'π' || next == 'e' || next == 'i' || next == '-' || expression.startsWith("Ans", i + 1)) {
                            throw new IllegalArgumentException();
                        }
                    }
                    i++;
                }
                // Modified: Add % to operators
                else if (c == '+' || c == '-' || c == '*' || c == '/' || c == '^' || c == '%') {
                    while (!operators.isEmpty() &&
                            hasHigherPrecedence(operators.peek(), c) &&
                            operators.peek() != '(' && operators.peek() != '|') {
                        performOperation(numbers, operators);
                    }
                    operators.push(c);
                    i++;
                }
                // Handle root
                else if (c == '√') {
                    if (i + 1 >= expression.length() || expression.charAt(i + 1) != '(') {
                        throw new IllegalArgumentException();
                    }
                    i += 2;
                    StringBuilder num1 = new StringBuilder();
                    StringBuilder num2 = new StringBuilder();
                    boolean hasComma = false;
                    int parenCount = 1;
                    while (i < expression.length() && parenCount > 0) {
                        char ch = expression.charAt(i);
                        if (ch == '(') parenCount++;
                        if (ch == ')') parenCount--;
                        if (parenCount == 0) break;
                        if (ch == ',' && parenCount == 1) {
                            hasComma = true;
                        } else if (!hasComma) {
                            num1.append(ch);
                        } else {
                            num2.append(ch);
                        }
                        i++;
                    }
                    if (parenCount != 0 || i >= expression.length() || expression.charAt(i) != ')') {
                        throw new IllegalArgumentException();
                    }
                    double base = evaluateSubExpression(num1.toString().trim());
                    int rootIndex = 2;
                    if (hasComma) {
                        double root = evaluateSubExpression(num2.toString().trim());
                        if (root != Math.floor(root) || root <= 0) {
                            throw new IllegalArgumentException();
                        }
                        rootIndex = (int) root;
                    }
                    if (rootIndex % 2 == 0 && base < 0) {
                        throw new IllegalArgumentException();
                    }
                    double result = Math.pow(base, 1.0 / rootIndex);
                    if (base < 0 && rootIndex % 2 == 1) {
                        result = -Math.pow(-base, 1.0 / rootIndex);
                    }
                    numbers.push(result);
                    i++;
                }
                // Handle logarithm
                else if (expression.startsWith("log", i)) {
                    if (i + 3 >= expression.length() || expression.charAt(i + 3) != '(') {
                        throw new IllegalArgumentException();
                    }
                    i += 4;
                    StringBuilder num1 = new StringBuilder();
                    StringBuilder num2 = new StringBuilder();
                    boolean hasComma = false;
                    int parenCount = 1;
                    while (i < expression.length() && parenCount > 0) {
                        char ch = expression.charAt(i);
                        if (ch == '(') parenCount++;
                        if (ch == ')') parenCount--;
                        if (parenCount == 0) break;
                        if (ch == ',' && parenCount == 1) {
                            hasComma = true;
                        } else if (!hasComma) {
                            num1.append(ch);
                        } else {
                            num2.append(ch);
                        }
                        i++;
                    }
                    if (parenCount != 0 || i >= expression.length() || expression.charAt(i) != ')' || !hasComma) {
                        throw new IllegalArgumentException();
                    }
                    double base = evaluateSubExpression(num1.toString().trim());
                    double number = evaluateSubExpression(num2.toString().trim());
                    if (base <= 0 || base == 1 || number <= 0) {
                        throw new IllegalArgumentException();
                    }
                    double result = Math.log(number) / Math.log(base);
                    numbers.push(result);
                    i++;
                }
                // Handle natural logarithm
                else if (expression.startsWith("ln", i)) {
                    if (i + 2 >= expression.length() || expression.charAt(i + 2) != '(') {
                        throw new IllegalArgumentException();
                    }
                    i += 3;
                    StringBuilder num = new StringBuilder();
                    int parenCount = 1;
                    while (i < expression.length() && parenCount > 0) {
                        char ch = expression.charAt(i);
                        if (ch == '(') parenCount++;
                        if (ch == ')') parenCount--;
                        if (parenCount == 0) break;
                        num.append(ch);
                        i++;
                    }
                    if (parenCount != 0 || i >= expression.length() || expression.charAt(i) != ')') {
                        throw new IllegalArgumentException();
                    }
                    double number = evaluateSubExpression(num.toString().trim());
                    if (number <= 0) {
                        throw new IllegalArgumentException();
                    }
                    double result = Math.log(number);
                    numbers.push(result);
                    i++;
                }
                // Handle sine
                else if (expression.startsWith("sin", i)) {
                    if (i + 3 >= expression.length() || expression.charAt(i + 3) != '(') {
                        throw new IllegalArgumentException();
                    }
                    i += 4;
                    StringBuilder num = new StringBuilder();
                    int parenCount = 1;
                    while (i < expression.length() && parenCount > 0) {
                        char ch = expression.charAt(i);
                        if (ch == '(') parenCount++;
                        if (ch == ')') parenCount--;
                        if (parenCount == 0) break;
                        num.append(ch);
                        i++;
                    }
                    if (parenCount != 0 || i >= expression.length() || expression.charAt(i) != ')') {
                        throw new IllegalArgumentException();
                    }
                    double number = evaluateSubExpression(num.toString().trim());
                    if ("Degree".equals(format)) {
                        number = toRadians(number);
                    }
                    double result = Math.sin(number);
                    if (Math.abs(result) < 1e-10) result = 0.0;
                    if (Math.abs(result - 1.0) < 1e-10) result = 1.0;
                    if (Math.abs(result + 1.0) < 1e-10) result = -1.0;
                    numbers.push(result);
                    i++;
                }
                // Handle cosine
                else if (expression.startsWith("cos", i)) {
                    if (i + 3 >= expression.length() || expression.charAt(i + 3) != '(') {
                        throw new IllegalArgumentException();
                    }
                    i += 4;
                    StringBuilder num = new StringBuilder();
                    int parenCount = 1;
                    while (i < expression.length() && parenCount > 0) {
                        char ch = expression.charAt(i);
                        if (ch == '(') parenCount++;
                        if (ch == ')') parenCount--;
                        if (parenCount == 0) break;
                        num.append(ch);
                        i++;
                    }
                    if (parenCount != 0 || i >= expression.length() || expression.charAt(i) != ')') {
                        throw new IllegalArgumentException();
                    }
                    double number = evaluateSubExpression(num.toString().trim());
                    if ("Degree".equals(format)) {
                        number = toRadians(number);
                    }
                    double result = Math.cos(number);
                    if (Math.abs(result) < 1e-10) result = 0.0;
                    if (Math.abs(result - 1.0) < 1e-10) result = 1.0;
                    if (Math.abs(result + 1.0) < 1e-10) result = -1.0;
                    numbers.push(result);
                    i++;
                }
                // Handle tangent
                else if (expression.startsWith("tan", i)) {
                    if (i + 3 >= expression.length() || expression.charAt(i + 3) != '(') {
                        throw new IllegalArgumentException();
                    }
                    i += 4;
                    StringBuilder num = new StringBuilder();
                    int parenCount = 1;
                    while (i < expression.length() && parenCount > 0) {
                        char ch = expression.charAt(i);
                        if (ch == '(') parenCount++;
                        if (ch == ')') parenCount--;
                        if (parenCount == 0) break;
                        num.append(ch);
                        i++;
                    }
                    if (parenCount != 0 || i >= expression.length() || expression.charAt(i) != ')') {
                        throw new IllegalArgumentException();
                    }
                    double number = evaluateSubExpression(num.toString().trim());
                    if ("Degree".equals(format)) {
                        number = toRadians(number);
                    }
                    double cosValue = Math.cos(number);
                    if (Math.abs(cosValue) < 1e-8) {
                        throw new IllegalArgumentException();
                    }
                    double result = Math.tan(number);
                    if (Math.abs(result) < 1e-10) result = 0.0;
                    if (Math.abs(result - 1.0) < 1e-10) result = 1.0;
                    if (Math.abs(result + 1.0) < 1e-10) result = -1.0;
                    numbers.push(result);
                    i++;
                }
                // Handle cosecant
                else if (expression.startsWith("csc", i)) {
                    if (i + 3 >= expression.length() || expression.charAt(i + 3) != '(') {
                        throw new IllegalArgumentException();
                    }
                    i += 4;
                    StringBuilder num = new StringBuilder();
                    int parenCount = 1;
                    while (i < expression.length() && parenCount > 0) {
                        char ch = expression.charAt(i);
                        if (ch == '(') parenCount++;
                        if (ch == ')') parenCount--;
                        if (parenCount == 0) break;
                        num.append(ch);
                        i++;
                    }
                    if (parenCount != 0 || i >= expression.length() || expression.charAt(i) != ')') {
                        throw new IllegalArgumentException();
                    }
                    double number = evaluateSubExpression(num.toString().trim());
                    if ("Degree".equals(format)) {
                        number = toRadians(number);
                    }
                    double sinValue = Math.sin(number);
                    if (Math.abs(sinValue) < 1e-8) {
                        throw new IllegalArgumentException();
                    }
                    double result = 1.0 / sinValue;
                    if (Math.abs(result) < 1e-10) result = 0.0;
                    if (Math.abs(result - 2.0) < 1e-10) result = 2.0;
                    numbers.push(result);
                    i++;
                }
                // Handle secant
                else if (expression.startsWith("sec", i)) {
                    if (i + 3 >= expression.length() || expression.charAt(i + 3) != '(') {
                        throw new IllegalArgumentException();
                    }
                    i += 4;
                    StringBuilder num = new StringBuilder();
                    int parenCount = 1;
                    while (i < expression.length() && parenCount > 0) {
                        char ch = expression.charAt(i);
                        if (ch == '(') parenCount++;
                        if (ch == ')') parenCount--;
                        if (parenCount == 0) break;
                        num.append(ch);
                        i++;
                    }
                    if (parenCount != 0 || i >= expression.length() || expression.charAt(i) != ')') {
                        throw new IllegalArgumentException();
                    }
                    double number = evaluateSubExpression(num.toString().trim());
                    if ("Degree".equals(format)) {
                        number = toRadians(number);
                    }
                    double cosValue = Math.cos(number);
                    if (Math.abs(cosValue) < 1e-8) {
                        throw new IllegalArgumentException();
                    }
                    double result = 1.0 / cosValue;
                    if (Math.abs(result) < 1e-10) result = 0.0;
                    if (Math.abs(result - 2.0) < 1e-10) result = 2.0;
                    numbers.push(result);
                    i++;
                }
                // Handle cotangent
                else if (expression.startsWith("cot", i)) {
                    if (i + 3 >= expression.length() || expression.charAt(i + 3) != '(') {
                        throw new IllegalArgumentException();
                    }
                    i += 4;
                    StringBuilder num = new StringBuilder();
                    int parenCount = 1;
                    while (i < expression.length() && parenCount > 0) {
                        char ch = expression.charAt(i);
                        if (ch == '(') parenCount++;
                        if (ch == ')') parenCount--;
                        if (parenCount == 0) break;
                        num.append(ch);
                        i++;
                    }
                    if (parenCount != 0 || i >= expression.length() || expression.charAt(i) != ')') {
                        throw new IllegalArgumentException();
                    }
                    double number = evaluateSubExpression(num.toString().trim());
                    if ("Degree".equals(format)) {
                        number = toRadians(number);
                    }
                    double sinValue = Math.sin(number);
                    if (Math.abs(sinValue) < 1e-8) {
                        throw new IllegalArgumentException();
                    }
                    double result = 1.0 / Math.tan(number);
                    if (Math.abs(result) < 1e-10) result = 0.0;
                    numbers.push(result);
                    i++;
                }
                // Handle arcsine
                else if (expression.startsWith("arcsin", i)) {
                    if (i + 6 >= expression.length() || expression.charAt(i + 6) != '(') {
                        throw new IllegalArgumentException();
                    }
                    i += 7;
                    StringBuilder num = new StringBuilder();
                    int parenCount = 1;
                    while (i < expression.length() && parenCount > 0) {
                        char ch = expression.charAt(i);
                        if (ch == '(') parenCount++;
                        if (ch == ')') parenCount--;
                        if (parenCount == 0) break;
                        num.append(ch);
                        i++;
                    }
                    if (parenCount != 0 || i >= expression.length() || expression.charAt(i) != ')') {
                        throw new IllegalArgumentException();
                    }
                    double number = evaluateSubExpression(num.toString().trim());
                    if (number < -1.0 || number > 1.0) {
                        throw new IllegalArgumentException();
                    }
                    double result = Math.asin(number);
                    if ("Degree".equals(format)) {
                        result = toDegrees(result);
                    }
                    numbers.push(result);
                    i++;
                }
                // Handle arccosine
                else if (expression.startsWith("arccos", i)) {
                    if (i + 6 >= expression.length() || expression.charAt(i + 6) != '(') {
                        throw new IllegalArgumentException();
                    }
                    i += 7;
                    StringBuilder num = new StringBuilder();
                    int parenCount = 1;
                    while (i < expression.length() && parenCount > 0) {
                        char ch = expression.charAt(i);
                        if (ch == '(') parenCount++;
                        if (ch == ')') parenCount--;
                        if (parenCount == 0) break;
                        num.append(ch);
                        i++;
                    }
                    if (parenCount != 0 || i >= expression.length() || expression.charAt(i) != ')') {
                        throw new IllegalArgumentException();
                    }
                    double number = evaluateSubExpression(num.toString().trim());
                    if (number < -1.0 || number > 1.0) {
                        throw new IllegalArgumentException();
                    }
                    double result = Math.acos(number);
                    if ("Degree".equals(format)) {
                        result = toDegrees(result);
                    }
                    numbers.push(result);
                    i++;
                }
                // Handle arctangent
                else if (expression.startsWith("arctan", i)) {
                    if (i + 6 >= expression.length() || expression.charAt(i + 6) != '(') {
                        throw new IllegalArgumentException();
                    }
                    i += 7;
                    StringBuilder num = new StringBuilder();
                    int parenCount = 1;
                    while (i < expression.length() && parenCount > 0) {
                        char ch = expression.charAt(i);
                        if (ch == '(') parenCount++;
                        if (ch == ')') parenCount--;
                        if (parenCount == 0) break;
                        num.append(ch);
                        i++;
                    }
                    if (parenCount != 0 || i >= expression.length() || expression.charAt(i) != ')') {
                        throw new IllegalArgumentException();
                    }
                    double number = evaluateSubExpression(num.toString().trim());
                    double result = Math.atan(number);
                    if ("Degree".equals(format)) {
                        result = toDegrees(result);
                    }
                    numbers.push(result);
                    i++;
                }
                else {
                    throw new IllegalArgumentException();
                }
            }

            while (!operators.isEmpty()) {
                if (operators.peek() == '(' || operators.peek() == '|') {
                    throw new IllegalArgumentException();
                }
                performOperation(numbers, operators);
            }

            if (numbers.isEmpty()) {
                throw new IllegalArgumentException();
            }

            double result = numbers.pop();
            if (!numbers.isEmpty()) {
                throw new IllegalArgumentException();
            }
            if (result == Math.floor(result) && !Double.isInfinite(result) && Math.abs(result) < 1e10) {
                Answer = String.valueOf((long) result);
            } else {
                Answer = String.valueOf(result);
            }
            lastAnswer = Answer;
            addToHistory(expression, Answer);
        } catch (Exception e) {
            Answer = "Syntax Error";
            lastAnswer = Answer;
            addToHistory(expression, Answer);
        }
    }

    private static void addToHistory(String expression, String answer) {
        history.add(new ExpressionsEntry(expression, answer));
        if (history.size() > 20) {
            history.remove(0);
        }
    }

    public static ArrayList<ExpressionsEntry> getHistory() {
        return new ArrayList<>(history);
    }

    private static double evaluateSubExpression(String str) {
        if (str.isEmpty()) {
            throw new IllegalArgumentException();
        }
        if (str.equals("π")) return Math.PI;
        if (str.equals("e")) return Math.E;
        if (str.equals("i")) throw new IllegalArgumentException();
        if (str.equals("Ans")) {
            if (lastAnswer == null || lastAnswer.equals("Syntax Error")) {
                throw new IllegalArgumentException();
            }
            return Double.parseDouble(lastAnswer);
        }
        try {
            double number = Double.parseDouble(str);
            return number;
        } catch (NumberFormatException e) {
            evaluate(str);
            if (Answer.equals("Syntax Error")) {
                throw new IllegalArgumentException();
            }
            return Double.parseDouble(Answer);
        }
    }

    // Modified: Add case for modulo (%)
    private static void performOperation(Deque<Double> numbers, Deque<Character> operators) {
        if (operators.isEmpty()) {
            throw new IllegalArgumentException();
        }

        char op = operators.pop();

        if (numbers.size() < 2) {
            throw new IllegalArgumentException();
        }

        double num2 = numbers.pop();
        double num1 = numbers.pop();

        switch (op) {
            case '+':
                numbers.push(num1 + num2);
                break;
            case '-':
                numbers.push(num1 - num2);
                break;
            case '*':
                numbers.push(num1 * num2);
                break;
            case '/':
                if (num2 == 0) {
                    throw new ArithmeticException();
                }
                numbers.push(num1 / num2);
                break;
            case '^':
                if (num1 == 0 && num2 <= 0) {
                    throw new ArithmeticException();
                }
                numbers.push(Math.pow(num1, num2));
                break;
            case '%':
                if (num2 == 0) {
                    throw new ArithmeticException();
                }
                numbers.push(num1 % num2);
                break;
        }
    }

    // Modified: Add precedence for %
    private static boolean hasHigherPrecedence(char op1, char op2) {
        if (op1 == '^' && (op2 == '+' || op2 == '-' || op2 == '*' || op2 == '/' || op2 == '%')) {
            return true;
        }
        if ((op1 == '*' || op1 == '/' || op1 == '%') && (op2 == '+' || op2 == '-')) {
            return true;
        }
        return false;
    }
}//MIRANDA, ALFONSO, IGNACIO, SOLANO