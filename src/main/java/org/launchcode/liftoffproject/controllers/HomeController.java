package org.launchcode.liftoffproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @GetMapping
    public String index() {
        return "index";
    }

    @RequestMapping("/about")
    public String aboutPage() {
        return "about"; // Return the name of your Thymeleaf about page template
    }


}
