package org.launchcode.liftoffproject.controllers;

import org.launchcode.liftoffproject.data.ChoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("chores")
public class ChoreController {

    @Autowired
    ChoreRepository choreRepository;
    @GetMapping
    public String displayChore(Model model){
        model.addAttribute("title" "All Chores");
        model.addAttribute("chores", choreRepository.findAll());
        return "chores/index";
    }

}
