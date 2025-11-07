package com.chikitsa.application.session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

@Component
public class SessionListener implements HttpSessionListener {

    @Autowired
    private UserSessionRegistry sessionRegistry;

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        sessionRegistry.addSession(se.getSession());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        sessionRegistry.removeSession(se.getSession());
    }
}
