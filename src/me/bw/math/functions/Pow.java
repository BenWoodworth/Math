package me.bw.math.functions;

import me.bw.math.Expression;
import me.bw.math.Function;
import me.bw.math.Number;
import me.bw.math.Variable;

public class Pow extends Function {
	private Expression base;
	private Expression exp;

	public Pow(Expression base, Expression exp) {
		super(base, exp);
		this.base = base;
		this.exp = exp;
	}

	protected Expression simplify2() {
		Expression b = base.simplify(), e = exp.simplify();
		if (b instanceof Pow) {
			e = new Multiply(e, ((Pow) b).getExp()).simplify();
			b = ((Pow) b).getBase();
		}
		return new Pow(b, e);
	}

	public Number evaluate() {
		Number newBase = base.evaluate(), newExp = exp.evaluate();
		double a = newBase.getReal(), b = newBase.getImag();
		double c = newExp.getReal(), d = newExp.getImag();

		double a2b2 = a * a + b * b;
		double lna2b2 = Math.log(a2b2);
		double arg = Math.atan2(b, a);

		double x = Math.pow(a2b2, c / 2) * Math.exp(-d * arg);
		double y = d * lna2b2 / 2 + c * arg;

		return new Number(x * Math.cos(y), x * Math.sin(y));
	}

	public Expression d(Variable var) {
		return new Add(
				new Multiply(exp, new Pow(base, new Subtract(exp, Number.ONE)),base.d(var)),
				new Multiply(this, new Ln(base), exp.d(var))).simplify2();
	}
	
	public Expression substitute(Variable var, Expression value) {
		return new Pow(base.substitute(var, value),
				exp.substitute(var, value));
	}

	public String getSymbol() {
		return "Exp";
	}
	
	public boolean usesSymbol() {
		return false;
	}

	public String toString() {
		return "(" + base + "^" + exp + ")";
	}

	public Expression getBase() {
		return base;
	}

	public Expression getExp() {
		return exp;
	}
	
	public boolean isExpNotation() {
		return true;
	}
}
