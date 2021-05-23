package com.example.assignment.binary;

import com.example.assignment.Expression;
import com.example.assignment.unary.Num;

public class Pow extends BinaryExpression implements Expression {
    public Pow(Expression first, Expression second) { super(first, second); }
    public Pow(double num1, double num2) { super(num1, num2); }
    public Pow(String var1, String var2) { super(var1, var2); }
    public Pow(Expression first, double num2) { super(first, num2); }
    public Pow(Expression first, String var2) { super(first, var2); }
    public Pow(double num1, Expression second) { super(num1, second); }
    public Pow(double num1, String var2) { super(num1, var2); }
    public Pow(String var1, Expression second) { super(var1, second); }
    public Pow(String var1, double num2) { super(var1, num2); }

    @Override
    protected double operate(double first, double second) throws Exception { return Math.pow(first, second); }
    @Override
    protected String operator() {
        return "^";
    }
    @Override
    public Expression clone() { return new Pow(this.first.clone(), this.second.clone()); }
    @Override
    public String toString() { return "(" + this.first.toString() + "^" + this.second.toString() + ")"; }

    /**
     * (x^a)' = a*(x^(a-1))
     */
    @Override
    public Expression differentiate(String var) {
        if(!this.first.getVariables().contains(var)) {
            // (a^b)' --> 0
            if(!this.second.getVariables().contains(var)) {
                return new Num(0);
            }
            // (a^g(x))' --> (log(e, a)*(a^g(x))*g'(x))
            else {
                return new Mul(new Log(new Num(Math.exp(1)), this.first.clone()), new Mul(new Pow(this.first.clone(), this.second.clone()), this.second.differentiate(var)));
            }
        } else {
            // (f(x)^b)' --> (b*(f(x)^(b-1)))
            if(!this.second.getVariables().contains(var)) {
                return new Mul(this.first.differentiate(var), new Mul(this.second.clone(), new Pow(this.first.clone(), new Minus(this.second.clone(), 1))));
            }
            // (f(x)^g(x))' -->
            else {
                return new Plus(new Mul(this.second.clone(), new Pow(this.first.clone(), new Minus(this.second.clone(), 1))),
                        new Mul(new Pow(this.first.clone(), this.second.clone()), new Log(new Num(Math.exp(1)), this.first.clone())));
            }
        }
    }

    @Override
    public Expression simplify() throws Exception {
        this.first = this.first.simplify();
        this.second = this.second.simplify();
        if(this.getVariables().isEmpty()) {
            return new Num(this.evaluate());
        } else if(isOne(this.second)) {
            return this.first.simplify();
        } else if(isZero(this.second)) {
            return new Num(1);
        } else {
            return new Pow(this.first.simplify(), this.second.simplify());
        }
    }
}
