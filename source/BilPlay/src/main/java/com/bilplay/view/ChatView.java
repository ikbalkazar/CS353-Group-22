package com.bilplay.view;

import com.bilplay.model.Message;
import com.bilplay.model.User;
import io.dropwizard.views.View;

import java.util.List;

public class ChatView extends View {
    private List<Message> messages;
    private User friend;

    public ChatView(List<Message> messages, User friend) {
        super("chat.ftl");
        this.messages = messages;
        this.friend = friend;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public User getFriend() {
        return friend;
    }
}
