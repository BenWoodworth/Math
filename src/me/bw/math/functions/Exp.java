package me.bw.math.functions;

import me.bw.math.Constant;
import me.bw.math.Expression;
import me.bw.math.Number;
import me.bw.math.Variable;

public class Exp extends Pow {
	
	public Exp(Expression... args) {
		super(Constant.E, args[0]);
		if (args.length != 1) argException();
	}

	protected Expression simplify2() {
		return new Exp(getExp().simplify());
	}

	public Number evaluate() {
		Number n = getExp().evaluate();
		double a = Math.exp(n.getReal()) * cos(n.getImag());
		double b = Math.exp(n.getReal()) * sin(n.getImag());
		return new Number(a, b);
	}

	public Expression d(Variable var) {
		return new Multiply(this, getExp().d(var));
	}
	
	public Expression substitute(Variable var, Expression value) {
		return new Exp(getExp().substitute(var, value));
	}

	public String getSymbol() {
		return "Exp";
	}
	
	public boolean usesSymbol() {
		return false;
	}

	public String toString() {
		return "e^" + getExp();
	}

}
