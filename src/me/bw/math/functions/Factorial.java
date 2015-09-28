package me.bw.math.functions;

import me.bw.math.Expression;
import me.bw.math.Function;
import me.bw.math.Number;
import me.bw.math.Variable;

public class Factorial extends Function {
	private int num;
	
	public Factorial(Number num) {
		super(num);
		if (!num.isRealInt()) argException();
		if (num.getReal() < 0) argException();
		this.num = (int) num.getReal();
	}

	protected Expression simplify2() {
		return evaluate();
	}

	public Number evaluate() {
		double result = 1;
		for (int i = 1; i <= num; i++)
			result *= i;
		return new Number(result);
	}

	public Expression d(Variable var) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Expression substitute(Variable var, Expression value) {
		return this;
	}

	protected String getSymbol() {
		return "Fact";
	}
	
	public boolean usesSymbol() {
		return false;
	}
	
	public String toString() {
		return Integer.toString(num) + "!";
	}

}
