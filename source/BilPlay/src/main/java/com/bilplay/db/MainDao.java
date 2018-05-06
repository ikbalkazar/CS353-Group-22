package com.bilplay.db;

import com.bilplay.model.User;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

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
}
