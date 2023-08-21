package org.launchcode.liftoffproject.data;

import org.launchcode.liftoffproject.models.Child;
import org.launchcode.liftoffproject.models.Chore;
import org.launchcode.liftoffproject.models.Parent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;


import java.time.LocalDate;
import java.util.List;

public interface ChoreRepository extends PagingAndSortingRepository<Chore, Integer> {
    List<Chore> findAllByParentCreator(Parent parent);

    List<Chore> findAllByParentCreatorAndCompleted(Parent parent, boolean completed);

    List<Chore> findAllByParentCreatorAndCompletedAndApprovedByParent(Parent parent, boolean completed, boolean approved);

    List<Chore> findAllByParentCreatorAndApprovedByParent(Parent parent, boolean approved);

    List<Chore> findAllByChildAssigned(Child child);

    List<Chore> findByChildAssignedOrChildAssignedIsNullAndParentCreator(Child child, Parent parent);

    List<Chore> findAllByChildAssignedAndApprovedByParent(Child child, boolean approved);

    List<Chore> findByDueDate(LocalDate dueDate);

    List<Chore> findByDueDateAndChildAssignedAndNameContaining(LocalDate dueDate, Child child, String choreName);

    List<Chore> findByDueDateAndChildAssigned(LocalDate dueDate, Child child);

    List<Chore> findByChildAssignedAndNameContaining(Child child, String choreName);


    Page<Chore> findByParentCreatorAndCompleted(Parent parent, boolean completed, Pageable pageable);

    List<Chore> findAllByChildAssignedAndDueDate(Child child, LocalDate dueDate);
}
