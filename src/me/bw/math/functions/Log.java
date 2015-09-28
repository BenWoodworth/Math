package me.bw.math.functions;

import me.bw.math.Expression;
import me.bw.math.Function;
import me.bw.math.Number;
import me.bw.math.Variable;

public class Log extends Function {
	private Expression base, num;
	
	public Log(Expression base, Expression num) {
		super(base, num);
		this.base = base;
		this.num = num;
	}

	protected Expression simplify2() {
		return new Divide(new Ln(getNum().simplify()),
				new Ln(getBase().simplify()));
	}

	public Number evaluate() {
		return simplify2().evaluate();
	}

	public Expression d(Variable var) {
		Expression a = new Multiply(new Ln(getBase()), getNum().d(var));
		Expression b = new Multiply(getBase().d(var), new Ln(getNum()));
		Expression c = new Subtract(new Divide(a, getNum()), new Divide(b, getBase()));
		return new Divide(c, new Square(new Ln(getBase())));
	}
	
	public Expression substitute(Variable var, Expression value) {
		return new Log(base.substitute(var, value),
				num.substitute(var, value));
	}

	public String getSymbol() {
		return "Log";
	}
	
	public Expression getBase() {
		return base;
	}

	public Expression getNum() {
		return num;
	}
	
}
