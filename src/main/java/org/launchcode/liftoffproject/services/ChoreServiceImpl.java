package org.launchcode.liftoffproject.services;

import org.launchcode.liftoffproject.models.Chore;
import org.launchcode.liftoffproject.models.Child;
import org.launchcode.liftoffproject.models.dto.AddChoreFormDTO;
import org.launchcode.liftoffproject.data.ChoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChoreServiceImpl implements ChoreService {

    @Autowired
    private ChoreRepository choreRepository;

    @Override
    public List<Chore> getChoresByChildUsername(String childUsername) {
        // return choreRepository.findByChildUsername(childUsername);
        return null;
    }

    @Override
    public Chore getChoreById(Long id) {

        // return choreRepository.findById(id).orElse(null);
        return null;
    }

    @Override
    public void saveChore(Chore chore) {

        // choreRepository.save(chore);
    }

    @Override
    public void markChoreAsComplete(Chore chore) {

        // chore.setCompleted(true);
        // choreRepository.save(chore);
    }

    @Override
    public void addChoreFromForm(AddChoreFormDTO addChoreFormDTO) {

        // Chore newChore = new Chore();
        // newChore.setName(addChoreFormDTO.getName());
        // newChore.setChoreDescription(addChoreFormDTO.getChoreDescription());
        // newChore.setChildAssigned(addChoreFormDTO.getChildAssigned());
        // newChore.setDueDay(addChoreFormDTO.getDueDay());
        // newChore.setRewardPoints(addChoreFormDTO.getRewardPoints());
        // choreRepository.save(newChore);
    }

    @Override
    public List<Chore> getChoresByChild(Child child) {
        // fetch chores for a specific child from the database

        return choreRepository.findByChildAssigned(child);
    }
}
