package me.bw.math.functions;

import me.bw.math.Expression;
import me.bw.math.Function;
import me.bw.math.Number;
import me.bw.math.Variable;

public class Sign extends Function {
	private Expression num;
	
	public Sign(Expression num) {
		super(num);
		this.num = num;
	}

	protected Expression simplify2() {
		Expression arg = num.simplify();
		return new Divide(arg, new Abs(arg));
	}

	public Number evaluate() {
		Number n = num.evaluate();
		if (n.equals(Number.ZERO)) return Number.ZERO;
		return simplify2().evaluate();
	}

	public Expression d(Variable var) {
		return new Derivative(this, var);
	}
	
	public Expression substitute(Variable var, Expression value) {
		return new Sign(num.substitute(var, value));
	}

	public String getSymbol() {
		return "Sign";
	}
	
	public Expression getNum() {
		return num;
	}

}
