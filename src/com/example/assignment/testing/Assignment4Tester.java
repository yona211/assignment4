package com.example.assignment.testing;

import com.example.assignment.Expression;
import com.example.assignment.binary.Mul;
import com.example.assignment.binary.Plus;
import com.example.assignment.binary.Pow;
import com.example.assignment.unary.Sin;

import java.util.Map;
import java.util.TreeMap;

public class Assignment4Tester {
    public static void main(String[] args) throws Exception {
        Expression e = new Plus(new Mul(2, "x"), new Plus(new Sin(new Mul(2, "y")), new Pow("e", "x")));
        System.out.println(e.toString());
        Map<String, Double> assignment = new TreeMap<String, Double>();
        assignment.put("x", 2.0);
        assignment.put("y", 0.25);
        assignment.put("e", Math.exp(1));
        double value = e.evaluate(assignment);
        System.out.println("value: " + String.valueOf(value));
        Expression de = e.differentiate("x");
        System.out.println(de.toString());
        Expression sde = de.simplify();
        System.out.println(sde.toString());
    }
}
