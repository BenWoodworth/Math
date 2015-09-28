package me.bw.math;


public class Number extends Expression {
	public static final Number ZERO = new Number(0);
	public static final Number ONE = new Number(1);
	public static final Number I = new Number(0, 1);

	private static final double epsilon = .0000000000001;

	double real, imag;
	boolean whole;

	public Number(double real, double imaginary) {
		this.real = real;
		this.imag = imaginary;
		whole = (int) real == real && (int) imag == imag;
	}
	public Number(double real) {
		this(real, 0);
	}

	public double getReal() {
		return real;
	}

	public double getImag() {
		return imag;
	}

	public Expression substitute(Variable var, Expression value) {
		return this;
	}

	public Number simplify() {
		return this;
	}

	public Number evaluate() {
		return this;
	}

	public boolean isReal() {
		return imag == 0;
	}

	public Number negate() {
		return new Number(-real, -imag);
	}

	public String toString() {
		String im = formatDouble(imag), re = formatDouble(real);
		
		if (im.equals("0")) {
			if (real < 0) return "(" + formatDouble(real) + ")";
			return formatDouble(real);
		} else if (re.equals("0")) {
			if (imag == 1 || imag == -1)
				return imag == 1 ? "i" : "(-i)";
			else
				return "(" + formatDouble(imag) + "i)";
		}
		String i;
		if (imag == 1 || imag == -1)
			i = imag == 1 ? "i" : "(-i)";
		else
			i = formatDouble(Math.abs(imag)) + "i";
		return "(" + formatDouble(real) + (imag < 0 ? " - " : " + ") + i + ")";
	}
	
	private static final int maxDecimalLen = 8;
	private static String formatDouble(double d) {
		String s = Double.toString(d);
		boolean negative = false;
		if (s.charAt(0) == '-') {
			negative = true;
			s = s.substring(1);
		}
		String[] split = s.split("E");
		int move = split.length == 2 ? Integer.parseInt(split[1]) : 0;
		String num = split[0];
		if (move != 0) {
			int pos = -1;
			for (int i = 0; i < num.length(); i++) {
				if (num.charAt(i) == '.') {
					pos = i;
					break;
				}
			}
			num = num.substring(0, pos) + num.substring(pos + 1);
			pos += move;
			StringBuilder sb = new StringBuilder();
			for (int i = Math.min(pos - 1, 0); i < Math.max(pos, num.length() - 1); i++) {
				if (i == pos) sb.append('.');
				if (i < 0 || i >= num.length()) {
					sb.append('0');
				} else {
					sb.append(num.charAt(i));
				}
			}
			num = sb.toString();
		}
		
		String[] decSplit = num.split("\\.");
		if (decSplit.length == 2) {
			if (decSplit[1].length() > maxDecimalLen)
				num = decSplit[0] + '.' + decSplit[1].substring(0, maxDecimalLen);
		}
		
		for (int i = num.length() - 1; i > 0; i--) {
			if (num.charAt(i) == '.') {
				num = num.substring(0, i);
				break;
			} else if (num.charAt(i) != '0') {
				break;
			}
		}
		
		if (negative) num = '-' + num;
		return num;
	}

	public Expression d(Variable var) {
		return Number.ZERO;
	}

	public boolean equals(Object o) {
		if (!(o instanceof Number)) return false;
		if (Math.abs(real - ((Number) o).real) >= epsilon) return false;
		return Math.abs(imag - ((Number) o).imag) < epsilon;
	}

	public boolean isInt() {
		return whole;
	}

	public boolean isRealInt() {
		return whole && imag == 0;
	}

	public boolean hasVars() {
		return false;
	}
}
