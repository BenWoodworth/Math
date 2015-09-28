package me.bw.math.functions;

import me.bw.math.Expression;
import me.bw.math.Function;
import me.bw.math.Number;
import me.bw.math.Variable;

public class Tanh extends Function {
	private Expression num;
	
	public Tanh(Expression num) {
		super(num);
		this.num = num;
	}

	protected Expression simplify2() {
		return new Tanh(num.evaluate());
	}

	public Number evaluate() {
		Number n = num.evaluate();
		double a = n.getReal(), b = n.getImag();
		double real = Math.sinh(2 * a) / (Math.cosh(2 * a) + Math.cos(2 * b));
		double imag = Math.sin(2 * b) / (Math.cosh(2 * a) + Math.cos(2 * b));
		return new Number(real, imag);
	}

	public Expression d(Variable var) {
		return new Multiply(new Square(new Sech(num)), num.d(var));
	}
	
	public Expression substitute(Variable var, Expression value) {
		return new Tanh(num.substitute(var, value));
	}

	public String getSymbol() {
		return "tanh";
	}
	
	public Expression getNum() {
		return num;
	}

}
