package me.bw.math.functions;

import me.bw.math.Expression;
import me.bw.math.Function;
import me.bw.math.Number;
import me.bw.math.Variable;

public class Csch extends Function {
	private Expression num;
	
	public Csch(Expression num) {
		super(num);
		this.num = num;
	}

	protected Expression simplify2() {
		return new Csch(num.simplify());
	}

	public Number evaluate() {
		Number n = num.evaluate();
		double a = n.getReal(), b = n.getImag();
		double real = -2 * sinh(a) * cos(b) / (cos(2 * b) - cosh(2 * a));
		double imag = 2 * cosh(a) * sin(b) / (cos(2 * b) - cosh(2 * a));
		return new Number(real, imag);
	}

	public Expression d(Variable var) {
		return new Multiply(new Negate(new Coth(num)), new Csch(num), num.d(var));
	}
	
	public Expression substitute(Variable var, Expression value) {
		return new Csch(num.substitute(var, value));
	}

	public String getSymbol() {
		return "csch";
	}
	
	public Expression getNum() { 
		return num;
	}

}
