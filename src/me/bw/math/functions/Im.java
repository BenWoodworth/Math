package me.bw.math.functions;

import me.bw.math.Expression;
import me.bw.math.Function;
import me.bw.math.Number;
import me.bw.math.Variable;

public class Im extends Function {
	private Expression num;
	
	public Im(Expression num) {
		super(num);
		this.num = num;
	}

	protected Expression simplify2() {
		Expression arg = num.simplify();
		if (arg instanceof Number)
			return new Number(((Number) arg).getReal());
		if (arg instanceof Re) return Number.ZERO;
		if (arg instanceof Im) arg = ((Re) arg).getNum();
		return new Im(arg);
	}

	public Number evaluate() {
		return new Number(num.evaluate().getImag());
	}

	public Expression d(Variable var) {
		return new Derivative(this, var);
	}
	
	public Expression substitute(Variable var, Expression value) {
		return new Im(num.substitute(var, value));
	}

	public String getSymbol() {
		return "Im";
	}
	
	public Expression getNum() {
		return num;
	}

}
