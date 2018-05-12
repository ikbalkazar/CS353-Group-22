package com.bilplay.auth;

import com.bilplay.db.MainDao;
import com.bilplay.model.User;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.net.URI;

@PreMatching
public class AuthRequestFilter implements ContainerRequestFilter {
    private MainDao dao;

    public AuthRequestFilter(MainDao dao) {
        this.dao = dao;
    }

    private void redirectToLogin() throws WebApplicationException {
        Response response = Response.seeOther(URI.create("/login")).build();
        throw new WebApplicationException(response);
    }

    @Override
    public void filter(ContainerRequestContext context) throws IOException {
        try {
            String username = context.getCookies().get("bilplay-username").getValue();
            String password = context.getCookies().get("bilplay-password").getValue();
            User user = dao.getUserByUsername(username);
            if (user == null || !user.getPassword().equals(password)) {
                redirectToLogin();
            }
            context.setSecurityContext(new BilPlaySecurityContext(user));
        } catch (Exception e) {
            redirectToLogin();
        }
    }
}
