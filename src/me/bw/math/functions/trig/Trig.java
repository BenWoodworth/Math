package me.bw.math.functions.trig;

import me.bw.math.Expression;
import me.bw.math.Function;
import me.bw.math.Number;
import me.bw.math.functions.Add;
import me.bw.math.functions.Arccos;
import me.bw.math.functions.Arccot;
import me.bw.math.functions.Arccsc;
import me.bw.math.functions.Arcsec;
import me.bw.math.functions.Arcsin;
import me.bw.math.functions.Arctan;
import me.bw.math.functions.Cos;
import me.bw.math.functions.Cot;
import me.bw.math.functions.Csc;
import me.bw.math.functions.Divide;
import me.bw.math.functions.Sec;
import me.bw.math.functions.Sin;
import me.bw.math.functions.Sqrt;
import me.bw.math.functions.Square;
import me.bw.math.functions.Subtract;
import me.bw.math.functions.Tan;

public abstract class Trig extends Function {
	protected final static int OPP = 0, ADJ = 1, HYP = 2;
	
	private Expression num, o, a, h;
	private int topSide, bottomSide;
	
	public Trig(Expression num, int topSide, int bottomSide) {
		super(num);
		this.num = num;
		o = a = h = null;
		this.topSide = topSide;
		this.bottomSide = bottomSide;
		
		if (num instanceof Divide) {
			Divide d = (Divide) num;
			setSide(topSide, d.getTop());
			setSide(bottomSide, d.getBottom());
		} else {
			setSide(topSide, num);
			setSide(bottomSide, Number.ONE);
		}
		
		if (o == null) o = new Sqrt(new Subtract(new Square(h), new Square(a)));
		if (a == null) a = new Sqrt(new Subtract(new Square(h), new Square(o)));
		if (h == null) h = new Sqrt(new Add(new Square(o), new Square(a)));
	}
	
	private void setSide(int side, Expression val) {
		switch (side) {
		case OPP: o = val; break;
		case ADJ: a = val; break;
		case HYP: h = val; break;
		}
	}
	
	private Expression getSide(int side) {
		switch (side) {
		case OPP: return o;
		case ADJ: return a;
		case HYP: return h;
		}
		return null;
	}
	
	private Divide getDiv(int topSide, int bottomSide) {
		return new Divide(getSide(topSide), getSide(bottomSide));
	}
	
	public Expression getNum() { return num; }
	public Expression getOpposite() { return o; }
	public Expression getAdjacent() { return a; }
	public Expression getHypotenuse() { return h; }
	
	protected Expression simplify2() {
		if (this instanceof TrigFunction && num instanceof ArcTrigFunction) {
			Trig num = (Trig) this.num;
			return num.getDiv(topSide, bottomSide).simplify();
		}
		
		// TODO Doesn't apply to everything:
		if (this instanceof ArcTrigFunction && num instanceof TrigFunction) {
			Trig num = (Trig) this.num;
			if (this instanceof Arcsin && num instanceof Sin) return num.getNum();
			if (this instanceof Arccos && num instanceof Cos) return num.getNum();
			if (this instanceof Arctan && num instanceof Tan) return num.getNum();
			if (this instanceof Arccsc && num instanceof Csc) return num.getNum();
			if (this instanceof Arcsec && num instanceof Sec) return num.getNum();
			if (this instanceof Arccot && num instanceof Cot) return num.getNum();
		}
		
		return simplify3();
	}
	
	protected abstract Expression simplify3();
	
}
