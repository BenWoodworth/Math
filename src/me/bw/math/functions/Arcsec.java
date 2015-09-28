package me.bw.math.functions;

import me.bw.math.Expression;
import me.bw.math.Number;
import me.bw.math.Variable;
import me.bw.math.functions.trig.ArcTrigFunction;

public class Arcsec extends ArcTrigFunction {
	
	public Arcsec(Expression num) {
		super(num, HYP, ADJ);
	}

	protected Expression simplify3() {
		return new Arcsec(getNum().simplify());
	}

	protected String getSymbol() {
		return "arcsec";
	}

	public Number evaluate() {
		Number n = new Reciprocal(getNum()).evaluate();
		return new Number((Math.PI - 2 * arcsinRe(n)) / 2, -arcsinIm(n));
	}

	public Expression d(Variable var) {
		return new Divide(getNum().d(var), new Multiply(new Square(getNum()),
				new Sqrt(new Subtract(Number.ONE, new Pow(getNum(), new Number(-2))))));
	}

	public Expression substitute(Variable var, Expression value) {
		return new Arcsec(getNum().substitute(var, value));
	}
	
}
