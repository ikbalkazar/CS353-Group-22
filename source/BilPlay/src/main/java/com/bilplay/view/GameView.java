package com.bilplay.view;

import com.bilplay.model.Game;
import com.bilplay.model.Review;
import io.dropwizard.views.View;

import java.util.List;

public class GameView extends View {
    private Game game;
    private List<Review> reviews;

    public GameView(Game game, List<Review> reviews) {
        super("game.ftl");
        this.game = game;
        this.reviews = reviews;
    }

    public Game getGame() {
        return game;
    }

    public List<Review> getReviews() {
        return reviews;
    }
}
