package com.bilplay.resources;

import com.bilplay.auth.Authenticated;
import com.bilplay.db.MainDao;
import com.bilplay.model.User;
import com.bilplay.view.IndexView;
import com.bilplay.view.LoginView;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.List;

@Path("/")
@Produces(MediaType.TEXT_HTML)
public class MainResource {
    private final MainDao dao;

    public MainResource(MainDao dao) {
        this.dao = dao;
    }

    @Path("/index")
    @GET
    @Authenticated
    public Response index(@Context SecurityContext context) {
        User user = (User)context.getUserPrincipal();
        List<User> friends = dao.getFriends(user.getId());
        String message = user.getUsername() + ":";
        for (User friend: friends) {
            message = message + friend.getUsername();
            message = message + ",";
        }
        IndexView view = new IndexView(message);
        return Response.ok(view).build();
    }

    @Path("/login")
    @GET
    public LoginView login() {
        System.out.println("Login Page!");
        return new LoginView();
    }

    @Path("/submit_login")
    @POST
    public Response submitLogin(@FormParam("username") String username, @FormParam("password") String password) {
        System.out.printf("username: %s password:%s\n", username, password);
        User user = dao.getUserByUsername(username);
        if (user == null || !user.getPassword().equals(password)) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        return Response.seeOther(URI.create("/index"))
                .cookie(new NewCookie("bilplay-username", username))
                .cookie(new NewCookie("bilplay-password", password))
                .build();
    }
}
