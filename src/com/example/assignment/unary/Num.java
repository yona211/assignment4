package com.example.assignment.unary;

import com.example.assignment.Expression;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Num implements Expression {
    private double number;

    /**
     * This function is to creating a new number.
     * @param number Double with the value to set.
     */
    public Num(double number) { this.number = number; }
    /**
     * Getter.
     * @return Double that is the value of the number.
     */
    public double getNumber() { return this.number; }
    /**
     * Setter.
     * @param number a Double that is the value to set.
     */
    public void setNumber(double number) { this.number = number; }
    @Override
    public double evaluate(Map<String, Double> assignment) {
        return this.number;
    }
    @Override
    public double evaluate() throws Exception { return this.number; }
    @Override
    public List<String> getVariables() {
        List<String> emptyList = new ArrayList<String>();
        return emptyList;
    }

    @Override
    public Expression assign(String var, Expression expression) { return this; }
    @Override
    public Expression clone() { return new Num(this.number); }
    @Override
    public Expression differentiate(String var) { return new Num(0); }

    @Override
    public Expression simplify() { return this.clone(); }

    @Override
    public boolean inVariables(Map<String, Double> assignment) { return true; }

    @Override
    public String toString() { return String.valueOf(this.number); }
}
