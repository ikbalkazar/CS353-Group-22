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

    @SqlQuery("SELECT * FROM user WHERE username = :username")
    @RegisterBeanMapper(User.class)
    User getUserByUsername(@Bind("username") String username);

    @SqlQuery("SELECT u.* FROM user u JOIN friend f ON u.id = f.friend_id WHERE f.user_id = :id")
    @RegisterBeanMapper(User.class)
    List<User> getFriends(@Bind("id") int id);

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
}