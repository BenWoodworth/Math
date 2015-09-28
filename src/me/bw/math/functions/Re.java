package me.bw.math.functions;

import me.bw.math.Expression;
import me.bw.math.Function;
import me.bw.math.Number;
import me.bw.math.Variable;

public class Re extends Function {
	private Expression num;
	
	public Re(Expression num) {
		super(num);
		this.num = num;
	}

	protected Expression simplify2() {
		Expression arg = num.simplify();
		if (arg instanceof Number)
			return new Number(((Number) arg).getReal());
		if (arg instanceof Im) return Number.ZERO;
		if (arg instanceof Re) arg = ((Re) arg).getNum();
		return new Re(arg);
	}

	public Number evaluate() {
		return new Number(num.evaluate().getReal());
	}

	public Expression d(Variable var) {
		return new Derivative(this, var);
	}
	
	public Expression substitute(Variable var, Expression value) {
		return new Re(num.substitute(var, value));
	}

	public String getSymbol() {
		return "Re";
	}
	
	public Expression getNum() {
		return num;
	}

}
