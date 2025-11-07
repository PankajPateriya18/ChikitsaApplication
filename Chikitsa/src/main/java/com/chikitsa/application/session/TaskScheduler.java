package com.chikitsa.application.session;


import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TaskScheduler {

    // Runs every 30 minutes
    @Scheduled(fixedRate = 30 * 60 * 1000) // 30 min = 1800000 ms
    public void runEvery30Minutes() {

        System.out.println("✅ Scheduled task executed at: " + new java.util.Date());

        // 👉 place your logic here (call service / repository etc.)
        // example
        // notesService.generateReport();
    }
}


