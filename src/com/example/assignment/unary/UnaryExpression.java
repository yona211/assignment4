package com.example.assignment.unary;

import com.example.assignment.BaseExpression;
import com.example.assignment.Expression;

import java.util.List;
import java.util.Map;

public abstract class UnaryExpression extends BaseExpression implements Expression {

    protected Expression first;

    //(UnaryExpression expression) COPY
    public UnaryExpression(UnaryExpression expression) { this(expression.getFirst()); }
    //(Expression first)
    public UnaryExpression(Expression first) { this.first = first; }
    //(double number)
    public UnaryExpression(double number) { this(new Num(number)); }
    //(String variable)
    public UnaryExpression(String variable) { this(new Var(variable)); }
    // Getter.
    public Expression getFirst() { return this.first; }
    // Setter.
    public void setFirst(Expression first) { this.first = first; }
    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        if(this.getVariables().isEmpty()) {
            return this.evaluate();
        }
        else if(inVariables(assignment)) {
            return operate(this.first.evaluate(assignment));
        } else {
            throw new Exception("variable wasn't assigned");
        }
    }
    @Override
    public double evaluate() throws Exception {
        if(this.getVariables().isEmpty()) {
            return operate(this.first.evaluate());
        } else {
            throw new Exception("variable wasn't assigned");
        }
    }
    protected abstract double operate(double first) throws Exception;

    @Override
    public boolean inVariables(Map<String, Double> assignment) {
        int counter = 0;
        int numOfVariables = this.first.getVariables().size();
        for(String variable : this.first.getVariables()) {
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
        List<String> firstList;
        firstList = this.first.getVariables();
        return firstList;
    }

    @Override
    public Expression assign(String var, Expression expression) {
        this.first = first.assign(var, expression);
        return this;
    }
    @Override
    public abstract Expression clone();
    @Override
    protected abstract String operator();
    @Override
    public String toString() {
        return this.operator() + "(" +
                this.first.toString() + ")";
    }

    @Override
    public Expression differentiate(String var) {
        return null;
    }

}
