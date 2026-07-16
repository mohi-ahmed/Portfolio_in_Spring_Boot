package com.sparktech.portfolio_deploy.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExperienceController {

    @GetMapping("/experience")
    public String experiencePage() {
        return "experience";
    }
}
