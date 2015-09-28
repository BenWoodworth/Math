package me.bw.math.functions;

import me.bw.math.Constant;
import me.bw.math.Expression;
import me.bw.math.Function;
import me.bw.math.Number;
import me.bw.math.Variable;

public class Negate extends Function {
	private Expression num;
	
	public Negate(Expression num) {
		super(num);
		this.num = num;
	}

	protected Expression simplify2() {
		Expression arg = num.simplify();
		if (arg instanceof Number && !(arg instanceof Constant))
			return ((Number) arg).negate();
		if (arg instanceof Negate)
			return ((Negate) arg).num;
		return new Negate(arg);
	}

	public Number evaluate() {
		return num.evaluate().negate();
	}

	public Expression d(Variable var) {
		return new Negate(num.d(var));
	}
	
	public Expression substitute(Variable var, Expression value) {
		return new Negate(num.substitute(var, value));
	}

	public String getSymbol() {
		return "Negate";
	}
	
	public boolean usesSymbol() {
		return false;
	}

	public String toString() {
		return "(-" + num + ")";
	}
	
	public Expression getNum() {
		return num;
	}

}
