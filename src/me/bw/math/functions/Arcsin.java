package me.bw.math.functions;

import me.bw.math.Expression;
import me.bw.math.Number;
import me.bw.math.Variable;
import me.bw.math.functions.trig.ArcTrigFunction;

public class Arcsin extends ArcTrigFunction {
	
	public Arcsin(Expression num) {
		super(num, OPP, HYP);
	}

	protected Expression simplify3() {
		return new Arcsin(getNum().simplify());
	}

	protected String getSymbol() {
		return "arcsin";
	}

	public Number evaluate() {
		Number n = getNum().evaluate();
		return new Number(arcsinRe(n), arcsinIm(n));
	}

	public Expression d(Variable var) {
		return new Divide(getNum().d(var),
				new Sqrt(new Subtract(Number.ONE, new Square(getNum()))));
	}

	public Expression substitute(Variable var, Expression value) {
		return new Arcsin(getNum().substitute(var, value));
	}

}
