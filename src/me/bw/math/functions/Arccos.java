package me.bw.math.functions;

import me.bw.math.Expression;
import me.bw.math.Number;
import me.bw.math.Variable;
import me.bw.math.functions.trig.ArcTrigFunction;

public class Arccos extends ArcTrigFunction {
	
	public Arccos(Expression num) {
		super(num, ADJ, HYP);
	}

	protected Expression simplify3() {
		return new Arccos(getNum().simplify());
	}

	protected String getSymbol() {
		return "arccos";
	}

	public Number evaluate() {
		Number n = getNum().evaluate();
		return new Number((Math.PI - 2 * arcsinRe(n)) / 2, -arcsinIm(n));
	}

	public Expression d(Variable var) {
		return new Divide(new Negate(getNum().d(var)),
				new Sqrt(new Subtract(Number.ONE, new Square(getNum()))));
	}

	public Expression substitute(Variable var, Expression value) {
		return new Arccos(getNum().substitute(var, value));
	}
	
}
