package me.bw.math.functions;

import me.bw.math.Expression;
import me.bw.math.Number;
import me.bw.math.Variable;

public class Cube extends Pow {
	
	public Cube(Expression num) {
		super(num, new Number(3));
	}

	public Number evaluate() {
		Number n = getBase().evaluate();
		double a = n.getReal(), b = n.getImag();
		double real = a * a * a - 3 * a * b * b;
		double imag = 3 * a * a * b - b * b * b;
		return new Number(real, imag);
	}

	public Expression d(Variable var) {
		return new Multiply(new Number(3), new Square(getBase()), getBase().d(var));
	}
	
	public Expression substitute(Variable var, Expression value) {
		return new Cube(getBase().substitute(var, value));
	}

	public String getSymbol() {
		return "Cube";
	}
	
	public boolean usesSymbol() {
		return false;
	}

}
