package com.example.assignment.binary;

import com.example.assignment.BaseExpression;
import com.example.assignment.Expression;
import com.example.assignment.unary.Num;
import com.example.assignment.unary.Var;

import java.util.List;
import java.util.Map;

public abstract class BinaryExpression extends BaseExpression implements Expression {
    protected Expression first, second;

    // (BinaryExpression expression) COPY
    public BinaryExpression(BinaryExpression expression) { this(expression.getFirst(), expression.getSecond()); }
    // (Expression first, Expression second)
    public BinaryExpression(Expression first, Expression second) { this.first = first; this.second = second; }
    // (double num1, double num2)
    public BinaryExpression(double num1, double num2) { this(new Num(num1), new Num(num2)); }
    // (String var1, String var2)
    public BinaryExpression(String var1, String var2) { this(new Var(var1), new Var(var2)); }
    // (Expression first, double num2)
    public BinaryExpression(Expression first, double num2) { this(first, new Num(num2)); }
    // (Expression first, String var2)
    public BinaryExpression(Expression first, String var2) { this(first, new Var(var2)); }
    // (double num1, Expression second)
    public BinaryExpression(double num1, Expression second) { this(new Num(num1), second); }
    // (double num1, String var2)
    public BinaryExpression(double num1, String var2) { this(new Num(num1), new Var(var2)); }
    // (String var1, Expression second)
    public BinaryExpression(String var1, Expression second) { this(new Var(var1), second); }
    // (String var1, double num2)
    public BinaryExpression(String var1, double num2) { this(new Var(var1), new Num(num2)); }
    // Getters.
    public Expression getFirst() { return this.first; }
    public Expression getSecond() { return this.second; }
    // Setters.
    public void setFirst(Expression first) { this.first = first; }
    public void setSecond(Expression second) { this.second = second; }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        if(this.getVariables().isEmpty()) {
            return this.evaluate();
        }
        else if(inVariables(assignment)) {
            return operate(this.first.evaluate(assignment), this.second.evaluate(assignment));
        } else {
            throw new Exception("variable wasn't assigned");
        }
    }
    @Override
    public double evaluate() throws Exception {
        if(this.getVariables().isEmpty()) {
            return operate(this.first.evaluate(), this.second.evaluate());
        } else {
            throw new Exception("variable wasn't assigned");
        }
    }

    protected abstract double operate(double first, double second) throws Exception;

    @Override
    public boolean inVariables(Map<String, Double> assignment) {
        int counter = 0;
        int numOfVariables = this.getVariables().size();
        for(String variable : this.getVariables()) {
            if(assignment.containsKey(variable)) {
                counter++;
            }
        }
        if(counter == numOfVariables) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<String> getVariables() {
        List<String> firstList, secondList;
        firstList = this.first.getVariables();
        secondList = this.second.getVariables();
        for(int i = 0; i < secondList.size(); i++) {
            boolean alreadyInFirst = false;
            for(int j = 0; j < firstList.size(); j++) {
                if(((String)firstList.get(j)).equals((String)secondList.get(i))) {
                    alreadyInFirst = true;
                    break;
                }
            }
            if(!alreadyInFirst) {
                firstList.add(secondList.get(i));
            }
        }
        return firstList;
    }
    @Override
    public Expression assign(String var, Expression expression) {

        this.first = first.assign(var, expression);
        this.second = second.assign(var, expression);
        return this;
    }
    @Override
    public abstract Expression clone();
    @Override
    protected abstract String operator();
    @Override
    public String toString() {
        return "(" + this.first.toString() +
                " " + this.operator() + " " +
                this.second.toString() + ")";
    }
}
