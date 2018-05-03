package com.bilplay.db;

import com.bilplay.model.User;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

public interface MainDao {
    @SqlQuery("SELECT * FROM user WHERE id = :id")
    @RegisterBeanMapper(User.class)
    User getUserById(@Bind("id") int id);
}
