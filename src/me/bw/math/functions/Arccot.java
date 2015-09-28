package me.bw.math.functions;

import me.bw.math.Constant;
import me.bw.math.Expression;
import me.bw.math.Number;
import me.bw.math.Variable;
import me.bw.math.functions.trig.ArcTrigFunction;

public class Arccot extends ArcTrigFunction {
	
	public Arccot(Expression num) {
		super(num, ADJ, OPP);
	}

	protected Expression simplify3() {
		return new Arccot(getNum().simplify());
	}

	protected String getSymbol() {
		return "arccot";
	}

	public Number evaluate() {
		Number n = new Reciprocal(getNum()).evaluate();
		Expression rA = new Divide(new Arg(new Add(
				Number.ONE, new Multiply(Constant.I, n))), new Number(2));
		Expression rB = new Divide(new Arg(new Subtract(
				Number.ONE, new Multiply(Constant.I, n))), new Number(2));
		double real = new Subtract(rA, rB).evaluate().getReal();
		
		double a = n.getReal(), b = n.getImag();
		double iA = Math.log(a * a + (1 + b) * (1 + b)) / 4;
		double iB = Math.log(a * a + (1 - b) * (1 - b)) / 4;
		return new Number(real, iA - iB);
	}

	public Expression d(Variable var) {
		return new Divide(new Negate(getNum().d(var)),
				new Add(Number.ONE, new Square(getNum())));
	}

	public Expression substitute(Variable var, Expression value) {
		return new Arccot(getNum().substitute(var, value));
	}
	
}
