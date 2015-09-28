package me.bw.math.functions;

import me.bw.math.Expression;
import me.bw.math.Function;
import me.bw.math.Number;
import me.bw.math.Variable;
import me.bw.math.functions.Add;
import me.bw.math.functions.Im;
import me.bw.math.functions.Multiply;
import me.bw.math.functions.Re;
import me.bw.math.functions.Sign;
import me.bw.math.functions.Sqrt;
import me.bw.math.functions.Square;

public class Abs extends Function {
	private Expression num;
	
	public Abs(Expression num) {
		super(num);
		this.num = num;
	}

	protected Expression simplify2() {
		Expression a = new Square(new Re(num));
		Expression b = new Square(new Im(num));
		return new Sqrt(new Add(a, b)).simplify2();
	}

	public Number evaluate() {
		Number n = num.evaluate();
		double a = n.getReal();
		double b = n.getImag();
		return new Number(Math.sqrt(a * a + b * b));
	}

	public Expression d(Variable var) {
		return new Multiply(new Sign(num), num.d(var));
	}
	
	public Expression substitute(Variable var, Expression value) {
		return new Abs(num.substitute(var, value));
	}

	public String getSymbol() {
		return "Abs";
	}
	
	public boolean usesSymbol() {
		return false;
	}
	
	public String toString() {
		return "|" + num + "|";
	}
	
	public Expression getNum() {
		return num;
	}
}
