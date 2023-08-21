package org.launchcode.liftoffproject.controllers;

import org.launchcode.liftoffproject.models.Author;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AboutUsController {

    @GetMapping("/aboutUs")
    public String displayAboutUs(Model model) {
      List<Author> authors = new ArrayList<>();

        authors.add(new Author(
              "Darren LaCour ",
              "/images/creators/pic-of-darren.jpeg",
              "Backend, User Role Configuration, Creating Crew Members and Chores" ,
              "Music theorist turned developer. Links below to say hello!",
                "https://www.linkedin.com/in/darrenlacour/",
                "https://github.com/lacourd"
        ));

        authors.add(new Author(
                "Marcie DeFonce",
                "/images/creators/pic-of-Marcie.jpeg",
                "Description of MD",
                "Bio for MD",
                "LinkedIn Url",
                "gitHub Link"
        ));
        authors.add(new Author(
                "Audra Hartwell",
                "/images/creators/pic-of-audra.jpeg",
                "User Authorization, About the Creators, Rewards Page",
                "Former teacher turned coder. Check my links to learn more.",
                "https://www.linkedin.com/in/audra-hartwell-252a66113/",
                "https://github.com/AudraHartwell"
        ));


        authors.add(new Author(
                "Mike Zanger",
                "/images/creators/pic-of-Mike.jpeg",
                "Backend, Creating Chore Details Page",
                "Current cook and coder.",
                "https://www.linkedin.com/in/michael-zanger-ba8b1a287/",
                "https://github.com/mikezanger"
        ));

        authors.add(new Author(
                "Rose Wachira",
                "/images/creators/pic-of-rose.jpeg",
                "Backend, DatePicker, Creating Child Dashboard Page",
                "Dietitian turned developer. Check my links to learn more.",
                "https://www.linkedin.com/in/rosewachira",
                "https://github.com/WachiraRose"
        ));

        model.addAttribute("authors", authors);
        return "about_us";
    }
}

