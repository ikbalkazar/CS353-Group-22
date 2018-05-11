package com.bilplay.resources;

import com.bilplay.auth.Authenticated;
import com.bilplay.db.MainDao;
import com.bilplay.model.Game;
import com.bilplay.model.Review;
import com.bilplay.model.User;
import com.bilplay.view.GameView;
import com.bilplay.view.IndexView;
import com.bilplay.view.LoginView;
import com.bilplay.view.SignupView;

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

    @Path("/SignUp")
    @GET
    public SignupView Signup(){
	System.out.println("++++++++++++++++++++++++++++++++++++++");
	return new SignupView();
    }

    @Path("/login")
    @GET
    public LoginView login() {
        System.out.println("Login Page!");
        return new LoginView();
    }

    @Path("/submit_Signup")
    @POST
    public Response submitSignup(@FormParam("username") String username, @FormParam("email") String email, @FormParam("password") String password, @FormParam("password2") String password2 ){
	User userByName = dao.getUserByUsername( username );
	User userByEmail = dao.getUserByEmail( email );
	
	if( userByName == null && userByEmail == null && password == password2 ){
		dao.addNewUser( username, email, password );
		return Response.status( Response.Status.UNAUTHORIZED ).build();
	}
	return Response.status( Response.Status.UNAUTHORIZED ).build();


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

    @Path("/games/{id}")
    @GET
    @Authenticated
    public GameView game(@PathParam("id") int id) {
        Game game = dao.getGameById(id);
        List<Review> reviews = dao.getGameReviews(id);
        return new GameView(game, reviews);
    }
}
