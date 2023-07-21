package org.launchcode.liftoffproject.data;

import org.launchcode.liftoffproject.models.Child;
import org.launchcode.liftoffproject.models.Parent;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ChildRepository extends PagingAndSortingRepository<Child, Integer> {

    List<Child> findAllByParent(Parent parent);
}
