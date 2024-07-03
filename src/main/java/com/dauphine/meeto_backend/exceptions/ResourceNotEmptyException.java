package com.dauphine.meeto_backend.exceptions;

public class ResourceNotEmptyException extends RuntimeException{

	public ResourceNotEmptyException() {
		super("ResourceNotEmptyException occured");
	}
	
	public ResourceNotEmptyException(String msg) {
		super(msg);
	}

}

