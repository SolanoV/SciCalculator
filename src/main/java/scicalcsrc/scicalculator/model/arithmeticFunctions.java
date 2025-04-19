package scicalcsrc.scicalculator.model;

public class arithmeticFunctions {
    public double addition(double a, double b) {
        return a + b;
    }

    public double subtraction(double a, double b) {
        return a - b;
    }

    public double multiplication(double a, double b) {
        return a * b;
    }

    public double division(double a, double b) {
        if (b == 0) {
            throw new IllegalArgumentException("Cannot divide by zero.");
        }
        return a / b;
    }

    public double modulo(double a, double b) {
        if (b == 0) {
            throw new IllegalArgumentException("Cannot perform modulo by zero.");
        }
        return a % b;
    }
}
