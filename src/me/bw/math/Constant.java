package me.bw.math;

public class Constant extends Number implements Symbol {
	public static final Constant
	PI = new Constant(Math.PI, 'π'),
	E = new Constant(Math.E, 'e'),
	PHI = new Constant((1 + Math.sqrt(5)) / 2, 'φ'),
	EULER = new Constant(0.57721566490153286060651209008240243104215933593992, 'γ');

	public static final Constant[] constants = {PI, E, PHI, EULER};
	
	private char symbol;

	public Constant(double real, char symbol) {
		super(real);
		this.symbol = symbol;
	}

	public Constant(double real, double imag, char symbol) {
		super(real, imag);
		this.symbol = symbol;
	}

	public Constant simplify2() {
		return this;
	}

	public char getSymbol() {
		return symbol;
	}

	public String toString() {
		return Character.toString(symbol);
	}
}
