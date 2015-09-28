package me.bw.math.functions;

import me.bw.math.Expression;
import me.bw.math.Function;
import me.bw.math.Number;
import me.bw.math.Variable;

public class Floor extends Function {
	private Expression num;
	
	public Floor(Expression num) {
		super(num);
		this.num = num;
	}

	protected Expression simplify2() {
		return new Floor(num.simplify());
	}

	public Number evaluate() {
		Number n = num.evaluate();
		double real = Math.floor(n.getReal());
		double imag = Math.floor(n.getImag());
		return new Number(real, imag);
	}

	public Expression d(Variable var) {
		return new Derivative(this, var);
	}
	
	public Expression substitute(Variable var, Expression value) {
		return new Floor(num.substitute(var, value));
	}

	public String getSymbol() {
		return "Floor";
	}
	
	public Expression getNum() {
		return num;
	}

}
