
import me.bw.math.Expression;
import me.bw.math.Function;
import me.bw.math.Number;
import me.bw.math.Variable;
import me.bw.math.functions.Cos;
import me.bw.math.functions.Derivative;
import me.bw.math.functions.Multiply;
import me.bw.math.functions.Sin;

public class Test {
	/* TODO Fix
	 * Cube derivative incorrect.
	 * e^(2*pi*i) incorrect.
	 *
	 * TODO Implement functions
	 * factorial/gamma
	 * integral
	 *
	 * TODO
	 * Inverse function evaluation
	 * Modulus on inverse functions by 2pi
	 * functions not merging in multiply
	 * 
	 * TODO Test
	 * Factorial
	 * Rand
	 * Taylor
	 * Floor/Ceil
	 */

	public static void main(String args[]) throws Exception {
		//p(Function.getFunction("sin"));
		p(Expression.parse("1 + sin(x, hi mom!) + cos(5)"));
		
		Variable x = new Variable('x');
		
		System.out.println(new Sin(x).equals(new Sin(x)));
		
		Expression e = new Derivative(new Multiply(new Sin(x), new Cos(x)), x, new Number(3));
		//Expression e = new Multiply(new Square(x), new Square(x));
		
		p(e);
		p(e.simplify());
		p(e.simplify().substitute(x, new Number(2)));
		p(e.substitute(x, new Number(2)).evaluate());
	}

	public static void p(Expression e) {
		System.out.println(e);
	}
}
