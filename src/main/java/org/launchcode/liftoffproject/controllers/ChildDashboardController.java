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
import java.time.LocalDate;
import java.util.List;

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
    public String childDashboard(Model model, HttpSession session) {
        Child child = authenticationController.getChildFromSession(session);

        // Fetch the child's chores and earned rewards
        List<Chore> chores = choreRepository.findAllByChildAssigned(child);
        List<Reward> earnedRewards = earnedRewardsRepository.findAllByChild(child);

        model.addAttribute("child", child);
        model.addAttribute("chores", chores);
        model.addAttribute("earnedRewards", earnedRewards);
        return "dashboard";
    }
    @GetMapping("/chores/{dueDate}")
    public List<Chore> getChoresForDate(@PathVariable("dueDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dueDate) {

        return choreRepository.findByDueDate(dueDate);
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
            child.setEarnedPoints(child.getEarnedPoints() - redeemedReward.getPoints());
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

