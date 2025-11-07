package com.chikitsa.application.session;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpSession;

@Component
public class UserSessionRegistry {

    private final Set<HttpSession> sessions = Collections.synchronizedSet(new HashSet<>());

    public void addSession(HttpSession session) {
        sessions.add(session);
    }

    public void removeSession(HttpSession session) {
        sessions.remove(session);
    }

    public Set<HttpSession> getAllSessions() {
        return sessions;
    }
}
