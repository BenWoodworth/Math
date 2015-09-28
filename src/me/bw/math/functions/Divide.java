package me.bw.math.functions;

import me.bw.math.Expression;
import me.bw.math.Function;
import me.bw.math.Number;
import me.bw.math.Variable;

public class Divide extends Function {
	private Expression top, bottom;
	
	public Divide(Expression top, Expression bottom) {
		super(top, bottom);
		this.top = top;
		this.bottom = bottom;
	}

	protected Expression simplify2() {
		Expression top = this.top.simplify(), bottom = this.bottom.simplify();
		if (bottom.equals(Number.ONE)) return top;
		if (bottom.equals(top)) return Number.ONE;
		return new Divide(top.simplify(), bottom.simplify());
	}

	public Number evaluate() {
		Number arg0 = top.evaluate(), arg1 = bottom.evaluate();
		double a = arg0.getReal(), b = arg0.getImag();
		double c = arg1.getReal(), d = arg1.getImag();
		double real = (a * c + b * d) / (c * c + d * d);
		double imag = (b * c - a * d) / (c * c + d * d);
		return new Number(real, imag);
	}

	public Expression d(Variable var) {
		Multiply a = new Multiply(top, bottom.d(var));
		Multiply b = new Multiply(top.d(var), bottom);
		return new Divide(new Subtract(a, b), new Square(bottom));
	}
	
	public Expression substitute(Variable var, Expression value) {
		return new Divide(top.substitute(var, value),
				bottom.substitute(var, value));
	}

	public String getSymbol() {
		return "Divide";
	}
	
	public boolean usesSymbol() {
		return false;
	}

	public String toString() {
		return "(" + top + " / " + bottom + ")";
	}
	
	public Expression getTop() {
		return top;
	}
	
	public Expression getBottom() {
		return bottom;
	}

}
