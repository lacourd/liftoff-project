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
              "/images/pic-of-darren.jpeg",
              "Description of Darren LaCour" ,
              "Bio for DL"
        ));

        authors.add(new Author(
                "Marcie DeFonce",
                "/images/pic-of-Marcie.jpeg",
                "Description of MD",
                "Bio for MD"
        ));
        authors.add(new Author(
                "Audra Hartwell",
                "/images/pic-of-audra.jpeg",
                "Description of AH",
                "Bio for AH"
        ));


        authors.add(new Author(
                "Mike Zanger",
                "/images/author4.jpg",
                "Description of MZ",
                "Bio for MZ"
        ));

        authors.add(new Author(
                "Rose Wachira",
                "/images/pic-of-rose.jpeg",
                "Description of RW",
                "Bio for RW"
        ));

        model.addAttribute("authors", authors);
        return "about_us";
    }
}

