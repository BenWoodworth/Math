package me.bw.math.functions;

import me.bw.math.Expression;
import me.bw.math.Function;
import me.bw.math.Number;
import me.bw.math.Variable;

public class Ceil extends Function {
	public Expression num;
	
	public Ceil(Expression num) {
		super(num);
		this.num = num;
	}

	protected Expression simplify2() {
		return new Ceil(num.simplify());
	}

	public Number evaluate() {
		Number n = num.evaluate();
		double real = Math.ceil(n.getReal());
		double imag = Math.ceil(n.getImag());
		return new Number(real, imag);
	}

	public Expression d(Variable var) {
		return new Derivative(this, var);
	}
	
	public Expression substitute(Variable var, Expression value) {
		return new Ceil(num.substitute(var, value));
	}

	public String getSymbol() {
		return "Ceil";
	}
	
	public Expression getNum() { 
		return num;
	}

}
