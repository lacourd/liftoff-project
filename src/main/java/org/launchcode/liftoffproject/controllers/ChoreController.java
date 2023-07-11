package org.launchcode.liftoffproject.controllers;

import org.launchcode.liftoffproject.data.ChoreRepository;
import org.launchcode.liftoffproject.models.Chore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("chores")
public class ChoreController {

    @Autowired
    private ChoreRepository choreRepository;

    @GetMapping
    public String displayAllChores(Model model) {
        model.addAttribute("title","All Chores");
        model.addAttribute("chores", choreRepository.findAll());
        return "chores/index";
    };

    @GetMapping("create")
    public String renderCreateChoreForm(Model model) {
      model.addAttribute("title","New Chore");
      model.addAttribute(new Chore());
      return "chores/create";
    };

    @PostMapping("create")
    public String processCreateChoreForm(@ModelAttribute @Valid Chore newChore, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title","New Chore");
            model.addAttribute(new Chore());
            return "chores/create";
        }
        choreRepository.save(newChore);
        return "redirect:";
    }
}
