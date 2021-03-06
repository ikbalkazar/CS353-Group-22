package com.bilplay.view;

import com.bilplay.model.InviteBundle;
import com.bilplay.model.User;
import io.dropwizard.views.View;

import java.util.List;

public class FriendsView extends View {
    private List<User> friends;
    private List<InviteBundle> invites;

    public FriendsView(List<User> friends, List<InviteBundle> invites) {
        super("friends.ftl");
        this.friends = friends;
        this.invites = invites;
    }

    public List<User> getFriends() {
        return friends;
    }

    public List<InviteBundle> getInvites() {
        return invites;
    }
}
