package me.bw.math.functions;

import me.bw.math.Expression;
import me.bw.math.Number;
import me.bw.math.Variable;

public class Sqrt extends Pow {
	
	public Sqrt(Expression num) {
		super(num, new Reciprocal(new Number(2)));
	}

	protected Expression simplify2() {
		Expression arg = getBase().simplify();
		if (arg instanceof Square)
			return new Abs(((Square) arg).getBase());
		return new Sqrt(arg);
	}

	public Number evaluate() {
		return new Pow(getBase(), new Number(.5)).evaluate();
	}

	public Expression d(Variable var) {
		return new Divide(getBase().d(var), new Multiply(new Number(2), this));
	}
	
	public Expression substitute(Variable var, Expression value) {
		return new Sqrt(getBase().substitute(var, value));
	}

	public String getSymbol() {
		return "Sqrt";
	}
	
	public boolean usesSymbol() {
		return true;
	}
	
	public String toString() {
		return toString(getSymbol(), getBase());
	}

}
