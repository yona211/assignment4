package com.example.assignment.binary;

import com.example.assignment.Expression;
import com.example.assignment.unary.Num;

public class Mul extends BinaryExpression implements Expression {

    public Mul(Expression first, Expression second) { super(first, second); }
    public Mul(double num1, double num2) { super(num1, num2); }
    public Mul(String var1, String var2) { super(var1, var2); }
    public Mul(Expression first, double num2) { super(first, num2); }
    public Mul(Expression first, String var2) { super(first, var2); }
    public Mul(double num1, Expression second) { super(num1, second); }
    public Mul(double num1, String var2) { super(num1, var2); }
    public Mul(String var1, Expression second) { super(var1, second); }
    public Mul(String var1, double num2) { super(var1, num2); }

    @Override
    protected double operate(double first, double second) throws Exception { return first * second; }
    @Override
    protected String operator() {
        return "*";
    }
    @Override
    public Expression clone() { return new Mul(this.first.clone(), this.second.clone()); }

    @Override
    public Expression differentiate(String var) {
        if(!this.first.getVariables().contains(var)) {
            // (a * b)' --> 0
            if(!this.second.getVariables().contains(var)) {
                return new Num(0);
            }
            // (a*f(x))' --> (a*f'(x))
            else {
                return new Mul(this.first.clone(), this.second.differentiate(var));
            }
        } else {
            // (f(x)*b)' --> (b*f'(x))
            if(!this.second.getVariables().contains(var)) {
                return new Mul(this.second.clone(), this.first.differentiate(var));
            }
            // (f(x)*g(x))' --> (f(x)*g'(x)) + (f'(x)*g(x))
            else {
                return new Plus(new Mul(this.first.clone(), this.second.differentiate(var)), new Mul(this.second.clone(), this.first.differentiate(var)));
            }
        }
    }

    @Override
    public Expression simplify() throws Exception {
        this.first = this.first.simplify();
        this.second = this.second.simplify();
        if(this.getVariables().isEmpty()) {
            return new Num(this.evaluate());
        } else if(isZero(this.first) || isZero(this.second)) {
            return new Num(0);
        } else if(isOne(this.first)) {
            return this.second.simplify();
        } else if(isOne(this.second)) {
            return this.first.simplify();
        }
        return new Mul(this.first.simplify(), this.second.simplify());
    }
}
