package org.launchcode.liftoffproject.controllers;

import org.launchcode.liftoffproject.data.ChildRepository;
import org.launchcode.liftoffproject.data.ChoreRepository;
import org.launchcode.liftoffproject.data.CommentRepository;
import org.launchcode.liftoffproject.data.CompletedChoreRepository;
import org.launchcode.liftoffproject.models.*;
import org.launchcode.liftoffproject.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("chores")
public class ChoreController {

    @Autowired
    private ChoreRepository choreRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private AuthenticationController authenticationController;

    @Autowired
    private ChildRepository childRepository;

    @GetMapping
    public String displayAllChores(@RequestParam(required = false) Integer childId, Model model, HttpSession session) {
        model.addAttribute("today", LocalDate.now());
        if (childId==null){
            model.addAttribute("title","All Chores");

            if (authenticationController.getChildFromSession(session) != null) {
                Child child = authenticationController.getChildFromSession(session);
                model.addAttribute("chores", choreRepository.findAllByChildAssigned(child));
            } else {
                Parent parent = authenticationController.getParentFromSession(session);
                model.addAttribute("chores", choreRepository.findAllByParentCreatorAndApprovedByParent(parent, false));
            }
        } else {
            Child child = childRepository.findById(childId).orElse(null);
            if (child != null) {
                model.addAttribute("chores", child.getChores());
                model.addAttribute("title", "Tasks assigned to " + child.getFirstName());
            } else {
                model.addAttribute("title", "Invalid Crew member");
            }
        }

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
    public String processCreateChoreForm(@ModelAttribute @Valid Chore newChore, Errors errors, Model model, HttpSession session) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "New Chore");
            model.addAttribute("crew", childRepository.findAllByParent(authenticationController.getParentFromSession(session)));
            return "chores/create";
        }
        newChore.setParentCreator(authenticationController.getParentFromSession(session));
        choreRepository.save(newChore);


        return "redirect:/chores";
    }
    @GetMapping("edit")
    public String displayEditChoreForm(@RequestParam("choreId") int choreId, HttpSession session, Model model) {
        Chore chore = choreRepository.findById(choreId).orElse(null);
        if (chore == null) {
            return "redirect:/chores";
        }
        model.addAttribute("crew", childRepository.findAllByParent(authenticationController.getParentFromSession(session)));
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
        updatedChore.setDueDate(chore.getDueDate());
        updatedChore.setRewardPoints(chore.getRewardPoints());
        updatedChore.setDetailedDescription(chore.getDetailedDescription());
        updatedChore.setSupplies(chore.getSupplies());
        choreRepository.save(updatedChore);
        return "redirect:/chores";
    }


    @GetMapping("detail")
    public String displayChoreDetails(@RequestParam Integer choreId, Model model, HttpSession session) {

        Optional<Chore> result = choreRepository.findById(choreId);

        if (result.isEmpty()) {
            model.addAttribute("title", "Invalid Chore ID: " + choreId);
        } else {
            Chore chore = result.get();
            model.addAttribute("title", chore.getName() + " Details");
            model.addAttribute("chore", chore);
            model.addAttribute("user", authenticationController.getUserFromSession(session));
            model.addAttribute("newComment", new Comment());
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
                chore.setCompleted(true);
                chore.setApprovedByParent(false);
            } else {
                chore.setCompleted(false);
                chore.setApprovedByParent(false);
                }
            }
            choreRepository.save(chore);

        return "redirect:/chores";
    }




    // Endpoint for approving a completed chore by the parent
    @PostMapping("approve")
    public String approveChore(@RequestParam Integer choreId) {
        Chore chore = choreRepository.findById(choreId).orElse(null);

        if (chore != null) {
            if (!chore.isApprovedByParent()) {
                    chore.setCompleted(true);
                    chore.setApprovedByParent(true);

                    // Update the child's earnedPoints field
                    Child childAssigned = chore.getChildAssigned();
                    childAssigned.setPoints(childAssigned.getPoints() + chore.getRewardPoints());

                    childRepository.save(childAssigned);
            }
        }

            choreRepository.save(chore);


        return "redirect:/chores";


    }

    @PostMapping("comment")
    public String addComment(@RequestParam int choreId, @ModelAttribute @Valid Comment newComment) {
        Chore chore = choreRepository.findById(choreId).orElse(null);
        newComment.setChore(chore);
        //newComment.setText("hello");
        newComment.setCreatedDate(LocalDate.now());
        commentRepository.save(newComment);

        return "redirect:/chores";

    }

//    @GetMapping("/chores/{dueDate}")
//    public List<Chore> getChoresForDate(@PathVariable("dueDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dueDate) {
//
//        return choreRepository.findByDueDate(dueDate);
//    }
}

