package com.chikitsa.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.chikitsa.application.session.UserSessionRegistry;

@Controller
public class LoginController {

	@Autowired
    private UserSessionRegistry sessionRegistry;
	
    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/index")
    public String indexPage() {
        return "index"; // index.html
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("pageToLoad", "dashboard");
        model.addAttribute("activePage", "dashboard");
		model.addAttribute("pageTitle", "Dashboard");
        return "layout"; 
    }
    
    @GetMapping("/logout")
    public String dashboard() {
    	sessionRegistry.getAllSessions().forEach(session -> {
            try {
                session.invalidate(); 
            } catch (IllegalStateException ignored) {}
        });
    	return "index";
    }
}