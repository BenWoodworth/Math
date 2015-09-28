package me.bw.math.functions;

import me.bw.math.Constant;
import me.bw.math.Expression;
import me.bw.math.Function;
import me.bw.math.Number;
import me.bw.math.Variable;

public class Fibonacci extends Function {
	private Expression num;
	
	public Fibonacci(Expression num) {
		super(num);
		this.num = num;
	}

	protected Expression simplify2() {
		Expression arg = num.simplify();
		Expression a = new Pow(Constant.PHI, arg);
		Expression b = new Pow(new Subtract(Constant.PHI, Number.ONE), arg);
		Expression c = new Multiply(b, new Cos(new Multiply(Constant.PI, arg)));
		return new Divide(new Subtract(a, c), new Sqrt(new Number(5)));
	}

	public Number evaluate() {
		return simplify2().evaluate();
	}

	public Expression d(Variable var) {
		return new Derivative(this, var);
	}
	
	public Expression substitute(Variable var, Expression value) {
		return new Fibonacci(num.substitute(var, value));
	}

	public String getSymbol() {
		return "Fibonacci";
	}
	
	public Expression getNum() {
		return num;
	}

}
