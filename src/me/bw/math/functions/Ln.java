package me.bw.math.functions;

import me.bw.math.Constant;
import me.bw.math.Expression;
import me.bw.math.Number;
import me.bw.math.Variable;

public class Ln extends Log {
	
	public Ln(Expression num) {
		super(Constant.E, num);
	}

	protected Expression simplify2() {
		return new Ln(getNum().simplify());
	}

	public Number evaluate() {
		Number n = getNum().evaluate();
		double a = n.getReal(), b = n.getImag();
		double real = Math.log(a * a + b * b) / 2;
		double imag = Math.atan2(b, a);
		return new Number(real, imag);
		
	}

	public Expression d(Variable var) {
		return new Divide(getNum().d(var), getNum());
	}
	
	public Expression substitute(Variable var, Expression value) {
		return new Ln(getNum().substitute(var, value));
	}

	public String getSymbol() {
		return "Ln";
	}

}
