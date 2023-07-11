package org.launchcode.liftoffproject.controllers;

import org.launchcode.liftoffproject.data.ChildRepository;
import org.launchcode.liftoffproject.data.RewardRepository;
import org.launchcode.liftoffproject.models.Parent;
import org.launchcode.liftoffproject.models.Child;
import org.launchcode.liftoffproject.models.Reward;
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
@RequestMapping("crew")
public class CrewController {

    @Autowired
    private ChildRepository childRepository;

    @GetMapping
    public String displayAllRewards(Model model) {
        model.addAttribute("title","All Crew Members");
        model.addAttribute("crew", Parent.getChildren());
        return "crew/index";
    };

    @GetMapping("add")
    public String renderCreateRewardForm(Model model) {
        model.addAttribute("title","Add a Crew Member");
        model.addAttribute(new Child());
        return "crew/add";
    };

    @PostMapping("add")
    public String processCreateRewardForm(@ModelAttribute @Valid Child newChild, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title","Add a Crew Member");
            model.addAttribute(new Child());
            return "crew/add";
        }
        childRepository.save(newChild);
        return "redirect:";
    }

}
