package org.launchcode.liftoffproject.services;

import org.launchcode.liftoffproject.models.Child;
import org.launchcode.liftoffproject.models.Chore;
import org.launchcode.liftoffproject.models.dto.AddChoreFormDTO;

import java.util.List;

public interface ChoreService {

    List<Chore> getChoresByChildUsername(String childUsername);

    Chore getChoreById(Long id);

    void saveChore(Chore chore);

    void markChoreAsComplete(Chore chore);

    void addChoreFromForm(AddChoreFormDTO addChoreFormDTO);

    List<Chore> getChoresByChild(Child child);
}

