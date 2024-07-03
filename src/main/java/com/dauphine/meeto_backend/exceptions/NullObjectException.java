package com.dauphine.meeto_backend.exceptions;

public class NullObjectException extends RuntimeException{
	
	public NullObjectException() {
		super("NullObjectException occured");
	}
	
	public NullObjectException(String msg) {
		super(msg);
	}

}