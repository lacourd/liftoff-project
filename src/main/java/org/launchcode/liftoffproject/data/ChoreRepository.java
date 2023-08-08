package org.launchcode.liftoffproject.data;

import org.launchcode.liftoffproject.models.Child;
import org.launchcode.liftoffproject.models.Chore;
import org.launchcode.liftoffproject.models.Parent;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.LocalDate;
import java.util.List;

public interface ChoreRepository extends PagingAndSortingRepository<Chore, Integer> {
    List<Chore> findAllByParentCreator(Parent parent);

    List<Chore> findAllByParentCreatorAndApprovedByParent(Parent parent, boolean approved);

    List<Chore> findAllByChildAssigned(Child child);

    List<Chore> findAllByChildAssignedAndApprovedByParent(Child child, boolean approved);

    List<Chore> findByDueDate(LocalDate dueDate);

    List<Chore> findAllByChildAssignedAndDueDate(Child child, LocalDate dueDate);
}
