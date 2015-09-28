package me.bw.math.functions;

import me.bw.math.Expression;
import me.bw.math.Function;
import me.bw.math.Number;
import me.bw.math.Variable;

public class Subtract extends Function {
	private Expression left, right;
	
	public Subtract(Expression left, Expression right) {
		super(left, right);
		this.left = left;
		this.right = right;
	}

	protected Expression simplify2() {
		Expression arg0 = left.simplify();
		Expression arg1 = right.simplify();
		if (arg0.equals(arg1)) return Number.ZERO;
		if (arg0.equals(Number.ZERO)) return new Negate(arg1);
		if (arg1.equals(Number.ZERO)) return arg0;
		return new Subtract(arg0, arg1);
	}

	public Number evaluate() {
		Number arg0 = left.evaluate();
		Number arg1 = right.evaluate();
		double a = arg0.getReal() - arg1.getReal();
		double b = arg0.getImag() - arg1.getImag();
		return new Number(a, b);
	}

	public Expression d(Variable var) {
		return new Subtract(left.d(var), right.d(var));
	}
	
	public Expression substitute(Variable var, Expression value) {
		return new Subtract(left.substitute(var, value),
				right.substitute(var, value));
	}

	public String getSymbol() {
		return "Subtract";
	}
	
	public boolean usesSymbol() {
		return false;
	}

	public String toString() {
		return "(" + left + " - " + right + ")";
	}
	
	public Expression getLeft() {
		return left;
	}
	
	public Expression getRight() {
		return right;
	}

}
