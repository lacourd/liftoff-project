package org.launchcode.liftoffproject.controllers;

import org.launchcode.liftoffproject.data.ChildRepository;
import org.launchcode.liftoffproject.data.ChoreRepository;
import org.launchcode.liftoffproject.models.Chore;
import org.launchcode.liftoffproject.models.Child;
import org.launchcode.liftoffproject.models.ChoreCompletion;
import org.launchcode.liftoffproject.models.DayOfTheWeek;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("chores")
public class ChoreController {

    @Autowired
    private ChoreRepository choreRepository;

    @Autowired
    private AuthenticationController authenticationController;

    @Autowired
    private ChildRepository childRepository;

    @GetMapping
    public String displayAllChores(Model model) {
        model.addAttribute("title","All Chores");
        model.addAttribute("chores", choreRepository.findAll());
        return "chores/index";
    };

    @GetMapping("create")
    public String renderCreateChoreForm(Model model, HttpSession session) {
      model.addAttribute("title","New Chore");
      //model.addAttribute("days", DayOfTheWeek.values());
      model.addAttribute("crew", childRepository.findAllByParent(authenticationController.getParentFromSession(session)));
      model.addAttribute("chore", new Chore());
      return "chores/create";
    };

    @PostMapping("create")
    public String processCreateChoreForm(@ModelAttribute @Valid Chore chore, Errors errors, Model model, HttpSession session) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "New Chore");
            return "chores/create";
        }

        Child child = chore.getChildAssigned();
        chore.setChildAssigned(child);

        choreRepository.save(chore);


        return "redirect:/chores";
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
        //updatedChore.setDueDay(chore.getDueDay());
        updatedChore.setDueDate((LocalDate) chore.getDueDate());
        updatedChore.setRewardPoints(chore.getRewardPoints());
        choreRepository.save(updatedChore);
        return "redirect:/chores";
    }

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

    // Endpoint for marking a chore complete by the child
    @GetMapping("/complete")
    public String markChoreComplete(@RequestParam Integer choreId, @RequestParam boolean completed) {
        Chore chore = choreRepository.findById(choreId).orElse(null);

        if (chore != null) {
            if (completed) {
                // Mark the chore as complete and add a new completion entry
                ChoreCompletion completion = new ChoreCompletion();
                completion.setChore(chore);
                completion.setCompletedByChild(true);
                completion.setApprovedByParent(false);
                chore.getCompletions().add(completion);
            } else {
                // Update existing completion if it exists and is marked by the child
                for (ChoreCompletion completion : chore.getCompletions()) {
                    if (completion.isCompletedByChild()) {
                        // Update the existing completion to indicate it is not complete
                        completion.setCompletedByChild(false);
                        completion.setApprovedByParent(false);
                        break;
                    }
                }
            }
            choreRepository.save(chore);
        }

        return "redirect:/chores";
    }




    // Endpoint for approving a completed chore by the parent
    @PostMapping("approve")
    public String approveChore(@RequestParam Integer choreId) {
        Chore chore = choreRepository.findById(choreId).orElse(null);

        if (chore != null) {
            for (ChoreCompletion completion : chore.getCompletions()) {
                if (completion.isCompletedByChild() && !completion.isApprovedByParent()) {
                    completion.setApprovedByParent(true);

                    // Update the child's earnedPoints field
                    Child childAssigned = chore.getChildAssigned();
                    childAssigned.setPoints(childAssigned.getPoints() + chore.getRewardPoints());

                    childRepository.save(childAssigned);
                }
            }
            choreRepository.save(chore);
        }

        return "redirect:/chores";


    }



//    @GetMapping("/chores/{dueDate}")
//    public List<Chore> getChoresForDate(@PathVariable("dueDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dueDate) {
//
//        return choreRepository.findByDueDate(dueDate);
//    }
}

