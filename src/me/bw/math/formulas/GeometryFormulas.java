package me.bw.math.formulas;

import me.bw.math.Constant;
import me.bw.math.Expression;
import me.bw.math.functions.Multiply;
import me.bw.math.functions.Square;
import me.bw.math.Number;

public class GeometryFormulas {
	
	public static Expression circleArea(Number radius) {
		return new Multiply(Constant.PI, new Square(radius));
	}
	public static Expression circlePerimeter(Number radius) {
		return new Multiply(new Number(2), Constant.PI, radius);
	}
	
	
}
