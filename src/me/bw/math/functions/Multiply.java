package me.bw.math.functions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import me.bw.math.Constant;
import me.bw.math.Expression;
import me.bw.math.Function;
import me.bw.math.Number;
import me.bw.math.Symbol;
import me.bw.math.Variable;

public class Multiply extends Function {
	private Expression[] nums;

	public Multiply(Expression... nums) {
		super(nums);
		this.nums = nums;
	}

	protected Expression simplify2() {
		if (nums.length == 0) return Number.ONE;

		List<Expression> toLoop = new ArrayList<Expression>();
		for (Expression e : nums) toLoop.add(e);

		double real = 1, imag = 0;
		List<Expression> consts = new ArrayList<Expression>();
		List<Expression> vars = new ArrayList<Expression>();
		List<Expression> nonNumbers = new ArrayList<Expression>();

		for (int i = 0; i < toLoop.size(); i++) {
			Expression cur = toLoop.get(i).simplify();
			if (cur instanceof Variable) {
				vars.add((Variable) cur);
			}else if (cur instanceof Constant) {
				consts.add((Constant) cur);
			} else if (cur instanceof Number) {
				Number n = (Number) cur;
				double a = real, b = imag, c = n.getReal(), d = n.getImag();
				real = a * c - b * d; imag = a * d + b * c;
			} else if (cur instanceof Multiply) {
				for (Expression e : ((Multiply) cur).nums)
					toLoop.add(e);
			} else if (cur instanceof Negate) {
				real *= -1; imag *= -1;
				toLoop.add(((Negate) cur).getNum());
			} else if (cur instanceof Divide) {
				toLoop.add(((Divide) cur).getTop());
				toLoop.add(new Pow(((Divide) cur).getBottom(), new Number(-1)));
			} else {
				nonNumbers.add(cur);
			}
		}
		
		if (real == 0 && imag == 0) return Number.ZERO;
		
		boolean addNumber = (Math.abs(real) != 1 || imag != 0);
		int size = consts.size() + vars.size() + nonNumbers.size();
		if (addNumber) size++;
		int pos = 0;
		
		Expression[] arr = new Expression[size];
		if (addNumber) arr[pos++] = new Number(real, imag);
		for (Expression c : consts) arr[pos++] = c;
		for (Expression v : vars) arr[pos++] = v;
		for (Expression e : nonNumbers) arr[pos++] = e;
		
		arr = merge(arr);
		if (!addNumber && real == -1)
			arr[0] = new Negate(arr[0]);
		
		if (arr.length == 1) return arr[0];
		Expression result = new Multiply(arr);
		if (real == -1 && imag == 0) result = new Negate(result);
		return result;
	}
	
	private Expression[] merge(Expression[] nums) {
		HashMap<Expression, List<Expression>> map = new HashMap<Expression, List<Expression>>();
		for (int i = 0; i < nums.length; i++) {
			Expression curBase = nums[i];
			Expression curPow = Number.ONE;
			if (nums[i] instanceof Pow) {
				curBase = ((Pow) nums[i]).getBase();
				curPow = ((Pow) nums[i]).getExp();
			}
			List<Expression> curAdd = map.get(curBase);
			if (curAdd == null) curAdd = new ArrayList<Expression>();
			curAdd.add(curPow);
			map.put(curBase, curAdd);
		}
		List<Expression> res = new ArrayList<Expression>();
		for (Expression e : nums) {
			Expression curBase = e;
			if (e instanceof Pow) e = ((Pow) e).getBase();
			List<Expression> curPow = map.get(e);
			if (curPow == null) continue;
			Expression newPow = new Add(curPow.toArray(new Expression[0])).simplify();
			if (newPow.equals(Number.ZERO));
			else if (newPow.equals(Number.ONE)) res.add(curBase);
			else res.add(new Pow(e, newPow).simplify());
			map.put(e, null);
		}
		return res.toArray(new Expression[0]);
	}

	public Number evaluate() {
		double real = 1, imag = 0;
		for (int i = 0; i < nums.length; i++) {
			Number cur = nums[i].evaluate();
			double a = real, b = imag;
			double c = cur.getReal(), d = cur.getImag();
			real = a * c - b * d;
			imag = a * d + b * c;
		}
		return new Number(real, imag);
	}

	public Expression d(Variable var) {
		Expression[] toAdd = new Expression[nums.length];
		for (int i = 0; i < toAdd.length; i++) {
			Expression[] curMult = new Expression[toAdd.length];
			for (int m = 0; m < curMult.length; m++) {
				curMult[m] = nums[m];
				if (m == i) curMult[m] = curMult[m].d(var);
			}
			toAdd[i] = new Multiply(curMult).simplify();
		}
		return new Add(toAdd).simplify();
	}
	
	public Expression substitute(Variable var, Expression value) {
		Expression[] result = new Expression[nums.length];
		for (int i = 0; i < result.length; i++)
			result[i] = nums[i].substitute(var, value);
		return new Multiply(result);
	}

	public String getSymbol() {
		return "Multiply";
	}
	
	public boolean usesSymbol() {
		return false;
	}

	public String toString() {
		if (nums.length == 0) return "1";
		if (nums.length == 1) return nums[0].toString();
		StringBuilder sb = new StringBuilder("(");
		sb.append(nums[0]);
		Expression prev = nums[0];
		for (int i = 1; i < nums.length; i++) {
			Expression cur = nums[i];
			if (!((prev instanceof Number || prev instanceof Variable) && hasCharBase(cur)))
				sb.append(" * ");
			else
				sb.append(' ');
			sb.append(cur);
			prev = cur;
		}
		return sb.append(")").toString();
	}

	private boolean hasCharBase(Expression e) {
		if (e instanceof Function) {
			Function f = (Function) e;
			if (f.usesSymbol()) return true;
			if (f instanceof Pow && ((Pow) f).isExpNotation())
				return ((Pow) f).getBase() instanceof Symbol;
		} else if (e instanceof Symbol) {
			return true;
		}
		return false;
	}

}
