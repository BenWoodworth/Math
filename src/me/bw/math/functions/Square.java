package me.bw.math.functions;

import me.bw.math.Expression;
import me.bw.math.Number;
import me.bw.math.Variable;

public class Square extends Pow {
	
	public Square(Expression num) {
		super(num, new Number(2));
	}

	public Number evaluate() {
		Number n = getBase().evaluate();
		double a = n.getReal(), b = n.getImag();
		double real = a * a - b * b;
		double imag = 2 * a * b;
		return new Number(real, imag);
	}

	public Expression d(Variable var) {
		return new Multiply(new Number(2), getBase(), getBase().d(var));
	}
	
	public Expression substitute(Variable var, Expression value) {
		return new Square(getBase().substitute(var, value));
	}

	public String getSymbol() {
		return "Square";
	}
	
	public boolean usesSymbol() {
		return false;
	}
	
}
