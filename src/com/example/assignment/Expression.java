package com.example.assignment;


import java.util.List;
import java.util.Map;

public interface Expression {

    /**
     * This function is to assign values into the expression's variables and return the result.
     * @param assignment is a Map with two part(a String, a Double) that represent the value
     * you want to assign into the variable at this expression.
     * @return Double with the result of the expression with this specific values.
     * @throws Exception if there any given variable that is not really in the assignment.
     */
    double evaluate(Map<String, Double> assignment) throws Exception;

    /**
     * This function is like the above(`evaluate(assignment)`) but without any values to
     * assign into the expression variables.
     * @return Double with the expression result.
     * @throws Exception if there was any needed variables to get to evaluate this expression.
     */
    double evaluate() throws Exception;

    /**
     * This function returns a list of the variables in the expression. "X", "X Y".
     * @return List<String> with all the variables in the expression.
     */
    List<String> getVariables();

    /**
     * This function returns a nice string representation of the expression. "(x + ((x + y)^2))^2".
     * @return String that  representing of the expression.
     */
    String toString();

    /**
     * This function returns a new expression in which all occurrences of the variable var are replaced with the provided expression.
     * @param var is the variable that you want to assign the expression to.
     * @param expression is the expression you want to assign.
     * @return a new Expression with the new assignment.
     */
    Expression assign(String var, Expression expression);

    /**
     * This function is line a copy constructor.
     * @return the copy of the expression.
     */
    Expression clone();

    /**
     * This function return the differentiate of the current expression.
     * @param var is the variable you want to differentiate on.
     * @return a new Expression after the differentiation.
     */
    Expression differentiate(String var);

    /**
     * This function is returning a simplified version of the current expression.
     * @return a new Expression that is the simplified version of the current expression.
     */
    Expression simplify() throws Exception;


    boolean inVariables(Map<String, Double> assignment);
}
