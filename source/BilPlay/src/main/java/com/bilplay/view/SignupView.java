package com.bilplay.view;

import io.dropwizard.views.View;

public class SignupView extends View{

	private String message;

	public SignupView( String message ){
		super("signup.ftl");
		this.message = message;
	}

	public String getMessage() {	return message;	}

}
