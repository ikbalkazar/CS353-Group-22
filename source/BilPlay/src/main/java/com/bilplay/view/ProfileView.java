package com.bilplay.view;

import io.dropwizard.views.View;

public class ProfileView extends View{

    private String firstName;
    private String lastName;
    private String message;
    private double budget;

    public ProfileView( String firstName, String lastName, double budget , String message ){
        super( "profile.ftl" );

        this.firstName = firstName;
        this.lastName = lastName;
        this.message = message;
        this.budget = budget;
    }

    public String getFirstName(){   return firstName;   }

    public String getLastName(){    return lastName;    }

    public String getMessage(){   return message;   }

    public double getBudget(){ return budget; }

}