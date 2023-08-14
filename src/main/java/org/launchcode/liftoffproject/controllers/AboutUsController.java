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
              "Description of Darren LaCour" ,
              "Bio for DL",
                "LinkedIn Url",
                "gitHub Link"
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
                "User Authorization, About the Authors, Rewards Page",
                "Former teacher turned coder. Check my links to learn more.",
                "https://www.linkedin.com/in/audra-hartwell-252a66113/",
                "https://github.com/AudraHartwell"
        ));


        authors.add(new Author(
                "Mike Zanger",
                "/images/author4.jpg",
                "Description of MZ",
                "Bio for MZ",
                "LinkedIn Url",
                "gitHub Link"
        ));

        authors.add(new Author(
                "Rose Wachira",
                "/images/creators/pic-of-rose.jpeg",
                "Description of RW",
                "Bio for RW",
                "LinkedIn Url",
                "gitHub Link"
        ));

        model.addAttribute("authors", authors);
        return "about_us";
    }
}

