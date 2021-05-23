package com.example.assignment.unary;

import com.example.assignment.Expression;
import com.example.assignment.binary.Mul;

public class Neg extends UnaryExpression implements Expression{

    public Neg(Expression first) { super(first); }
    public Neg(String var1) { super(var1); }
    public Neg(int num1) { super(num1); }

    @Override
    protected double operate(double first) throws Exception { return first * -1; }
    @Override
    protected String operator() { return "-"; }
    @Override
    public Expression clone() { return new Neg(this.first.clone()); }

    @Override
    public Expression differentiate(String var) {
        if(!this.first.getVariables().contains(var)) {
            return new Num(0);
        } else {
            return new Mul(-1, this.first.differentiate(var));
        }
    }

    @Override
    public Expression simplify() throws Exception { return this.first.simplify(); }
}
