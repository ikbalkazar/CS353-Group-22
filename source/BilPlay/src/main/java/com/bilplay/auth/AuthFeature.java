package com.bilplay.auth;

import com.bilplay.db.MainDao;

import javax.ws.rs.container.DynamicFeature;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.FeatureContext;

public class AuthFeature implements DynamicFeature {
    private MainDao dao;

    public AuthFeature(MainDao dao) {
        this.dao = dao;
    }

    @Override
    public void configure(ResourceInfo resourceInfo, FeatureContext featureContext) {
        if (resourceInfo.getResourceMethod().isAnnotationPresent(Authenticated.class)) {
            featureContext.register(new AuthRequestFilter(dao));
        }
    }
}
