package com.example.assignment;

public abstract class BaseExpression implements Expression {
    public abstract Expression clone();

    protected abstract String operator();

    public static boolean isZero(Expression expression) {
        if(expression.toString().equals("0.0")) {
            return true;
        }
        return false;
    }
    public static boolean isOne(Expression expression) {
        if(expression.toString().equals("1.0")) {
            return true;
        }
        return false;
    }
    public static boolean isEquals(Expression first, Expression second) {
        if(first.toString().equals(second.toString())) {
            return true;
        } else {
            return false;
        }
    }


}
