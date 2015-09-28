package me.bw.math.functions;

import me.bw.math.Expression;
import me.bw.math.Function;
import me.bw.math.Number;
import me.bw.math.Variable;

public class Derivative extends Function {
	private Expression num;
	private Variable var;
	private int order;
	
	public Derivative(Expression num, Variable var, Number order) {
		super(num, var, order);
		if (!order.isRealInt()) argException();
		if (order.getReal() < 0) argException();
		
		this.num = num;
		this.var = var;
		this.order = (int) order.getReal();
	}
	
	public Derivative(Expression num, Variable var) {
		this(num, var, Number.ONE);
	}

	protected Expression simplify2() {
		Expression e = num.simplify();
		for (int i = 0; i < order; i++)
			e = e.d(var);
		return e.simplify();
	}

	public Number evaluate() {
		Expression e = simplify2();
		return e.evaluate();
	}

	public String getSymbol() {
		return "Derivative";
	}
	
	public boolean usesSymbol() {
		return false;
	}

	public Expression d(Variable var) {
		if (var == this.var)
			return new Derivative(num, var, new Number(order + 1));
		return new Derivative(this, var);
	}

	public String toString() {
		String exp = order == 1 ? "" : "^" + Integer.toString(order);
		if (num instanceof Variable)
			return "d" + exp + num + "/d" + exp + var;
		String arg = num.toString();
		if (arg.charAt(0) != '(') arg = " " + arg;
		return "d" + exp + "/d" + var + exp + arg;
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

}
