package me.bw.math.functions;

import me.bw.math.Expression;
import me.bw.math.Number;
import me.bw.math.Variable;
import me.bw.math.functions.trig.ArcTrigFunction;

public class Arccsc extends ArcTrigFunction {
	
	public Arccsc(Expression num) {
		super(num, HYP, OPP);
	}

	protected Expression simplify3() {
		return new Arccsc(getNum().simplify());
	}

	protected String getSymbol() {
		return "arccsc";
	}

	public Number evaluate() {
		Number n = new Reciprocal(getNum()).evaluate();
		return new Number(arcsinRe(n), arcsinIm(n));
	}

	public Expression d(Variable var) {
		return new Divide(new Negate(getNum().d(var)), new Multiply(new Square(getNum()),
				new Sqrt(new Subtract(Number.ONE, new Pow(getNum(), new Number(-2))))));
	}

	public Expression substitute(Variable var, Expression value) {
		return new Arccsc(getNum().substitute(var, value));
	}
	
}
