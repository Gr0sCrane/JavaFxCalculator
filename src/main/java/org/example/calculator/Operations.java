package org.example.calculator;

public class Operations {
    public static double addition(double a, double b){
        return a+b;
    }
    public static double subtract(double a, double b){
        return a-b;
    }
    public static double multiply(double a, double b){
        return a*b;
    }
    public static double divide(double a, double b){
        if (b == 0){
            throw new ArithmeticException("Divide by zero");
        }
        return a/b;
    }
}
