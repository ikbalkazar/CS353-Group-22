package com.bilplay.view;

import com.bilplay.model.Game;
import java.util.List;
import com.bilplay.model.Review;
import io.dropwizard.views.View;

public class StoreView extends View {
    private List<Game> games;

    public StoreView(List<Game> games) {
        super("store.ftl");
        this.games = games;
    }

    public List<Game> getGames() {
        return games;
    }
}
