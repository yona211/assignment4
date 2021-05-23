package com.example.assignment.unary;

import com.example.assignment.Expression;
import com.example.assignment.binary.Mul;

public class Cos extends UnaryExpression implements Expression {
    // All the Cos object constructors options.
    public Cos(Expression first) { super(first); }
    public Cos(String var1) { super(var1); }
    public Cos(int num1) { super(num1); }

    @Override
    protected double operate(double first) throws Exception { return Math.cos(Math.toRadians(first)); }


    @Override
    protected String operator() { return "cos"; }
    @Override
    public Expression clone() { return new Cos(this.first); }

    /**
     * (cos(x))' = -sin(x)
     */
    @Override
    public Expression differentiate(String var) {
        if(!this.first.getVariables().contains(var) || this.first.toString().equals(var)) {
            return new Mul(-1.0, new Sin(this.first.clone()));
        } else {
            if(this.first.toString().equals("-(" + var + ")")) {
                return new Sin(this.first.clone());
            } else {
                return new Mul(-1.0, new Mul(this.first.differentiate(var), new Sin(this.first.clone())));
            }
        }
    }

    @Override
    public Expression simplify() throws Exception { return this.first.simplify(); }
}
