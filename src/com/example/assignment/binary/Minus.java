package com.example.assignment.binary;

import com.example.assignment.Expression;
import com.example.assignment.unary.Neg;
import com.example.assignment.unary.Num;

public class Minus extends BinaryExpression implements Expression {
    public Minus(Expression first, Expression second) { super(first, second); }
    public Minus(double num1, double num2) { super(num1, num2); }
    public Minus(String var1, String var2) { super(var1, var2); }
    public Minus(Expression first, double num2) { super(first, num2); }
    public Minus(Expression first, String var2) { super(first, var2); }
    public Minus(double num1, Expression second) { super(num1, second); }
    public Minus(double num1, String var2) { super(num1, var2); }
    public Minus(String var1, Expression second) { super(var1, second); }
    public Minus(String var1, double num2) { super(var1, num2); }

    @Override
    protected double operate(double first, double second) throws Exception { return first - second; }
    @Override
    protected String operator() {
        return "-";
    }
    @Override
    public Expression clone() { return new Minus(this.first.clone(), this.second.clone()); }

    /**
     * h'(x) = f'(x) + g'(x)
     */
    @Override
    public Expression differentiate(String var) {
        return new Minus(this.first.differentiate(var), this.second.differentiate(var));
    }

    @Override
    public Expression simplify() throws Exception {
        this.first = this.first.simplify();
        this.second = this.second.simplify();
        if(this.getVariables().isEmpty()) {
            return new Num(this.evaluate());
        } else if(isEquals(this.first, this.second)) {
            return new Num(0);
        } else if(isZero(this.first)) {
            return new Neg(this.second.simplify());
        } else if(isZero(this.second)) {
            return this.first.simplify();
        } else {
            return new Minus(this.first.simplify(), this.second.simplify());
        }
    }
}
