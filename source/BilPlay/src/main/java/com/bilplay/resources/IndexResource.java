package com.bilplay.resources;

import com.bilplay.db.MainDao;
import com.bilplay.model.User;
import com.bilplay.view.IndexView;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/index")
@Produces(MediaType.TEXT_HTML)
public class IndexResource {
    private final MainDao dao;

    public IndexResource(MainDao dao) {
        this.dao = dao;
    }

    @GET
    public IndexView index() {
        User user = dao.getUserById(1);
        return new IndexView(user.getUsername());
    }
}
