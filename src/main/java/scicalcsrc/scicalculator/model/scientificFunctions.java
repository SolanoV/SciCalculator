package scicalcsrc.scicalculator.model;

public class scientificFunctions {
    // Trigonometric Functions
    public static double sin(double x) {
        return Math.sin(Math.toRadians(x));         // Convert degrees to radians
    }

    public static double cos(double x) {
        return Math.cos(Math.toRadians(x));
    }

    public static double tan(double x) {
        return Math.tan(Math.toRadians(x));
    }

    // Inverse Trigonometric Functions
    public static double asin(double x) {      // Return value in degrees
        return Math.asin(Math.toRadians(x));
    }

    public static double acos(double x) {
        return Math.acos(Math.toDegrees(x));
    }

    public static double atan(double x) {
        return Math.atan(Math.toDegrees(x));
    }

    // Exponent Function (e^x)
    public static double exp(double x) {
        return Math.exp(x);
    }

    // Square Root
    public static double sqrt(double x) {
        return Math.sqrt(x);
    }

    // Nth Root (e.g. cube root, 4th root)
    public static double nthRoot(double base, double n) {
        return Math.pow(base, 1.0 / n);
    }

    // Power Function (base^exponent)
    public static double power(double base, double exponent) {
        return Math.pow(base, exponent);
    }

    // Logarithmic Functions
    public static double log10(double x) {
        return Math.log10(Math.toRadians(x));
    }

    public static double log(double x) {
        return Math.log(x);         // ln(x)
    }
}
