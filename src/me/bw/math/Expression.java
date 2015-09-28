package me.bw.math;

import java.util.ArrayList;
import java.util.List;

import me.bw.math.exceptions.ParseException;

public abstract class Expression {
	public abstract Expression simplify();
	public abstract Number evaluate();
	public abstract Expression d(Variable var);
	public abstract Expression substitute(Variable var, Expression value);
	public abstract String toString();
	public abstract boolean equals(Object o);
	public abstract boolean hasVars();

	public static Expression parse(String s) throws ParseException { //TODO Remove leading/trailing spaces?
		StringBuilder sb = new StringBuilder();
		char[] chars = s.toCharArray();
		int numOpen = 0;
		for (int i = 0; i < chars.length; i++) {
			if (chars[i] == '[') chars[i] = '(';
			if (chars[i] == ']') chars[i] = ')';
			if (chars[i] == '(') numOpen++;
			if (chars[i] == ')') numOpen--;
			if (numOpen < 0) break;
		}
		if (numOpen != 0) throw new ParseException("Parentheses don't match");

		// Functions/Parentheses
		List<Integer> open = new ArrayList<Integer>();
		List<Integer> close = new ArrayList<Integer>();
		numOpen = 0;
		for (int i = 0; i < chars.length; i++) {
			if (chars[i] == '(') {
				if (numOpen == 0) open.add(i);
				numOpen++;
			} else if (chars[i] == ')') {
				if (numOpen == 1) close.add(i);
				numOpen--;
			}
		}
		Expression[] parts = new Expression[open.size()];

		for (int i = 0; i < open.size(); i++) {
			boolean firstSpace = true;
			int pos, end = -1;
			for (pos = open.get(i) - 1; pos >= 0; pos--) {
				char c = chars[pos];
				if (c == ' ' && !firstSpace) break;
				if (firstSpace && c != ' ') end = pos;
				if (c != ' ') firstSpace = false;
				if (isOperator(c)) break;
			}
			pos++;

			sb.setLength(0);
			for (int j = pos; j <= end; j++)
				sb.append(chars[j]);
			String func = sb.toString();

			if (func.equals("")) {
				sb.setLength(0);
				for (int j = open.get(i) + 1; j < close.get(i); j++)
					sb.append(chars[j]);
				parts[i] = parse(sb.toString());
			} else {
				List<Integer> commas = new ArrayList<Integer>();
				for(int j = open.get(i) + 1; j < close.get(i); j++)
					if (chars[j] == ',') commas.add(j);
				int argSize = commas.size() + 1;
				if (argSize == 1) {
					boolean empty = true;
					for (int j = open.get(i) + 1; j < close.get(i) && empty; j++)
						if (chars[j] != ' ') empty = false;
					if (empty) argSize = 0;
				}
				Expression[] args = new Expression[argSize];
				if (argSize > 0) {
					commas.add(0, open.get(i));
					commas.add(close.get(i));
					for(int j = 0; j < commas.size() - 1; j++) {
						sb.setLength(0);
						for (int n = commas.get(j) + 1; n < commas.get(j + 1); n++)
							sb.append(chars[n]);
						args[j] = parse(sb.toString());
					}
				}
				open.set(i, pos);
				try {
					parts[i] = Function.getFunction(func, args);
				} catch (Exception e) {
					sb.setLength(0);
					for (int j = pos; j <= close.get(i); j++)
						sb.append(chars[j]);
					throw new ParseException("Invalid function: " + sb.toString());
				}
			}
			
			// Factorial
			int count = 0;
			for (char c : chars) if (c == '!') count++;
			if (count > 0) throw new ParseException("Factorials not yet supported!");
		}
		
		

		return new Variable('y');
	}

	private static boolean isOperator(char c) {
		switch (c) {
		case '+': case '-':
		case '*': case '/':
		case '(': case ')':
		case ',': case '!':
		case '^':
			return true;
		}
		return false;
	}
}
