package me.bw.math.functions;

import me.bw.math.Expression;
import me.bw.math.Number;
import me.bw.math.Variable;
import me.bw.math.functions.trig.TrigFunction;

public class Cos extends TrigFunction {
	private Expression num;
	
	public Cos(Expression num) {
		super(num, ADJ, HYP);
		this.num = num;
	}

	protected Expression simplify3() {
		return new Cos(num.simplify());
	}

	public Number evaluate() {
		Number n = num.evaluate();
		double real = cos(n.getReal()) * cosh(n.getImag());
		double imag = -sin(n.getReal()) * sinh(n.getImag());
		return new Number(real, imag);
	}

	public Expression d(Variable var) {
		return new Multiply(new Negate(new Sin(num)), num.d(var));
	}
	
	public Expression substitute(Variable var, Expression value) {
		return new Cos(num.substitute(var, value));
	}

	public String getSymbol() {
		return "cos";
	}
	
	public Expression getNum() { 
		return num;
	}

}
