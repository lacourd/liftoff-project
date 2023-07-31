package org.launchcode.liftoffproject.data;

import org.launchcode.liftoffproject.models.Child;
import org.launchcode.liftoffproject.models.Chore;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ChoreRepository extends PagingAndSortingRepository<Chore, Integer> {
    List<Chore> findByChildAssigned(Child child);
}
