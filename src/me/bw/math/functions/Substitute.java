package me.bw.math.functions;

import me.bw.math.Expression;
import me.bw.math.Function;
import me.bw.math.Number;
import me.bw.math.Variable;
import me.bw.math.exceptions.EvalException;

public class Substitute extends Function {
	private Expression num;
	private Variable var;
	private Expression value;
	
	public Substitute(Expression num, Variable var, Expression value) {
		super(num, var, value);
		this.num = num;
		this.var = var;
		this.value = value;
	}
	
	protected Expression simplify2() {
		return num.simplify().substitute(var, value);
	}

	protected String getSymbol() {
		return "Substitute";
	}

	public Number evaluate() {
		Expression simp = simplify2();
		if (simp instanceof Substitute)
			throw new EvalException("Unable to substitute!");
		return simp.evaluate();
	}

	public Expression d(Variable var) {
		return new Derivative(this, var);
	}

	public Expression substitute(Variable var, Expression value) {
		if (var == this.var) return this;
		return new Substitute(this, var, value);
	}
	
	public Expression getNum() {
		return num;
	}
	
	public Variable getVar() {
		return var;
	}
	
	public Expression getValue() {
		return value;
	}

}
