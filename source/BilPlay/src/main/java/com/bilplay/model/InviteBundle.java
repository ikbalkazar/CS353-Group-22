package com.bilplay.model;

public class InviteBundle {
    private Invite invite;
    private Game game;
    private User inviter;

    public InviteBundle(Invite invite, Game game, User inviter) {
        this.invite = invite;
        this.game = game;
        this.inviter = inviter;
    }

    public Invite getInvite() {
        return invite;
    }

    public Game getGame() {
        return game;
    }

    public User getInviter() {
        return inviter;
    }
}
