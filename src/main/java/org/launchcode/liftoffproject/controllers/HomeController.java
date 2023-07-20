package org.launchcode.liftoffproject.controllers;

import org.launchcode.liftoffproject.models.dto.LoginFormDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @GetMapping
    public String index(Model model) {
        model.addAttribute(new LoginFormDTO());
        return "index";
    }

    @RequestMapping("/about")
    public String aboutPage() {
        return "about"; // Return the name of your Thymeleaf about page template
    }


}
