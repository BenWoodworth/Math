package me.bw.math.functions;

import me.bw.math.Expression;
import me.bw.math.Function;
import me.bw.math.Number;
import me.bw.math.Variable;

public class Taylor extends Function {
	private Expression num;
	private Variable var;
	private Number point;
	private int order;
	
	public Taylor(Expression num, Variable var, Number a, Number terms) {
		super(num, var, a, terms);
		if (!terms.isRealInt()) argException();
		if (terms.getReal() < 0) argException();
		
		this.num = num;
		this.var = var;
		this.point = a;
		this.order = (int) terms.getReal();
	}

	protected Expression simplify2() {
		Expression[] toAdd = new Expression[order];
		for (int i = 0; i < order; i++) {
			Expression der = new Derivative(num, var, new Number(i));
			Expression sub = der.substitute(var, point);
			Expression pow = new Pow(new Subtract(var, point), new Number(i));
			Expression fact = new Factorial(new Number(i + 1));
			toAdd[i] = new Divide(new Multiply(sub, pow), fact).simplify();
		}
		return new Add(toAdd).simplify();
	}

	public Number evaluate() {
		return simplify2().evaluate();
	}

	public Expression d(Variable var) {
		return new Derivative(this, var);
	}

	public String getSymbol() {
		return "Taylor";
	}
	
	public Expression substitute(Variable var, Expression value) {
		return new Substitute(this, var, value);
	}

	public Expression getNum() {
		return num;
	}
	
	public Variable getVar() {
		return var;
	}
	
	public int getOrder() {
		return order;
	}
	
	public Number getPoint() {
		return point;
	}
}
