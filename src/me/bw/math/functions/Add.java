package me.bw.math.functions;

import java.util.ArrayList;
import java.util.List;

import me.bw.math.Constant;
import me.bw.math.Expression;
import me.bw.math.Function;
import me.bw.math.Number;
import me.bw.math.Variable;

public class Add extends Function {
	Expression[] nums;
	
	public Add(Expression... args) {
		super(args);
		nums = args;
	}

	protected Expression simplify2() {
		if (nums.length == 0) return Number.ZERO;

		List<Expression> toLoop = new ArrayList<Expression>();
		for (Expression e : nums) toLoop.add(e);

		double real = 0, imag = 0;
		List<Constant> consts = new ArrayList<Constant>();
		List<Variable> vars = new ArrayList<Variable>();
		List<Expression> nonNumbers = new ArrayList<Expression>();

		for (int i = 0; i < toLoop.size(); i++) {
			Expression cur = toLoop.get(i).simplify();
			if (cur instanceof Variable) {
				vars.add((Variable) cur);
			}else if (cur instanceof Constant) {
				consts.add((Constant) cur);
			} else if (cur instanceof Number) {
				real += ((Number) cur).getReal();
				imag += ((Number) cur).getImag();
			} else if (cur instanceof Add) {
				for (Expression e : ((Add) cur).nums)
					toLoop.add(e);
			} else {
				nonNumbers.add(cur);
			}
		}
		
		boolean addNumber = (real != 0 || imag != 0);
		int size = consts.size() + vars.size() + nonNumbers.size();
		if (addNumber) size++;
		int pos = 0;
		
		Expression[] result = new Expression[size];
		if (addNumber) result[pos++] = new Number(real, imag);
		for (Constant c : consts) result[pos++] = c;
		for (Variable v : vars) result[pos++] = v;
		for (Expression e : nonNumbers) result[pos++] = e;
		
		if (result.length == 1) return result[0];
		return new Add(result);
	}

	public Number evaluate() {
		double a = 0, b = 0;
		for (int i = 0; i < nums.length; i++) {
			Number cur = nums[i].evaluate();
			a += cur.getReal();
			b += cur.getImag();
		}
		return new Number(a, b);
	}

	public Expression d(Variable var) {
		Expression[] result = new Expression[nums.length];
		for (int i = 0; i < result.length; i++)
			result[i] = nums[i].d(var);
		return new Add(result);
	}
	
	public Expression substitute(Variable var, Expression value) {
		Expression[] result = new Expression[nums.length];
		for (int i = 0; i < result.length; i++)
			result[i] = nums[i].substitute(var, value);
		return new Add(result);
	}

	public String getSymbol() {
		return "Add";
	}
	
	public boolean usesSymbol() {
		return false;
	}

	public String toString() {
		if (nums.length == 0) return "0";
		if (nums.length == 1) return nums[0].toString();
		StringBuilder sb = new StringBuilder("(");
		sb.append(nums[0].toString());
		for (int i = 1; i < nums.length; i++)
			sb.append(" + ").append(nums[i].toString());
		return sb.append(")").toString();
	}

}
