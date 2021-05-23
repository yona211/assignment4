package com.example.assignment.binary;

import com.example.assignment.Expression;
import com.example.assignment.unary.Num;

public class Div extends BinaryExpression implements Expression {

    public Div(Expression first, Expression second) { super(first, second); }
    public Div(double num1, double num2) { super(num1, num2); }
    public Div(String var1, String var2) { super(var1, var2); }
    public Div(Expression first, double num2) { super(first, num2); }
    public Div(Expression first, String var2) { super(first, var2); }
    public Div(double num1, Expression second) { super(num1, second); }
    public Div(double num1, String var2) { super(num1, var2); }
    public Div(String var1, Expression second) { super(var1, second); }
    public Div(String var1, double num2) { super(var1, num2); }

    @Override
    protected double operate(double first, double second) throws Exception { return first / second; }
    @Override
    protected String operator() {
        return "/";
    }
    @Override
    public Expression clone() { return new Div(this.first.clone(), this.second.clone()); }

    @Override
    public Expression differentiate(String var) {
        if(!this.first.getVariables().contains(var)) {
            // (a / b)' --> 0
            if(!this.second.getVariables().contains(var)) {
                return new Num(0);
            }
        }
        return new Div(new Minus(new Mul(this.first.differentiate(var), this.second.clone()),
                    new Mul(this.first.clone(), this.second.differentiate(var))),
                new Pow(this.second.clone(), new Num(2)));
    }

    @Override
    public Expression simplify() throws Exception {
        this.first = this.first.simplify();
        this.second = this.second.simplify();
        if(this.getVariables().isEmpty()) {
            return new Num(this.evaluate());
        } else if(isEquals(this.first, this.second)) {
            return new Num(1);
        } else if(isOne(this.second)) {
            return this.first.simplify();
        } else if(isZero(this.first)) {
            return new Num(0);
        } else {
            return new Div(this.first.simplify(), this.second.simplify());
        }
    }
}
