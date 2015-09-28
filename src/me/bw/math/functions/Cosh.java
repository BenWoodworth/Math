package me.bw.math.functions;

import me.bw.math.Expression;
import me.bw.math.Function;
import me.bw.math.Number;
import me.bw.math.Variable;
import me.bw.math.functions.Multiply;
import me.bw.math.functions.Sinh;

public class Cosh extends Function {
	private Expression num;
	
	public Cosh(Expression num) {
		super(num);
		this.num = num;
	}

	protected Expression simplify2() {
		return new Cosh(num.simplify());
	}

	public Number evaluate() {
		Number n = num.evaluate();
		double real = cosh(n.getReal()) * cos(n.getImag());
		double imag = sinh(n.getReal()) * sin(n.getImag());
		return new Number(real, imag);
	}

	public Expression d(Variable var) {
		return new Multiply(new Sinh(num), num.d(var));
	}
	
	public Expression substitute(Variable var, Expression value) {
		return new Cosh(num.substitute(var, value));
	}

	public String getSymbol() {
		return "cosh";
	}
	
	public Expression getNum() { 
		return num;
	}

}
