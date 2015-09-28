package me.bw.math.functions;

import me.bw.math.Expression;
import me.bw.math.Number;
import me.bw.math.Variable;
import me.bw.math.functions.trig.TrigFunction;

public class Sin extends TrigFunction {
	private Expression num;
	
	public Sin(Expression num) {
		super(num, OPP, HYP);
		this.num = num;
	}

	protected Expression simplify3() {
		return new Sin(num.simplify());
	}

	public Number evaluate() {
		Number n = num.evaluate();
		double real = sin(n.getReal()) * cosh(n.getImag());
		double imag = cos(n.getReal()) * sinh(n.getImag());
		return new Number(real, imag);
	}

	public Expression d(Variable var) {
		return new Multiply(new Cos(num), num.d(var));
	}
	
	public Expression substitute(Variable var, Expression value) {
		return new Sin(num.substitute(var, value));
	}

	public String getSymbol() {
		return "sin";
	}
	
	public Expression getNum() {
		return num;
	}

}
