package com.example.assignment.unary;

import com.example.assignment.Expression;
import com.example.assignment.binary.Mul;

public class Sin extends UnaryExpression implements Expression {

    public Sin(Expression first) { super(first); }
    public Sin(String var1) { super(var1); }
    public Sin(int num1) { super(num1); }

    @Override
    protected double operate(double first) throws Exception { return Math.sin(Math.toRadians(first)); }
    @Override
    protected String operator() { return "sin"; }
    @Override
    public Expression clone() { return new Sin(this.first); }

    /**
     * (sin(x))' = cos(x)
     */
    @Override
    public Expression differentiate(String var) {
        if(!this.first.getVariables().contains(var) || this.first.toString().equals(var)) {
            return new Cos(this.first.clone());
        } else {
            if(this.first.toString().equals("-(" + var + ")")) {
                return new Mul(-1.0, new Cos(this.first.clone()));
            } else {
                return new Mul(this.first.differentiate(var), new Cos(this.first.clone()));
            }
        }
    }

    @Override
    public Expression simplify() throws Exception { return this.first.simplify(); }

}
