package com.example.assignment.unary;

import com.example.assignment.Expression;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Var implements Expression {
    private String variable;

    /**
     * This function is to setting the desired variable.
     * @param variable String with the variable to set.
     */
    public Var(String variable) { this.variable = variable; }
    /**
     * Getter.
     * @return String that is the variable.
     */
    public String getVariable() { return this.variable; }
    /**
     * Setter.
     * @param variable a String that is the variable to set.
     */
    public void setVariable(String variable) { this.variable = variable; }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        if(assignment.containsKey(this.variable)) {
            return assignment.get(this.variable);
        } else {
            throw new Exception("variable wasn't assigned");
        }
    }

    @Override
    public double evaluate() throws Exception { throw new Exception("variable wasn't assigned"); }

    @Override
    public List<String> getVariables() {
        List<String> singleVariable = new ArrayList<String>();
        singleVariable.add(this.variable);
        return singleVariable;
    }
    @Override
    public Expression assign(String var, Expression expression) {
        if(var.equals(this.variable)) {
            return expression.clone();
        } else {
            return this;
        }
    }
    @Override
    public Expression clone() { return new Var(this.variable); }

    @Override
    public Expression differentiate(String var) {
        if(this.variable.equals(var)) {
            return new Num(1);
        } else {
            return this.clone();
        }
    }

    @Override
    public Expression simplify() { return this.clone(); }

    @Override
    public boolean inVariables(Map<String, Double> assignment) {
        if(assignment.containsKey(this.variable)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() { return this.variable; }
}
