package org.launchcode.liftoffproject.controllers;


import org.launchcode.liftoffproject.models.ChorePerformance;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/choreperformances")
public class ChorePerformanceController {

    @RequestMapping("")
    public Iterable<ChorePerformance> getAllChorePerformances() {
        return ChorePerformance.findAll();
    }

    @RequestMapping("/{id}")
    public ChorePerformance getChorePerformanceById(Integer id) {
        return ChorePerformance.findById(id);
    }

}