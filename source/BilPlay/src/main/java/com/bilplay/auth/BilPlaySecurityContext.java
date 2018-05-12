package com.bilplay.auth;

import com.bilplay.model.User;

import javax.ws.rs.core.SecurityContext;
import java.security.Principal;

public class BilPlaySecurityContext implements SecurityContext {
    private User user;

    public BilPlaySecurityContext(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    @Override
    public Principal getUserPrincipal() {
        return user;
    }

    @Override
    public boolean isUserInRole(String s) {
        return false;
    }

    @Override
    public boolean isSecure() {
        return true;
    }

    @Override
    public String getAuthenticationScheme() {
        return null;
    }
}
