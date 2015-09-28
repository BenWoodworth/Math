package me.bw.math.functions.trig;

import me.bw.math.Constant;
import me.bw.math.Expression;
import me.bw.math.Number;
import me.bw.math.functions.Add;
import me.bw.math.functions.Arg;
import me.bw.math.functions.Multiply;
import me.bw.math.functions.Sqrt;
import me.bw.math.functions.Square;
import me.bw.math.functions.Subtract;

public abstract class ArcTrigFunction extends Trig {

	public ArcTrigFunction(Expression num, int topSide, int bottomSide) {
		super(num, topSide, bottomSide);
	}

	protected double arcsinRe(Number n) {
		Expression a = new Multiply(Constant.I, n);
		Expression b = new Sqrt(new Subtract(Number.ONE, new Square(n)));
		return new Arg(new Add(a, b)).evaluate().getReal();
	}
	
	protected double arcsinIm(Number n) {
		double a = n.getReal(), b = n.getImag();
		double cbrta = -a * a + b * b + 1;
		double cbrt = Math.pow(4 * a * a * b * b + cbrta * cbrta, .25);
		double trg = new Arg(new Subtract(Number.ONE, new Square(n))).evaluate().getReal() / 2;
		double u = a + cbrt * sin(trg);
		double d = -b + cbrt * cos(trg);
		return -Math.log(Math.sqrt(u * u + d * d));
	}
	
}
