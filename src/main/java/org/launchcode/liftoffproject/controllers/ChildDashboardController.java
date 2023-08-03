package org.launchcode.liftoffproject.controllers;

import org.launchcode.liftoffproject.data.ChildRepository;
import org.launchcode.liftoffproject.data.ChoreRepository;
import org.launchcode.liftoffproject.data.EarnedRewardsRepository;
import org.launchcode.liftoffproject.models.Child;
import org.launchcode.liftoffproject.models.Chore;

import org.launchcode.liftoffproject.models.Reward;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
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

    @PostMapping("/updateAvatar")
    public String updateAvatar(@RequestBody String avatarUrl, HttpSession session) {
        Child child = (Child) session.getAttribute("child");
        if (child == null) {

            return "redirect:/login";
        }

        // Update child's avatar  in the database
        child.setAvatar(avatarUrl);
        childRepository.save(child);

        return "redirect:/dashboard";
    }

    @PostMapping("/updateChoreCompletion")
    @ResponseBody
    public ResponseEntity<String> updateChoreCompletion(@RequestBody ChoreCompletionRequest request) {
        Chore chore = choreRepository.findById(request.getChoreId()).orElse(null);
        if (chore != null) {
            chore.setCompleted(request.isCompleted());
            choreRepository.save(chore);
            return ResponseEntity.ok("Chore completion status updated successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Chore not found.");
        }
    }


    private static class ChoreCompletionRequest {
        private int choreId;
        private boolean completed;

        // Getters and setters

        public int getChoreId() {
            return choreId;
        }

        public void setChoreId(int choreId) {
            this.choreId = choreId;
        }

        public boolean isCompleted() {
            return completed;
        }

        public void setCompleted(boolean completed) {
            this.completed = completed;
        }
    }
}
