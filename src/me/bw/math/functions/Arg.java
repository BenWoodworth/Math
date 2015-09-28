package me.bw.math.functions;

import me.bw.math.Expression;
import me.bw.math.Function;
import me.bw.math.Number;
import me.bw.math.Variable;

public class Arg extends Function {
	private Expression num;
	
	public Arg(Expression num) {
		super(num);
		this.num = num;
	}

	protected Expression simplify2() {
		return new Arg(num.simplify());
	}

	public Number evaluate() {
		Number n = num.evaluate();
		return new Number(Math.atan2(n.getImag(), n.getReal()));
	}

	public Expression d(Variable var) {
		return new Derivative(this, var);
	}
	
	public Expression substitute(Variable var, Expression value) {
		return new Arg(num.substitute(var, value));
	}

	public String getSymbol() {
		return "Arg";
	}
	
	public Expression getNum() {
		return num;
	}

}
