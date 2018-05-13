package com.bilplay.view;

import com.bilplay.model.User;
import io.dropwizard.views.View;

import java.util.List;

public class FriendsView extends View {
    private List<User> friends;

    public FriendsView(List<User> friends) {
        super("friends.ftl");
        this.friends = friends;
    }

    public List<User> getFriends() {
        return friends;
    }
}
