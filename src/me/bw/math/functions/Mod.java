package me.bw.math.functions;

import me.bw.math.Expression;
import me.bw.math.Function;
import me.bw.math.Number;
import me.bw.math.Variable;

public class Mod extends Function {
	public Expression numA, numB;
	
	public Mod(Expression numA, Expression numB) {
		super(numA, numB);
		this.numA = numA;
		this.numB = numB;
	}
	
	protected Expression simplify2() {
		return new Mod(numA.simplify(), numB.simplify());
	}

	protected String getSymbol() {
		return "Mod";
	}

	public Number evaluate() {
		Number a = numA.evaluate(), b = numB.evaluate();
		return new Subtract(a, new Multiply(b, new Quotient(a, b))).evaluate();
	}

	public Expression d(Variable var) {
		return new Derivative(this, var);
	}

	public Expression substitute(Variable var, Expression value) {
		return new Mod(numA.substitute(var, value), numB.substitute(var, value));
	}
	
	public Expression getNumA() {
		return numA;
	}
	
	public Expression getNumB() {
		return numB;
	}

}
