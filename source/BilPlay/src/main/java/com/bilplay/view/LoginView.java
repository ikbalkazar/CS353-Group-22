package com.bilplay.view;

import io.dropwizard.views.View;

public class LoginView extends View {

    private String message;

    public LoginView( String message ) {
        super("login.ftl");
        this.message = message;
    }

    public String getMessage(){   return message;   }

}
