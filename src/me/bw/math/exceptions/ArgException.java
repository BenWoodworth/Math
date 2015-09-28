package me.bw.math.exceptions;

@SuppressWarnings("serial")
public class ArgException extends RuntimeException {
	public ArgException() {
		super("Incorrect arguments!");
	}
}
