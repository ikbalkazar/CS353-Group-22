package com.bilplay.view;

import io.dropwizard.views.View;

public class IndexView extends View {
    private final String message;

    public IndexView(String message) {
        super("index.ftl");
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
