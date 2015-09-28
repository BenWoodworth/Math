package me.bw.math;

import me.bw.math.exceptions.EvalException;
import me.bw.math.functions.Derivative;

public class Variable extends Expression implements Symbol {
	char symbol;
	
	public Variable(char symbol) {
		this.symbol = symbol;
	}
	
	public boolean equals(Object o) {
		if (o instanceof Variable)
			return ((Variable)o).symbol == symbol;
		return false;
	}
	
	public Expression substitute(Variable var, Expression value) {
		if (equals(var)) return value;
		return this;
	}
	
	public Expression simplify() {
		return this;
	}

	public Number evaluate() {
		throw new EvalException("Cannot evaluate variables!");
	}
	
	public String toString() {
		return Character.toString(symbol);
	}

	public Expression d(Variable var) {
		if (equals(var)) return Number.ONE;
		return new Derivative(this, var);
	}

	public boolean hasVars() {
		return true;
	}
}
