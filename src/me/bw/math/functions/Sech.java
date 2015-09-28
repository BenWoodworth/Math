package me.bw.math.functions;

import me.bw.math.Expression;
import me.bw.math.Function;
import me.bw.math.Number;
import me.bw.math.Variable;

public class Sech extends Function {
	private Expression num;
	
	public Sech(Expression num) {
		super(num);
		this.num = num;
	}

	protected Expression simplify2() {
		return new Sech(num.simplify());
	}

	public Number evaluate() {
		Number n = num.evaluate();
		double a = n.getReal(), b = n.getImag();
		double real = 2 * cosh(a) * cos(b) / (cosh(2 * a) + cos(2 * b));
		double imag = -2 * sinh(a) * sin(b) / (cosh(2 * a) + cos(2 * b));
		return new Number(real, imag);
	}

	public Expression d(Variable var) {
		return new Negate(new Multiply(new Tanh(num), this, num.d(var)));
	}
	
	public Expression substitute(Variable var, Expression value) {
		return new Sech(num.substitute(var, value));
	}

	public String getSymbol() {
		return "sech";
	}
	
	public Expression getNum() {
		return num;
	}

}
