package me.bw.math.functions;

import me.bw.math.Expression;
import me.bw.math.Number;
import me.bw.math.Variable;

public class Reciprocal extends Divide {
	
	public Reciprocal(Expression num) {
		super(Number.ONE, num);
	}

	public Number evaluate() {
		Number n = getBottom().evaluate();
		double a = n.getReal(), b = n.getImag();
		double a2b2 = a * a + b * b;
		return new Number(a / a2b2, -b / a2b2);
	}

	public Expression d(Variable var) {
		return new Divide(new Negate(getBottom().d(var)),
				new Square(getBottom()));
	}
	
	public Expression substitute(Variable var, Expression value) {
		return new Reciprocal(getBottom().substitute(var, value));
	}

	public String getSymbol() {
		return "Reciprocal";
	}
	
	public boolean usesSymbol() {
		return false;
	}
	
	public Expression getNum() {
		return getBottom();
	}

}
