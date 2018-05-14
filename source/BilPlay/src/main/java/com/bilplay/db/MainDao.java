package com.bilplay.db;

import com.bilplay.model.*;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

public interface MainDao {
    @SqlQuery("SELECT * FROM user WHERE id = :id")
    @RegisterBeanMapper(User.class)
    User getUserById(@Bind("id") int id);

    @SqlQuery("SELECT id FROM user WHERE username = :username")
    @RegisterBeanMapper(User.class)
    int getIdByUsername(@Bind("username") String username);

    @SqlQuery("SELECT * FROM user WHERE username = :username")
    @RegisterBeanMapper(User.class)
    User getUserByUsername(@Bind("username") String username);

    @SqlQuery("SELECT u.* FROM user u JOIN friend f ON u.id = f.friend_id WHERE f.user_id = :id")
    @RegisterBeanMapper(User.class)
    List<User> getFriends(@Bind("id") int id);

    @SqlUpdate("INSERT INTO friend (user_id, friend_id) VALUES (:user_id, :friend_id)")
    void addFriend(@Bind("user_id") int userId, @Bind("friend_id") int friendId);

    @SqlUpdate("DELETE FROM friend WHERE user_id = :user_id AND friend_id = :friend_id")
    void deleteFriend(@Bind("user_id") int userId, @Bind("friend_id") int friendId);

    @SqlQuery("SELECT * FROM message WHERE (user_id = :user_id AND friend_id = :friend_id) OR (user_id = :friend_id AND friend_id = :user_id)")
    @RegisterBeanMapper(Message.class)
    List<Message> getMessages(@Bind("user_id") int userId, @Bind("friend_id") int friendId);

    @SqlUpdate("INSERT INTO message (user_id, friend_id, content) VALUES (:uid, :fid, :ct)")
    void addMessage(@Bind("uid") int uid, @Bind("fid") int fid, @Bind("ct") String ct);

    @SqlQuery("SELECT * FROM user WHERE email = :email")
    @RegisterBeanMapper(User.class)
    User getUserByEmail(@Bind("email") String email);

    @SqlUpdate("INSERT INTO `user` (`username`, `password`, `email` ) VALUES ( :username, :password, :email )")
    void addNewUser(@Bind("username") String username, @Bind("email") String email, @Bind("password") String password );

    @SqlQuery("SELECT * FROM game WHERE id = :id")
    @RegisterBeanMapper(Game.class)
    Game getGameById(@Bind("id") int id);

    @SqlQuery("SELECT * FROM review WHERE game_id = :id")
    @RegisterBeanMapper(Review.class)
    List<Review> getGameReviews(@Bind("id") int id);

    @SqlUpdate("UPDATE user SET budget = :budget WHERE id = :user_id")
    void updateUserBudget(@Bind("user_id") int userId, @Bind("budget") Double budget);

    @SqlUpdate("INSERT INTO purchase (user_id, game_id) VALUES (:user_id, :game_id)")
    void addPurchase(@Bind("user_id") int userId, @Bind("game_id") int gameId);

    @SqlQuery("SELECT game_id FROM purchase WHERE user_id = :id")
    List<Integer> getGamesByIdOfUser( @Bind("id") int id );

    @SqlQuery("SELECT id,name,price,pic,rating FROM game,(SELECT game_id,AVG(rating) as rating FROM review GROUP BY game_id) AS reviews WHERE id = game_id AND genre = :genre AND price > :lowPrice AND price <= :highPrice AND rating >= :lowRating AND rating <= :highRating")
    @RegisterBeanMapper(Game.class)
    List<Game> getGamesInStore( @Bind("genre") String genre, @Bind("lowPrice") int lowPrice,@Bind("highPrice") int highPrice, @Bind("lowRating") Double lowRating, @Bind("highRating") Double highRating);

    @SqlQuery("SELECT id,name,price,pic,rating FROM game,(SELECT game_id,AVG(rating) as rating FROM review GROUP BY game_id) AS reviews WHERE id = game_id AND price > :lowPrice AND price <= :highPrice AND rating >= :lowRating AND rating <= :highRating")
    @RegisterBeanMapper(Game.class)
    List<Game> getGamesInStoreNoGenre( @Bind("lowPrice") int lowPrice,@Bind("highPrice") int highPrice, @Bind("lowRating") Double lowRating, @Bind("highRating") Double highRating);

    @SqlUpdate("UPDATE user SET firstName = :firstName WHERE id = :id")
    void setFirstName(@Bind("firstName") String firstName, @Bind("id") int id);

    @SqlUpdate("UPDATE user SET lastName = :lastName WHERE id = :id")
    void setLastName(@Bind("lastName") String lastName, @Bind("id") int id);

    @SqlUpdate("UPDATE user SET budget = :budget + budget WHERE id = :id")
    void addBudget(@Bind("budget") double budget, @Bind("id") int id );

    @SqlUpdate("UPDATE user SET password = :password WHERE id = :id")
    void setPassword(@Bind("password") String password, @Bind("id") int id );

    @SqlQuery("SELECT * FROM invite WHERE user_id = :id AND accepted = 0")
    @RegisterBeanMapper(Invite.class)
    List<Invite> getPendingInvites(@Bind("id") int id);

    @SqlQuery("SELECT u.* FROM invite i JOIN user u ON i.user_id = u.id WHERE session_id = :id AND accepted = 1")
    @RegisterBeanMapper(User.class)
    List<User> getSessionUsers(@Bind("id") int id);

    @SqlQuery("SELECT * FROM session WHERE id = :id")
    @RegisterBeanMapper(Session.class)
    Session getSessionById(@Bind("id") int id);

    @SqlUpdate("INSERT INTO session (creator_id, game_id) VALUES (:cid, :gid)")
    @GetGeneratedKeys
    int createSession(@Bind("cid") int creatorId, @Bind("gid") int gameId);

    @SqlUpdate("UPDATE invite SET accepted = 1 WHERE session_id = :sid AND user_id = :uid")
    void acceptInvite(@Bind("sid") int sessionId, @Bind("uid") int userId);

    @SqlUpdate("UPDATE invite SET accepted_at = NOW() WHERE session_id = :sid AND user_id = :uid")
    void updateInviteAcceptedAt(@Bind("sid") int sessionId, @Bind("uid") int userId);

    @SqlUpdate("INSERT INTO invite (session_id, user_id) VALUES (:sid, :fid)")
    void addInvite(@Bind("sid") int sessionId, @Bind("fid") int friendId);

    @SqlUpdate("UPDATE invite SET left_at = NOW() WHERE session_id = :sid AND user_id = :uid")
    void leaveSession(@Bind("sid") int sessionId, @Bind("uid") int userId);

    @SqlUpdate("UPDATE session SET left_at = NOW() WHERE id = :id")
    void adminLeaveSession(@Bind("id") int id);

    @SqlQuery("SELECT * FROM review WHERE user_id = :user_id AND game_id = :game_id")
    @RegisterBeanMapper(Review.class)
    Review getReview( @Bind("user_id") int user_id , @Bind("game_id") int game_id );

    @SqlUpdate("INSERT INTO review (user_id,game_id,rating,comment) VALUES (':user_id',':game_id',':rating', ':comment')")
    void addReview( @Bind("user_id") int user_id , @Bind("game_id") int game_id , @Bind("rating") int rating, @Bind("comment") String comment);

    @SqlUpdate("UPDATE review SET rating = :rating WHERE user_id = :user_id AND game_id = :game_id")
    void updateReviewRating( @Bind("user_id") int user_id , @Bind("game_id") int game_id , @Bind("rating") int rating );

    @SqlUpdate("UPDATE review SET comment = :comment WHERE user_id = :user_id AND game_id = :game_id")
    void updateReviewComment( @Bind("user_id") int user_id , @Bind("game_id") int game_id , @Bind("comment") String comment );

}
