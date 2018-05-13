package com.bilplay.resources;

import com.bilplay.auth.Authenticated;
import com.bilplay.db.MainDao;
import com.bilplay.model.Game;
import com.bilplay.model.Review;
import com.bilplay.model.User;
import com.bilplay.view.*;


import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.*;

@Path("/")
@Produces(MediaType.TEXT_HTML)
public class MainResource {
    private final MainDao dao;

    public MainResource(MainDao dao) {
        this.dao = dao;
    }

    private static Response rejectRequest() {
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    private static Response acceptRequest() { return Response.status(Response.Status.OK).build(); }

    private static Response redirect(String url) {
        return Response.seeOther(URI.create(url)).build();
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

    @Path("/MyLibrary")
    @GET
    @Authenticated
    public MyLibraryView MyLibrary( @Context SecurityContext context, @QueryParam("game_id") int game_id ){
        User user = (User)context.getUserPrincipal();
        String username = user.getName();
        int id = dao.getIdByUsername( username );
        List<Integer> gamesID = dao.getGamesByIdOfUser( id );
        ArrayList<Game> games = new ArrayList<Game>();
        for( int i = 0 ; i < gamesID.size() ; i++ ){
            games.add( dao.getGameById( gamesID.get(i) ) );
        }
        return new MyLibraryView( username, games, game_id );
    }

    @Path("/submit_signup")
    @POST
    public Response submitSignup(@FormParam("username") String username, @FormParam("email") String email, @FormParam("password") String password, @FormParam("password2") String password2 ){
	User userByName = dao.getUserByUsername( username );
	User userByEmail = dao.getUserByEmail( email );
	
	if( userByName == null && userByEmail == null && password.equals(password2) ){
		dao.addNewUser( username, email, password );
	    return Response.seeOther(URI.create("/index"))
                .cookie(new NewCookie("bilplay-username", username))
                .cookie(new NewCookie("bilplay-password", password))
                .build();
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
        return Response.seeOther(URI.create("/profile"))
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

    @Path("/purchase/{id}")
    @GET
    @Authenticated
    public PurchaseView purchase(@PathParam("id") int id, @Context SecurityContext context) {
        User user = (User)context.getUserPrincipal();
        Game game = dao.getGameById(id);
        return new PurchaseView(user, game);
    }

    @Path("/make_purchase/{id}")
    @POST
    @Authenticated
    public Response makePurchase(@PathParam("id") int id, @Context SecurityContext context) {
        User user = (User)context.getUserPrincipal();
        Game game = dao.getGameById(id);
        System.out.printf("budget: %f\n", user.getBudget());
        if (user.getBudget() < game.getPrice()) {
            return rejectRequest();
        }
        Double newBudget = user.getBudget() - game.getPrice();
        dao.updateUserBudget(user.getId(), newBudget);
        dao.addPurchase(user.getId(), game.getId());
        return redirect("/MyLibrary");
    }

    @Path("/friends")
    @GET
    @Authenticated
    public FriendsView friends(@Context SecurityContext context) {
        User user = (User)context.getUserPrincipal();
        List<User> friends = dao.getFriends(user.getId());
        return new FriendsView(friends);
    }

    @Path("/add_friend")
    @POST
    @Authenticated
    public Response addFriend(@FormParam("username") String username, @Context SecurityContext context) {
        User user = (User)context.getUserPrincipal();
        User friend = dao.getUserByUsername(username);
        if (friend == null) {
            return rejectRequest();
        }
        dao.addFriend(user.getId(), friend.getId());
        return redirect("/friends");
    }

    @Path("/delete_friend/{friendId}")
    @POST
    @Authenticated
    public Response deleteFriend(@PathParam("friendId") int friendId, @Context SecurityContext context) {
        User user = (User)context.getUserPrincipal();
        User friend = dao.getUserById(friendId);
        if (friend == null) {
            return rejectRequest();
        }
        dao.deleteFriend(user.getId(), friend.getId());
        return redirect("/friends");
    }

    @Path("/profile")
    @GET
    @Authenticated
    public ProfileView profile(@DefaultValue("") @QueryParam("message") String message, @Context SecurityContext context){
        User user = (User)context.getUserPrincipal();
        return new ProfileView( user.getFirstName() , user.getLastName() , user.getBudget(), message );
    }

    @Path("/addBudget")
    @POST
    @Authenticated
    public Response addBudget( @FormParam("budget") double budget , @Context SecurityContext context ){
        User user = (User)context.getUserPrincipal();
        if ( budget > 0.0 )
            dao.addBudget( budget , user.getId() );

        return Response.seeOther(URI.create("/profile")).build();

    }

    @Path("/nameChange")
    @POST
    @Authenticated
    public Response nameChange(@FormParam("firstName") String firstName, @FormParam("lastName") String lastName, @Context SecurityContext context){
        User user = (User)context.getUserPrincipal();
        dao.setFirstName( firstName , user.getId() );
        dao.setLastName( lastName , user.getId() );

        return Response.seeOther(URI.create("/profile")).build();
    }

    @Path("/passwordChange")
    @POST
    @Authenticated
    public Response passwordChange(@FormParam("password") String password, @FormParam("newPassword") String newPassword, @FormParam("reNewPassword") String reNewPassword, @Context SecurityContext context){
        User user = (User)context.getUserPrincipal();
        if ( !user.getPassword().equals( password ) )
            return Response.seeOther(URI.create("/profile?message=Incorrect%20Password%20!")).build();
        if ( !newPassword.equals( reNewPassword ) )
            return Response.seeOther(URI.create("/profile?message=New%20passwords%20does%20not%20match%20!")).build();

        dao.setPassword( newPassword , user.getId() );

        return Response.seeOther(URI.create("/profile?message=Password%20Changed%20!")).build();


    }

}


