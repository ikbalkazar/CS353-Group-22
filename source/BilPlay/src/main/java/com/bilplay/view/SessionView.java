package com.bilplay.view;

import com.bilplay.model.Game;
import com.bilplay.model.Session;
import com.bilplay.model.SessionUser;
import com.bilplay.model.User;
import io.dropwizard.views.View;

import java.util.List;

public class SessionView extends View {
    private User user;
    private Game game;
    private Session session;
    private List<User> users;
    private List<SessionUser> friends;

    public SessionView(User user, Game game, Session session, List<User> users, List<SessionUser> friends) {
        super("session.ftl");
        this.user = user;
        this.game = game;
        this.session = session;
        this.users = users;
        this.friends = friends;
    }

    public Game getGame() {
        return game;
    }

    public User getUser() {
        return user;
    }

    public Session getSession() {
        return session;
    }

    public List<User> getUsers() {
        return users;
    }

    public List<SessionUser> getFriends() {
        return friends;
    }

}
