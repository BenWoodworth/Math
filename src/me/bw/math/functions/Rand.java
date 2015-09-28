package me.bw.math.functions;

import me.bw.math.Expression;
import me.bw.math.Function;
import me.bw.math.Number;
import me.bw.math.Variable;

public class Rand extends Function {
	private Expression min, max;
	
	public Rand(Expression min, Expression max) {
		super(min, max);
		this.min = min;
		this.max = max;
	}

	protected Expression simplify2() {
		Expression newMin = min.simplify();
		Expression newMax = max.simplify();
		if (newMin.equals(newMax)) return newMin;
		return new Rand(newMin, newMax);
	}

	public Number evaluate() {
		Number newMin = min.evaluate(), newMax = max.evaluate();
		double a = Math.random() * (newMax.getReal() - newMin.getReal()) + newMin.getReal();
		double b = Math.random() * (newMax.getImag() - newMin.getImag()) + newMin.getImag();
		return new Number(a, b);
	}

	public Expression d(Variable var) {
		return new Derivative(this, var);
	}
	
	public Expression substitute(Variable var, Expression value) {
		return new Rand(min.substitute(var, value),
				max.substitute(var, value));
	}

	public String getSymbol() {
		return "Rand";
	}
	
	public Expression getMin() {
		return min;
	}
	
	public Expression getMax() {
		return max;
	}

}
