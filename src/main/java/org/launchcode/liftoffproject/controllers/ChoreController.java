package org.launchcode.liftoffproject.controllers;

import org.launchcode.liftoffproject.data.ChoreRepository;
import org.launchcode.liftoffproject.models.Chore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

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

    @GetMapping("edit")
    public String displayEditChoreForm(@RequestParam("choreId") int choreId, Model model) {
        Chore chore = choreRepository.findById(choreId).orElse(null);
        if (chore == null) {
            return "redirect:/chores";
        }
        model.addAttribute("title", "Edit Chore");
        model.addAttribute("chore", chore);
        return "chores/edit";
    }

    @PostMapping("edit")
    public String processEditChoreForm(@RequestParam(required = false) int choreId, @ModelAttribute Chore chore) {

        Chore updatedChore = choreRepository.findById(choreId).orElse(null);

        updatedChore.setName(chore.getName());
        updatedChore.setChoreDescription(chore.getChoreDescription());
        updatedChore.setChildAssigned(chore.getChildAssigned());
        updatedChore.setDueDay(chore.getDueDay());
        updatedChore.setRewardPoints(chore.getRewardPoints());
        choreRepository.save(updatedChore);
        return "redirect:/chores";

    @GetMapping("detail")
    public String displayEventDetails(@RequestParam Integer choreId, Model model) {

        Optional<Chore> result = choreRepository.findById(choreId);

        if (result.isEmpty()) {
            model.addAttribute("title", "Invalid Chore ID: " + choreId);
        } else {
            Chore chore = result.get();
            model.addAttribute("title", chore.getName() + " Details");
            model.addAttribute("chore", chore);
        }

        return "chores/detail";
    }
}
