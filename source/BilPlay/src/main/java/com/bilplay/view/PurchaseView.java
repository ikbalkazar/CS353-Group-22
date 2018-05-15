package com.bilplay.view;

import com.bilplay.model.Game;
import com.bilplay.model.User;
import io.dropwizard.views.View;

public class PurchaseView extends View {
    private User user;
    private Game game;
    private String message;

    public PurchaseView(User user, Game game, String message) {
        super("purchase.ftl");
        this.user = user;
        this.game = game;
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public Game getGame() {
        return game;
    }

    public String getMessage() { return message; }
}
