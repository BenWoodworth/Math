package me.bw.math;

import me.bw.math.exceptions.ParseException;

public class Equation {
	private Expression left, right;
	
	public Equation(Expression left, Expression right) {
		this.left = left;
		this.right = right;
	}
	
	public String toString() {
		return left + " = " + right;
	}
	
	public Equation parse(String s) throws ParseException {
		String[] split = s.split("=");
		if (split.length != 1)
			throw new ParseException("Equation must contain one equal sign");
		return new Equation(Expression.parse(split[0]), Expression.parse(split[1]));
	}
	
	public Expression getLeft() {
		return left;
	}
	
	public Expression getRight() {
		return right;
	}
}
