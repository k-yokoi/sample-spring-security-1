package org.debugroom.sample.spring.security.chat.app.web;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class SampleController {

    @GetMapping(value = "/login")
    public String login(){
        return "login";
    }

    @GetMapping(value= "/portal")
    public String portal(Model model, HttpSession httpSession){
        String sessionId = httpSession.getId();
        model.addAttribute("sessionId", sessionId);
        return "portal";
    }

    @GetMapping(value = "/invalidateSession")
    @ResponseBody
    public ResponseEntity<String> invalidateSession(HttpSession httpSession){
        String sessionId = httpSession.getId();
        httpSession.invalidate();
        return ResponseEntity.ok("Session Invalidate: " + sessionId);
    }

    @GetMapping(value = "/timeout")
    public String timeout(){
        return "timeout";
    }

}
