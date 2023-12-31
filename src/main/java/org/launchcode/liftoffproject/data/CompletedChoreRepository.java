package org.launchcode.liftoffproject.data;

import org.launchcode.liftoffproject.models.Child;
import org.launchcode.liftoffproject.models.Chore;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.LocalDate;
import java.util.List;

public interface CompletedChoreRepository extends PagingAndSortingRepository<Chore, Integer> {
    List<Chore> findAllByChildAssigned(Child child);

    List<Chore> findByDueDate(LocalDate dueDate);

}
