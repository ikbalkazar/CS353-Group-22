package com.bilplay.model;

public class SessionUser {
    private User user;
    private boolean invited;

    public SessionUser(User user, boolean invited) {
        this.user = user;
        this.invited = invited;
    }

    public User getUser() {
        return user;
    }

    public boolean getInvited() {
        return invited;
    }
}
