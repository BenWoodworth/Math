package me.bw.math.functions;

import me.bw.math.Expression;
import me.bw.math.Number;
import me.bw.math.Variable;
import me.bw.math.functions.trig.TrigFunction;

public class Tan extends TrigFunction {
	private Expression num;
	
	public Tan(Expression num) {
		super(num, OPP, ADJ);
		this.num = num;
	}

	protected Expression simplify3() {
		return new Tan(num.simplify());
	}

	public Number evaluate() {
		Number n = num.evaluate();
		double a = n.getReal(), b = n.getImag();
		double real = Math.sin(2 * a) / (Math.cos(2 * a) + Math.cosh(2 * b));
		double imag = Math.sinh(2 * b) / (Math.cos(2 * a) + Math.cosh(2 * b));
		return new Number(real, imag);
	}

	public Expression d(Variable var) {
		return new Multiply(new Square(new Sec(num)), num.d(var));
	}
	
	public Expression substitute(Variable var, Expression value) {
		return new Tan(num.substitute(var, value));
	}

	public String getSymbol() {
		return "tan";
	}
	
	public Expression getNum() {
		return num;
	}

}
