package com.chikitsa.application.session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class AutoLogoutTask {

    @Autowired
    private UserSessionRegistry sessionRegistry;

    @Scheduled(fixedRate =  30 * 60 * 1000) // 30 * 60 * 1000 runs every 30 minutes
    public void runEvery30Minutes() {

        System.out.println("🔁 Auto Logout Triggered at: " + new java.util.Date());

        sessionRegistry.getAllSessions().forEach(session -> {
            try {
                session.invalidate();  // logout session
            } catch (IllegalStateException ignored) {}
        });

        System.out.println("✅ All active sessions invalidated successfully");
    }
}
