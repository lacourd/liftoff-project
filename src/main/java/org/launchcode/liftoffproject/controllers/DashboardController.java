package org.launchcode.liftoffproject.controllers;


import org.launchcode.liftoffproject.data.ChildRepository;
import org.launchcode.liftoffproject.data.ChoreRepository;
import org.launchcode.liftoffproject.data.EarnedRewardsRepository;
import org.launchcode.liftoffproject.data.RewardRepository;
import org.launchcode.liftoffproject.models.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

@Controller
public class DashboardController {

    @Autowired
    private ChildRepository childRepository;

    @Autowired
    private ChoreRepository choreRepository;

    @Autowired
    private RewardRepository rewardRepository;

    @Autowired
    private AuthenticationController authenticationController;

    @GetMapping("dashboard")
    public String renderDashboard(
            @RequestParam(name = "dueDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dueDate,
            @RequestParam(name = "choreName", required = false) String choreName,
            Model model, HttpSession session) {
        Child child = authenticationController.getChildFromSession(session);
        if (child != null) {
            List<Chore> chores;
            if (dueDate != null && choreName != null) {
                chores = choreRepository.findByDueDateAndChildAssignedAndNameContaining(dueDate, child, choreName);
            } else if (dueDate != null) {
                chores = choreRepository.findByDueDateAndChildAssigned(dueDate, child);
            } else if (choreName != null) {
                chores = choreRepository.findByChildAssignedAndNameContaining(child, choreName);
            } else {
                chores = choreRepository.findAllByChildAssigned(child);
            }


            List<Reward> earnedRewards = rewardRepository.findAllByChildAndRedeemed(child, true);

            // What is the selected day of the week from calendar
            String dayOfWeekMessage = null;
            if (dueDate != null) {
                DayOfWeek dayOfWeek = dueDate.getDayOfWeek();
                dayOfWeekMessage = "for : " + dayOfWeek.getDisplayName(TextStyle.FULL, Locale.ENGLISH);
            }

            model.addAttribute("child", child);
            model.addAttribute("chores", chores);
            model.addAttribute("earnedRewards", earnedRewards);
            model.addAttribute("avatar", child.getAvatar());
            model.addAttribute("dayOfWeekMessage", dayOfWeekMessage); // Pass the calculated message
        } else {
            model.addAttribute("today", LocalDate.now());
            Parent parent = authenticationController.getParentFromSession(session);
            model.addAttribute("parent", parent);
            List<Chore> pendingChores = choreRepository.findAllByParentCreatorAndCompletedAndApprovedByParent(parent, true,false);
            model.addAttribute("pendingChores", pendingChores);
            List<Child> crew = childRepository.findAllByParent(parent);
            model.addAttribute("crew", crew);
            List<Chore> incompleteChores = choreRepository.findAllByParentCreatorAndCompleted(parent, false);
            model.addAttribute("incompleteChores", incompleteChores);
            Pageable pageable;
            pageable = PageRequest.of(0, 5, Sort.by("id").ascending());
            Page<Chore> recentlyCompleted = choreRepository.findByParentCreatorAndCompleted(parent,true, pageable);
            model.addAttribute("recentlyCompleted", recentlyCompleted);
            List<Reward> pendingRewards = rewardRepository.findAllByParentCreatorAndRedeemedAndFulfilled(parent, true, false);
            model.addAttribute("pendingRewards", pendingRewards);
            List<Reward> recentlyRedeemed = rewardRepository.findAllByParentCreatorAndRedeemed(parent, true);
            model.addAttribute("recentlyRedeemed", recentlyRedeemed);
            List<Reward> availableRewards = rewardRepository.findAllByParentCreatorAndRedeemed(parent, false);
            model.addAttribute("availableRewards", availableRewards);
        }

        return "dashboard";
    }

    @PostMapping("avatar")
    public String processAvatarForm(HttpSession session, @ModelAttribute Child child) {
        Child updatedChild = authenticationController.getChildFromSession(session);
        updatedChild.setAvatar(child.getAvatar());
        childRepository.save(updatedChild);
        return "redirect:/dashboard";
    }

    @GetMapping("/chores/{dueDate}")
    public List<Chore> getChoresForDate(@PathVariable("dueDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dueDate, HttpSession session) {
        Child child = authenticationController.getChildFromSession(session);
        return choreRepository.findByDueDateAndChildAssigned(dueDate, child);
    }

}


