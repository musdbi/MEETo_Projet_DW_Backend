package com.dauphine.meeto_backend.exceptions;

public class ResourceNotFoundException extends RuntimeException{

	public ResourceNotFoundException() {
		super("ResourceNotFoundException occured");
	}
	
	public ResourceNotFoundException (String msg) {
		super(msg);
	}

}