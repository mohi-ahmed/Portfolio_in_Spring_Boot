package com.sparktech.portfolio_deploy.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SkillsController {

    @GetMapping("/skills")
    public String skillsPage() {
        return "skills";
    }

}
