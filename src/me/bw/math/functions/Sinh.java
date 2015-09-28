package me.bw.math.functions;

import me.bw.math.Expression;
import me.bw.math.Function;
import me.bw.math.Number;
import me.bw.math.Variable;
import me.bw.math.functions.Cosh;
import me.bw.math.functions.Derivative;
import me.bw.math.functions.Multiply;

public class Sinh extends Function {
	private Expression num;
	
	public Sinh(Expression num) {
		super(num);
		this.num = num;
	}

	protected Expression simplify2() {
	 return new Sinh(num.simplify());
	}

	public Number evaluate() {
		Number n = num.evaluate();
		double real = Math.sinh(n.getReal()) * Math.cos(n.getImag());
		double imag = Math.cosh(n.getReal()) * Math.sin(n.getImag());
		return new Number(real, imag);
	}

	public Expression d(Variable var) {
		return new Multiply(new Cosh(num), new Derivative(num, var));
	}
	
	public Expression substitute(Variable var, Expression value) {
		return new Sinh(num.substitute(var, value));
	}

	public String getSymbol() {
		return "sinh";
	}
	
	public Expression getNum() {
		return num;
	}

}
