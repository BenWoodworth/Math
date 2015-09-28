package me.bw.math.functions;

import me.bw.math.Expression;
import me.bw.math.Number;
import me.bw.math.Variable;
import me.bw.math.functions.trig.TrigFunction;

public class Sec extends TrigFunction {
	private Expression num;
	
	public Sec(Expression num) {
		super(num, HYP, ADJ);
		this.num = num;
	}

	protected Expression simplify3() {
		return new Sec(num.simplify());
	}

	public Number evaluate() {
		Number n = num.evaluate();
		double a = n.getReal(), b = n.getImag();
		double real = 2 * cos(a) * cosh(b) / (cos(2 * a) + cosh(2 * b));
		double imag = 2 * sin(a) * sinh(b) / (cos(2 * a) + cosh(2 * b));
		return new Number(real, imag);
	}

	public Expression d(Variable var) {
		return new Multiply(new Tan(num), this, num.d(var));
	}
	
	public Expression substitute(Variable var, Expression value) {
		return new Sec(num.substitute(var, value));
	}

	public String getSymbol() {
		return "sec";
	}
	
	public Expression getNum() {
		return num;
	}

}
