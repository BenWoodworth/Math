package me.bw.math;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

import me.bw.math.exceptions.ArgException;

public abstract class Function extends Expression {
	private Expression[] args;

	public Function(Expression... args) {
		this.args = args;
	}

	public boolean equals(Object o) {
		if (o instanceof Function) {
			Function f = (Function) o;
			if (!f.getClass().equals(getClass())) return false;
			return Arrays.equals(args, f.args);
		}
		return false;
	}

	protected abstract Expression simplify2();
	protected abstract String getSymbol();

	public boolean usesSymbol() {
		return true;
	}

	public String toString() {
		return toString(getSymbol(), args);
	}
	
	protected static String toString(String symbol, Expression... args) {
		if (args.length == 1) {
			String a = args[0].toString();
			if (a.charAt(0) != '(') a = "(" + a + ")";
			return symbol + a;
		}
		String result = symbol + "(" + args[0];
		for (int i = 1; i < args.length; i++)
			result += ", " + args[i];
		return result + ")";
	}

	public Expression simplify() {
		if (!hasVars()) {
			Number n = evaluate();
			if (n.isInt()) return n;
			for (Constant c : Constant.constants)
				if (c.equals(n)) return c;
		}
		return simplify2();
	}

	protected void argException() {
		throw new ArgException();
	}

	public boolean hasVars() {
		for (Expression e : args)
			if (e.hasVars()) return true;
		return false;
	}

	protected static double sin(double d) { return Math.sin(d); }
	protected static double cos(double d) { return Math.cos(d); }
	protected static double tan(double d) { return Math.tan(d); }
	protected static double sinh(double d) { return Math.sinh(d); }
	protected static double cosh(double d) { return Math.cosh(d); }
	protected static double tanh(double d) { return Math.tanh(d); }
	
	public static Function getFunction(String name, Expression... args)
			throws ClassNotFoundException, NoSuchMethodException,
			SecurityException, InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		Class<?> c = getFunctionClass(name);
		Constructor<?> con = getConstructor(c, args);
		if (con == null) throw new ClassNotFoundException();
		return (Function) con.newInstance((Object[]) args);
	}
	
	private static Constructor<?> getConstructor(Class<?> c, Expression... args) {
		Expression[] simpArgs = new Expression[args.length];
		for (int i = 0; i < args.length; i++)
			simpArgs[i] = args[i].simplify();
		args = simpArgs;
		for (Constructor<?> con : c.getConstructors()) {
			if (con.getParameterCount() != args.length) continue;
			Class<?>[] types = con.getParameterTypes();
			boolean found = true;
			for (int i = 0; i < args.length; i++) {
				if (!types[i].isAssignableFrom(args[i].getClass())) {
					found = false;
					break;
				}
			}
			if (found) return con;
		}
		return null;
	}
	
	private static Class<?> getFunctionClass(String name) throws ClassNotFoundException {
		char[] newName = name.toCharArray();
		newName[0] = Character.toUpperCase(newName[0]);
		for (int i = 1; i < newName.length; i++)
			newName[i] = Character.toLowerCase(newName[i]);
		return Class.forName("me.bw.math.functions." + new String(newName));
	}

}
