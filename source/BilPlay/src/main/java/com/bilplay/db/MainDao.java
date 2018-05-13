package com.bilplay.db;

import com.bilplay.model.User;
import com.bilplay.model.Game;
import com.bilplay.model.Review;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
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
    

}
