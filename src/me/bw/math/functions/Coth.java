package me.bw.math.functions;

import me.bw.math.Expression;
import me.bw.math.Function;
import me.bw.math.Number;
import me.bw.math.Variable;

public class Coth extends Function {
	private Expression num;
	
	public Coth(Expression num) {
		super(num);
		this.num = num;
	}

	protected Expression simplify2() {
		return new Coth(num.simplify());
	}

	public Number evaluate() {
		Number n = num.evaluate();
		double a = n.getReal(), b = n.getImag();
		double real = -sinh(2 * a) / (cos(2 * b) - cosh(2 * a));
		double imag = sin(2 * b) / (cos(2 * b) - cosh(2 * a));
		return new Number(real, imag);
	}

	public Expression d(Variable var) {
		return new Multiply(new Negate(new Square(new Csc(num))), num.d(var));
	}
	
	public Expression substitute(Variable var, Expression value) {
		return new Coth(num.substitute(var, value));
	}

	public String getSymbol() {
		return "coth";
	}
	
	public Expression getNum() { 
		return num;
	}

}
