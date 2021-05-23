package com.example.assignment.binary;

import com.example.assignment.Expression;
import com.example.assignment.unary.Num;


public class Log extends BinaryExpression implements Expression {

    public Log(Expression base, Expression second) { super(base, second); }
    public Log(double base, double num2) { super(base, num2); }
    public Log(String base, String var2) { super(base, var2); }
    public Log(Expression base, double num2) { super(base, num2); }
    public Log(Expression base, String var2) { super(base, var2); }
    public Log(double base, Expression second) { super(base, second); }
    public Log(double base, String var2) { super(base, var2); }
    public Log(String base, Expression second) { super(base, second); }
    public Log(String base, double num2) { super(base, num2); }

    @Override
    protected double operate(double first, double second) throws Exception { return Math.log(second) / Math.log(first); }
    @Override
    protected String operator() {
        return "log";
    }
    @Override
    public Expression clone() { return new Log(this.first.clone(), this.second.clone()); }

    @Override
    public Expression differentiate(String var) {
        return null;
    }

    @Override
    public Expression simplify() throws Exception {
        this.first = this.first.simplify();
        this.second = this.second.simplify();
        if(this.getVariables().isEmpty()) {
            return new Num(this.evaluate());
        } else if(isEquals(this.first, this.second) || (this.first.toString().equals("e") || this.second.equals(Math.exp(1))) || (this.second.toString().equals("e") || this.first.equals(Math.exp(1)))) {
            return new Num(1);
        } else {
            return new Log(this.first.simplify(), this.second.simplify());
        }
    }


    @Override
    public String toString() {
        return "(log(" + this.first.toString() + ", " + this.second.toString() + "))";
    }
}
