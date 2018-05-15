package com.bilplay.resources;

import com.bilplay.auth.Authenticated;
import com.bilplay.db.MainDao;
import com.bilplay.model.*;
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
    public SignupView Signup( @DefaultValue("") @QueryParam("message") String message ){
	System.out.println("++++++++++++++++++++++++++++++++++++++");
	return new SignupView( message );
    }

    @Path("/login")
    @GET
    public LoginView login( @DefaultValue("") @QueryParam("message") String message ) {
        System.out.println("Login Page!");
        return new LoginView( message );
    }
    @Path("/logout")
    @GET
    public Response logout() {

        return Response.seeOther(URI.create("/login"))
            .cookie(new NewCookie("bilplay-username", "0000sa;ldfsdlkghsag" ))
            .cookie(new NewCookie("bilplay-password", "apogjal;gnrng;weg" ))
            .build();
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

        if ( game_id == 0 )
            game_id = games.get(0).getId();

        Game game = dao.getGameById(game_id);

        int timePlayed = 218;
        return new MyLibraryView( username, games, game_id, timePlayed, game );
    }

    @Path("/postReview")
    @POST
    @Authenticated
    public Response postReview( @Context SecurityContext context, @QueryParam("game_id") int game_id , @FormParam("review") String comment, @FormParam("rating") int rating  ){

        User user = (User)context.getUserPrincipal();

        Review r = dao.getReview( user.getId() , game_id );

        if ( r==null )
            dao.addReview( user.getId() , game_id , rating , comment );
        else{
            dao.updateReviewRating( user.getId() , game_id , rating );
            dao.updateReviewComment( user.getId() , game_id , comment );
        }

        return Response.seeOther(URI.create("/MyLibrary")).build();
    }

    @Path("/submit_signup")
    @POST
    public Response submitSignup(@FormParam("username") String username, @FormParam("email") String email, @FormParam("password") String password, @FormParam("password2") String password2 ){
	User userByName = dao.getUserByUsername( username );
	User userByEmail = dao.getUserByEmail( email );

	if ( userByName != null )
	    return Response.seeOther(URI.create("/SignUp?message=This%20username%20is%20currently%20in%20use!")).build();

	if ( userByEmail != null )
        return Response.seeOther(URI.create("/SignUp?message=This%20email%20is%20currently%20in%20use!")).build();

	if ( !password.equals(password2) )
        return Response.seeOther(URI.create("/SignUp?message=Passwords%20do%20not%20match!")).build();

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

        if ( user==null )
            return Response.seeOther(URI.create("/login?message=User%20not%20found!")).build();

        if ( !user.getPassword().equals(password) )
            return Response.seeOther(URI.create("/login?message=Incorrect%20password!")).build();

        return Response.seeOther(URI.create("/profile"))
                .cookie(new NewCookie("bilplay-username", username))
                .cookie(new NewCookie("bilplay-password", password))
                .build();
    }

    @Path("/games/{id}")
    @GET
    @Authenticated
    public GameView game(@PathParam("id") int id) {
        Game game = dao.getGameByIdIncludingRating(id);
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
        //System.out.printf("budget: %f\n", user.getBudget());
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
        List<Invite> invites = dao.getPendingInvites(user.getId());
        List<InviteBundle> inviteBundles = new ArrayList<>();
        for (Invite invite: invites) {
            Session session = dao.getSessionById(invite.getSessionId());
            Game game = dao.getGameById(session.getGameId());
            inviteBundles.add(new InviteBundle(invite, game, dao.getUserById(session.getCreatorId())));
        }
        return new FriendsView(friends, inviteBundles);
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

    @Path("/chat/{friendId}")
    @GET
    @Authenticated
    public ChatView chat(@PathParam("friendId") int friendId, @Context SecurityContext context) {
        User user = (User)context.getUserPrincipal();
        User friend = dao.getUserById(friendId);
        List<Message> messages = dao.getMessages(user.getId(), friend.getId());
        return new ChatView(messages, friend);
    }

    @Path("/message/{friendId}")
    @POST
    @Authenticated
    public Response message(@FormParam("message") String message, @PathParam("friendId") int friendId, @Context SecurityContext context) {
        User user = (User)context.getUserPrincipal();
        User friend = dao.getUserById(friendId);
        dao.addMessage(user.getId(), friend.getId(), message);
        String url = "/chat/" + friend.getId();
        return redirect(url);
    }

    @Path("/session/{id}")
    @GET
    @Authenticated
    public SessionView session(@PathParam("id") int sessionId, @Context SecurityContext context) {
        User user = (User)context.getUserPrincipal();
        Session session = dao.getSessionById(sessionId);
        Game game = dao.getGameById(session.getGameId());
        List<User> sessionUsers = dao.getSessionUsers(sessionId);
        List<User> friends = dao.getFriends(user.getId());
        List<SessionUser> sessionFriends = new ArrayList<>();
        for (User friend: friends) {
            Invite invite = dao.getInvite(sessionId, friend.getId());
            if (invite != null && invite.getAccepted() == 1) {
                continue;
            }
            sessionFriends.add(new SessionUser(friend, invite != null));
        }
        return new SessionView(user, game, session, sessionUsers, sessionFriends);
    }

    @Path("/create_session/{gid}")
    @GET
    @Authenticated
    public Response createSession(@PathParam("gid") int gameId, @Context SecurityContext context) {
        User user = (User)context.getUserPrincipal();
        int sessionId = dao.createSession(user.getId(), gameId);
        return redirect("/session/" + sessionId);
    }

    @Path("/join/{sid}")
    @GET
    @Authenticated
    public Response join(@PathParam("sid") int sessionId, @Context SecurityContext context) {
        User user = (User)context.getUserPrincipal();
        dao.acceptInvite(sessionId, user.getId());
        dao.updateInviteAcceptedAt(sessionId, user.getId());
        return redirect("/session/" + sessionId);
    }

    @Path("/invite/{sid}/{fid}")
    @GET
    @Authenticated
    public Response invite(@PathParam("sid") int sessionId, @PathParam("fid") int friendId, @Context SecurityContext context) {
        Session session = dao.getSessionById(sessionId);
        User user = (User)context.getUserPrincipal();
        if (session.getCreatorId() != user.getId()) {
            rejectRequest();
        }
        dao.addInvite(sessionId, friendId);
        return redirect("/session/" + sessionId);
    }

    @Path("/leave/{sid}")
    @GET
    @Authenticated
    public Response leave(@PathParam("sid") int sessionId, @Context SecurityContext context) {
        User user = (User)context.getUserPrincipal();
        Session session = dao.getSessionById(sessionId);
        if (user.getId() == session.getCreatorId()) {
            dao.adminLeaveSession(user.getId());
        } else {
            dao.leaveSession(sessionId, user.getId());
        }
        return redirect("/MyLibrary");
    }

    @Path("/store")
    @GET
    public StoreView store(@DefaultValue("null") @QueryParam("genre") String genre, @DefaultValue("-1") @QueryParam("price") int price,@DefaultValue("-1") @QueryParam("rating") int rating){
        Double lowRating, highRating;
        int lowPrice, highPrice;
        List<Game> games;
        if(price == -1){
          lowPrice = -1;
          highPrice = 100;
        }
        else{
          lowPrice = (price - 1) * 5;
          highPrice = price * 5;
        }
        if(rating == -1){
          lowRating = 0.0;
          highRating = 10.0;
        }
        else{
          lowRating = rating * 2.5;
          highRating = (rating + 1) * 2.5;
        }
        if(genre.equals("null"))
          games = dao.getGamesInStoreNoGenre(lowPrice,highPrice,lowRating,highRating);
        else
          games = dao.getGamesInStore(genre,lowPrice,highPrice,lowRating,highRating);
        return new StoreView(games);
    }

    @Path("/store_search")
    @POST
    public Response storeSearch(@DefaultValue("null") @FormParam("genre") String genre, @DefaultValue("-1") @FormParam("price") int price, @DefaultValue("-1") @FormParam("rating") int rating){
        return Response.seeOther(URI.create("/store?genre=" + genre +"&price=" + price + "&rating=" + rating)).build();
    }
}
