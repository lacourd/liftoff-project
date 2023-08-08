package org.launchcode.liftoffproject.data;

import org.launchcode.liftoffproject.models.Child;
import org.launchcode.liftoffproject.models.Chore;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.LocalDate;
import java.util.List;

public interface ChoreRepository extends PagingAndSortingRepository<Chore, Integer> {
    List<Chore> findAllByChildAssigned(Child child);

    List<Chore> findByDueDate(LocalDate dueDate);

    List<Chore> findByDueDateAndChildAssignedAndNameContaining(LocalDate dueDate, Child child, String choreName);

    List<Chore> findByDueDateAndChildAssigned(LocalDate dueDate, Child child);

    List<Chore> findByChildAssignedAndNameContaining(Child child, String choreName);
}
