package me.bw.math.functions;

import me.bw.math.Expression;
import me.bw.math.Function;
import me.bw.math.Number;
import me.bw.math.Variable;

public class Quotient extends Function {
	Expression numA, numB;
	
	public Quotient(Expression numA, Expression numB) {
		super(numA, numB);
		this.numA = numA;
		this.numB = numB;
	}
	
	protected Expression simplify2() {
		return new Floor(new Divide(numA.simplify(), numB.simplify()));
	}

	protected String getSymbol() {
		return "Quotient";
	}

	public Number evaluate() {
		return simplify2().evaluate();
	}

	public Expression d(Variable var) {
		return new Derivative(this, var);
	}

	public Expression substitute(Variable var, Expression value) {
		return new Quotient(numA.substitute(var, value), numB.substitute(var, value));
	}

}
