package org.launchcode.liftoffproject.controllers;


import org.launchcode.liftoffproject.data.ChildRepository;
import org.launchcode.liftoffproject.data.ChoreRepository;
import org.launchcode.liftoffproject.data.EarnedRewardsRepository;
import org.launchcode.liftoffproject.models.*;

import org.springframework.beans.factory.annotation.Autowired;
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
public class ChildDashboardController {

    @Autowired
    private ChildRepository childRepository;

    @Autowired
    private ChoreRepository choreRepository;

    @Autowired
    private EarnedRewardsRepository earnedRewardsRepository;

    @Autowired
    private AuthenticationController authenticationController;

    @GetMapping("dashboard")
    public String childDashboard(
            @RequestParam(name = "dueDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dueDate,
            @RequestParam(name = "choreName", required = false) String choreName,
            Model model, HttpSession session) {
        Child child = authenticationController.getChildFromSession(session);


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


        List<Reward> earnedRewards = earnedRewardsRepository.findAllByChild(child);


        // What is the selected day of the week from calendar
        String dayOfWeekMessage = null;
        if (dueDate != null) {
            DayOfWeek dayOfWeek = dueDate.getDayOfWeek();
            dayOfWeekMessage = "for : " + dayOfWeek.getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        }

        model.addAttribute("child", child);
        model.addAttribute("chores", chores);
        model.addAttribute("earnedRewards", earnedRewards);
        model.addAttribute("dayOfWeekMessage", dayOfWeekMessage); // Pass the calculated message

        return "dashboard";
    }

    @GetMapping("/chores/{dueDate}")
    public List<Chore> getChoresForDate(@PathVariable("dueDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dueDate, HttpSession session) {
        Child child = authenticationController.getChildFromSession(session);
        return choreRepository.findAllByChildAssignedAndDueDate(child, dueDate);
    }
    @PostMapping("/redeemReward")
    @ResponseBody
    public ResponseEntity<String> redeemReward(@RequestBody RedeemRewardRequest request, HttpSession session) {
        Child child = authenticationController.getChildFromSession(session);
        Reward redeemedReward = earnedRewardsRepository.findById(request.getRewardId()).orElse(null);

        if (child != null && redeemedReward != null && !redeemedReward.isRedeemed()) {
            redeemedReward.setRedeemed(true);
            redeemedReward.setRedemptionDate(LocalDate.now());
            redeemedReward.setChild(child);
            earnedRewardsRepository.save(redeemedReward);

            // Reduce child's earned points by the reward points
            child.setPoints(child.getPoints() - redeemedReward.getPoints());
            childRepository.save(child);

            return ResponseEntity.ok("Reward redeemed successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Reward not found or already redeemed.");
        }
    }
}



//    @PostMapping("/updateChoreCompletion")
//    @ResponseBody
//    public ResponseEntity<String> updateChoreCompletion(@RequestBody ChoreCompletionRequest request) {
//        Chore chore = choreRepository.findById(request.getChoreId()).orElse(null);
//        if (chore != null) {
//            chore.setCompleted(request.isCompleted());
//            choreRepository.save(chore);
//            return ResponseEntity.ok("Chore completion status updated successfully.");
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Chore not found.");
//        }
//    }


