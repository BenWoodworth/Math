package me.bw.math.functions;

import me.bw.math.Expression;
import me.bw.math.Number;
import me.bw.math.Variable;
import me.bw.math.functions.trig.TrigFunction;

public class Cot extends TrigFunction {
	private Expression num;
	
	public Cot(Expression num) {
		super(num, ADJ, OPP);
		this.num = num;
	}

	protected Expression simplify3() {
		return new Cot(num.simplify());
	}

	public Number evaluate() {
		Number n = num.evaluate();
		double a = n.getReal(), b = n.getImag();
		double real = -sin(2 * a) / (cos(2 * a) - cosh(2 * b));
		double imag = sinh(2 * b) / (cos(2 * a) - cosh(2 * b));
		return new Number(real, imag);
	}

	public Expression d(Variable var) {
		return new Multiply(new Negate(new Square(new Csc(num))), num.d(var));
	}
	
	public Expression substitute(Variable var, Expression value) {
		return new Cot(num.substitute(var, value));
	}

	public String getSymbol() {
		return "cot";
	}
	
	public Expression getNum() { 
		return num;
	}

}
